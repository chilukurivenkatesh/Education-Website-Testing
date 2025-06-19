package in.co.elearning.ctl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.elearning.dto.CourseDTO;
import in.co.elearning.dto.EnrollDTO;
import in.co.elearning.dto.UserDTO;
import in.co.elearning.exception.DuplicateRecordException;
import in.co.elearning.form.EnrollForm;
import in.co.elearning.service.CourseServiceInt;
import in.co.elearning.service.EnrollServiceInt;
import in.co.elearning.util.DataUtility;



@Controller
public class EnrollCtl extends BaseCtl {

	@Autowired
	private EnrollServiceInt service;
	
	@Autowired
	private CourseServiceInt courseService;
	
	@ModelAttribute
	public void preload(Model model) {
	}

	@GetMapping("/home/login/student/enroll")
	public String display(@RequestParam(required = false) Long id, Long cId, @ModelAttribute("form") EnrollForm form,
			HttpSession session, Model model,HttpServletRequest request) {
		if (form.getId() > 0) {
			EnrollDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		UserDTO uDto=(UserDTO)session.getAttribute("user");
		try {
		cId=DataUtility.getLong(String.valueOf(cId));
		if(service.findByCourseIdAndUserId(cId,uDto.getId())!=null) {
			return "redirect:/home/login/student/mycourse/enroll/search";
		}
		if(cId > 0) {	
			CourseDTO cDto=courseService.findBypk(cId);
			EnrollDTO dto=new EnrollDTO();
			dto.setUserId(uDto.getId());
			dto.setUser(uDto);
			dto.setCourseId(cId);
			dto.setCourse(cDto);
			dto=(EnrollDTO)populateDTO(dto, request);
			service.add(dto);
			return "redirect:/home/login/student/mycourse/enroll/search";
		}else {
			return "redirect:/home/login/student/course/search";
		}
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "enroll";
	}

	@PostMapping("/home/login/student/enroll")
	public String submit(@Valid @ModelAttribute("form") EnrollForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/student/enroll";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "enroll";
				}
				EnrollDTO bean = (EnrollDTO) populateDTO(form.getDTO(),request);
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Enroll update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Enroll Added Successfully!!!!");
				}
				return "enroll";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "enroll";
		} 
		return "";
	}

	@RequestMapping(value = "/home/login/student/mycourse/enroll/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") EnrollForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/student/mycourse/enroll/search";
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

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					EnrollDTO dto = new EnrollDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		EnrollDTO dto = (EnrollDTO) form.getDTO();

		UserDTO uDto = (UserDTO) session.getAttribute("user");
		dto.setUserId(uDto.getId());

		List<EnrollDTO> list = service.search(dto, pageNo, pageSize);
		List<EnrollDTO> totallist = service.search(dto);
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
		return "enrollList";
	}
	
	
	

}
