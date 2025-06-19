package in.co.elearning.form;

import javax.validation.constraints.NotEmpty;

import in.co.elearning.dto.BaseDTO;
import in.co.elearning.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm extends BaseForm {

	@NotEmpty(message = "Login is required")
	private String login;
	@NotEmpty(message = "Password is required")
	private String password;
	

	@Override
	public BaseDTO getDTO() {
		UserDTO bean=new UserDTO();
		bean.setLogin(login);
		bean.setPassword(password);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		UserDTO entity=(UserDTO) bdto;
		login=entity.getLogin();
		password=entity.getPassword();
	}

}
