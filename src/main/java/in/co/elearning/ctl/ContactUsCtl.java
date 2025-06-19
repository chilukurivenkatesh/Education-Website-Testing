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

import in.co.elearning.dto.ContactUsDTO;
import in.co.elearning.dto.UserDTO;
import in.co.elearning.exception.DuplicateRecordException;
import in.co.elearning.form.ContactUsForm;
import in.co.elearning.service.ContactUsServiceInt;



@Controller
public class ContactUsCtl extends BaseCtl {

	@Autowired
	private ContactUsServiceInt service;
	
	@ModelAttribute
	public void preload(Model model) {
	}

	@GetMapping("/home/contactUs")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") ContactUsForm form,
			HttpSession session, Model model) {
		
		return "contactUs";
	}

	@PostMapping("/home/contactUs")
	public String submit(@Valid @ModelAttribute("form") ContactUsForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/contactUs";
		}
		try {
			if (OP_SUBMIT.equalsIgnoreCase(form.getOperation())) {
				
				if (bindingResult.hasErrors()) {
					return "contactUs";
				}
				
				ContactUsDTO bean = (ContactUsDTO) populateDTO(form.getDTO(),request);
		
					service.add(bean);
					model.addAttribute("success", "Data Submitted Successfully!!!!");
				
				return "contactUs";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "contactUs";
		} 
		return "";
	}

	@RequestMapping(value = "/home/login/contactUs/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") ContactUsForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/contactUs/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/login/contactUs";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					ContactUsDTO dto = new ContactUsDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		ContactUsDTO dto = (ContactUsDTO) form.getDTO();

		UserDTO uDto = (UserDTO) session.getAttribute("user");
		

		List<ContactUsDTO> list = service.search(dto, pageNo, pageSize);
		List<ContactUsDTO> totallist = service.search(dto);
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
		return "contactUsList";
	}
	
	
	

}
