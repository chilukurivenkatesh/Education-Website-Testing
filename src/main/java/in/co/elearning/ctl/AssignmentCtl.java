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

import in.co.elearning.dto.AssignmentDTO;
import in.co.elearning.dto.ContentDTO;
import in.co.elearning.dto.CourseDTO;
import in.co.elearning.dto.UserDTO;
import in.co.elearning.exception.DuplicateRecordException;
import in.co.elearning.form.AssignmentForm;
import in.co.elearning.form.ContactUsForm;
import in.co.elearning.form.ContentForm;
import in.co.elearning.service.AssignmentService;
import in.co.elearning.service.ContentServiceInt;
import in.co.elearning.service.CourseServiceInt;
import in.co.elearning.util.DataUtility;

@Controller
public class AssignmentCtl {

	@Autowired
	public AssignmentService service;

	@GetMapping("/assignment")
	public String display( @ModelAttribute("form") AssignmentForm form, HttpSession session, Model model) {

		return "assignment";
	}

	@GetMapping("/assignmentlist")
	public String list(@ModelAttribute("form") AssignmentForm form, HttpSession session, Model model) {

		List<AssignmentDTO> list = service.list();
		model.addAttribute("list", list);
		return "assignmentlist";
	}

	@PostMapping("/AddAssignment")
	public String Add(@Valid @ModelAttribute("form") AssignmentForm form,@RequestParam("afile") MultipartFile file, BindingResult bindingResult,
					  HttpSession session, Model model, HttpServletRequest request) {

		AssignmentDTO bean = (AssignmentDTO) form.getDTO();
		try {
			bean.setAfile(file.getBytes());
			bean.setStatus("Pending");
			service.add(bean);
			model.addAttribute("success", "Assignment Added Successfully..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "assignment";
	}

	@PostMapping("/submitAssignment")
	public String SubmitAssignment(@ModelAttribute("form") AssignmentForm form, @RequestParam("ansAssignment") MultipartFile file,  BindingResult bindingResult,
								   HttpSession session, Model model, HttpServletRequest request) {

		AssignmentDTO bean = (AssignmentDTO)form.getDTO();
		System.out.println("Bean ID..............: "+bean.getId());

		AssignmentDTO ebean = service.findBypk(bean.getId());
		System.out.println("ebean ID..............: "+ebean);
		try {
			ebean.setAnsAssignment(file.getBytes());
			ebean.setStatus("Submitted");
			//bean.setAfile(afile.getBytes());
			System.out.println("bean: "+ebean.toString());
			service.submitAssignment(ebean);

			model.addAttribute("success", "Assignment Submited Successfully..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return "redirect:/assignmentlist";
	}

	@GetMapping("getAfile/{id}")
	public void getNewsImage(HttpServletResponse response, @PathVariable("id") long id) throws Exception {

		Blob blb= service.getAfileById(id);
		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());

	}

	@GetMapping("getSubmitfile/{id}")
	public void getSubmitfile(HttpServletResponse response, @PathVariable("id") long id) throws Exception {

		Blob blb= service.getSubmitfileById(id);
		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());

	}

}
