package in.co.elearning.ctl;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WelcomeCtl {

	@GetMapping({"/home","/"})
	public String display(Model model) {
		return "welcome";
	}

	@GetMapping("/home/aboutUs")
	public String aboutUs(Model model) {
		return "aboutUs";
	}

	@GetMapping("/home/login/dashboard")
	public String dashboard(Model model) {
		return "dashboard";
	}



}
