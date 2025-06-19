package in.co.elearning.ctl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.co.elearning.dto.ContentDTO;
import in.co.elearning.dto.CourseDTO;
import in.co.elearning.dto.UserDTO;
import in.co.elearning.exception.DuplicateRecordException;
import in.co.elearning.form.ContentForm;
import in.co.elearning.service.ContentServiceInt;
import in.co.elearning.service.CourseServiceInt;
import in.co.elearning.util.DataUtility;



@Controller
public class ContentCtl extends BaseCtl {

	@Autowired
	private ContentServiceInt service;

	@Autowired
	private CourseServiceInt courseService;

	@ModelAttribute
	public void preload(Model model) {
	}

	@GetMapping("/home/login/dashboard/course/content")
	public String display(@RequestParam(required = false) Long id, Long cId, @ModelAttribute("form") ContentForm form,
						  HttpSession session, Model model) {
		if (form.getId() > 0) {
			ContentDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		if(DataUtility.getLong(String.valueOf(id))==0) {
			cId=DataUtility.getLong(String.valueOf(cId));
			if(cId > 0) {
				session.setAttribute("cId",cId);
			}else {
				return "redirect:/home/login/dashboard/course/search";
			}
		}
		return "content";
	}

	@GetMapping("/home/login/student/myCourse/content/view")
	public String showContent(@RequestParam(required = false) Long id, Long cId, @ModelAttribute("form") ContentForm form,
							  HttpSession session, Model model) {
		if (form.getId() > 0) {
			ContentDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		cId=DataUtility.getLong(String.valueOf(cId));
		if(cId > 0) {
			session.setAttribute("cId",cId);
		}else {
			return "redirect:/ctl/enroll";
		}
		return "view";
	}

	@PostMapping("/home/login/dashboard/course/content")
	public String submit(@RequestParam("video") MultipartFile file, @RequestParam("material") MultipartFile file1,@Valid @ModelAttribute("form") ContentForm form, BindingResult bindingResult,
						 HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/dashboard/course/content";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "content";
				}
				ContentDTO bean = (ContentDTO) form.getDTO();
				bean.setVideo(file.getBytes());
				bean.setMaterial(file1.getBytes());
				CourseDTO cDto=courseService.findBypk(DataUtility.getLong(String.valueOf(session.getAttribute("cId"))));
				bean.setCourse(cDto);
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Content update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Content Added Successfully!!!!");
				}
				return "content";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "content";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/home/login/dashboard/course/content/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") ContentForm form,
							 @RequestParam(required = false) String operation, Long cId, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/dashboard/course/content/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/login/dashboard/course/content";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					ContentDTO dto = new ContentDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		ContentDTO dto = (ContentDTO) form.getDTO();

		UserDTO uDto = (UserDTO) session.getAttribute("user");
		cId =DataUtility.getLong(String.valueOf(cId));


		List<ContentDTO> list = service.search(dto, pageNo, pageSize);
		List<ContentDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "contentList";
	}

	@RequestMapping(value = "/home/login/student/myCourse/content", method = { RequestMethod.GET, RequestMethod.POST })
	public String myCourseContent(@ModelAttribute("form") ContentForm form,
								  @RequestParam(required = false) String operation, Long cId, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/ctl/myCourse/content";
		}

		ContentDTO dto = (ContentDTO) form.getDTO();

		cId =DataUtility.getLong(String.valueOf(cId));
		model.addAttribute("cDto",courseService.findBypk(cId));

		List<ContentDTO> list = service.search(dto);
		List<ContentDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("form", form);
		return "courseContent";
	}



	@GetMapping("/ctl/content/getVideo/{id}")
	public void getVideo(HttpServletResponse response, @PathVariable("id") long id) throws Exception {

		Blob blb=service.getVideoById(id);

		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}

	@GetMapping("/ctl/content/getMaterial/{id}")
	public void getMaterial(HttpServletResponse response, @PathVariable("id") long id) throws Exception {

		Blob blb=service.getMaterialById(id);

		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}

}
