package in.co.elearning.ctl;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import in.co.elearning.dto.FaqDTO;
import in.co.elearning.exception.DuplicateRecordException;
import in.co.elearning.form.FaqForm;
import in.co.elearning.service.FaqService;

@Controller
public class FaqCtl {
	
	
	@Autowired
	public FaqService service;

	@GetMapping("/faq")
	public String display( @ModelAttribute("form") FaqForm form, HttpSession session, Model model) {

		return "faq";
	}

	@GetMapping("/faqlist")
	public String list(@ModelAttribute("form") FaqForm form, HttpSession session, Model model) {
        
		List<FaqDTO> list = service.list();
		model.addAttribute("list", list);
		return "faqlist";
	}

	@PostMapping("/addfaq")
	public String Add(@Valid @ModelAttribute("form") FaqForm form, BindingResult bindingResult,
			HttpSession session, Model model, HttpServletRequest request) {		
		FaqDTO bean = (FaqDTO) form.getDTO();
		try {
			
			service.add(bean);
			model.addAttribute("success", "FAQ Added Successfully..");
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return "faq";
	}
	
	@GetMapping("/faqEdit")	
	public String update(@ModelAttribute("form")FaqForm form, Model model, @RequestParam("id") long id ){
		FaqDTO bean = service.findBypk(id);
		form.populate(bean);
		model.addAttribute("bean",bean);	
		return "faq";
	}
	
	@GetMapping("/faqDelete")	
	public String delete(@ModelAttribute("form")FaqForm form, Model model, @RequestParam("id") long id ) throws Exception{
		service.delete(id);	
		
		List<FaqDTO> list =	service.list();
		model.addAttribute("list", list);
		model.addAttribute("success", "Faq Deleted successfully");
		return "faqlist";
	}


}
