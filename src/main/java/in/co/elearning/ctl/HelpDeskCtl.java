package in.co.elearning.ctl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import in.co.elearning.dto.HelpDeskDTO;
import in.co.elearning.dto.UserDTO;
import in.co.elearning.exception.DuplicateRecordException;
import in.co.elearning.form.HelpDeskForm;
import in.co.elearning.service.HelpDeskServiceInt;
import in.co.elearning.util.DataUtility;



@Controller
public class HelpDeskCtl extends BaseCtl {

	@Autowired
	private HelpDeskServiceInt service;

	@ModelAttribute
	public void preload(Model model) {
	}

	@GetMapping("/home/login/helpDesk")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") HelpDeskForm form,
						  HttpSession session, Model model) {
		if (form.getId() > 0) {
			HelpDeskDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "helpDesk";
	}

	@PostMapping("/home/login/helpDesk")
	public String submit(@Valid @ModelAttribute("form") HelpDeskForm form, BindingResult bindingResult,
						 HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/helpDesk";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "helpDesk";
				}
				HelpDeskDTO bean = (HelpDeskDTO) populateDTO(form.getDTO(),request);
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "HelpDesk update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "HelpDesk Added Successfully!!!!");
				}
				return "helpDesk";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "helpDesk";
		}
		return "";
	}

	@RequestMapping(value = "/home/login/helpDesk/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") HelpDeskForm form,
							 @RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/helpDesk/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/login/helpDesk";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					HelpDeskDTO dto = new HelpDeskDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		HelpDeskDTO dto = (HelpDeskDTO) form.getDTO();

		UserDTO uDto = (UserDTO) session.getAttribute("user");


		List<HelpDeskDTO> list = service.search(dto, pageNo, pageSize);
		List<HelpDeskDTO> totallist = service.search(dto);
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
		return "helpDeskList";
	}




}
