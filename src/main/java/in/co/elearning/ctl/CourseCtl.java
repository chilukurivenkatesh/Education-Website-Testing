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

import in.co.elearning.dto.CourseDTO;
import in.co.elearning.dto.UserDTO;
import in.co.elearning.exception.DuplicateRecordException;
import in.co.elearning.form.CourseForm;
import in.co.elearning.service.CourseServiceInt;



@Controller
public class CourseCtl extends BaseCtl {

	@Autowired
	private CourseServiceInt service;

	@ModelAttribute
	public void preload(Model model) {
	}

	@GetMapping("/home/login/dashboard/course")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") CourseForm form,
						  HttpSession session, Model model) {
		if (form.getId() > 0) {
			CourseDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "course";
	}

	@PostMapping("/home/login/dashboard/course")
	public String submit(@RequestParam("image") MultipartFile file,@Valid @ModelAttribute("form") CourseForm form, BindingResult bindingResult,
						 HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/dashboard/course";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "course";
				}
				CourseDTO bean = (CourseDTO) form.getDTO();
				UserDTO uDto=(UserDTO)session.getAttribute("user");
				bean.setUserId(uDto.getId());
				if (bean.getId() > 0) {
					System.out.println("In Edit Operation");
					if(file.getBytes().toString()==null) {
						System.out.print("Controller checking bytes in course is null`");
						bean.setImage(service.findBypk(bean.getId()).getImage());
					}else {
						bean.setImage(file.getBytes());
					}
					service.update(bean);
					model.addAttribute("success", "Course update Successfully!!!!");
				} else {
					bean.setImage(file.getBytes());
					service.add(bean);
					model.addAttribute("success", "Course Added Successfully!!!!");
				}
				return "course";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "course";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/home/login/dashboard/course/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") CourseForm form,
							 @RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/dashboard/course/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/login/dashboard/course";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					CourseDTO dto = new CourseDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		CourseDTO dto = (CourseDTO) form.getDTO();

		UserDTO uDto = (UserDTO) session.getAttribute("user");


		List<CourseDTO> list = service.search(dto, pageNo, pageSize);
		List<CourseDTO> totallist = service.search(dto);
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
		return "courseList";
	}

	@RequestMapping(value = "/home/login/student/course/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchUserList(@ModelAttribute("form") CourseForm form,
								 @RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/student/course/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		}
		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;


		CourseDTO dto = (CourseDTO) form.getDTO();


		List<CourseDTO> list = service.search(dto, pageNo, pageSize);
		List<CourseDTO> totallist = service.search(dto);
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
		return "userCourse";
	}


	@GetMapping("/ctl/course/getImage/{id}")
	public void getStudentPhoto(HttpServletResponse response, @PathVariable("id") long id) throws Exception {

		Blob blb=service.getImageById(id);

		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}

}
