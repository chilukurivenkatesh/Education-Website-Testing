package in.co.elearning.form;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import in.co.elearning.dto.BaseDTO;
import in.co.elearning.dto.ContentDTO;
import in.co.elearning.dto.CourseDTO;
import in.co.elearning.dto.ContactUsDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactUsForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;
	
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "Message is required")
	private String message;
	

	@Override
	public BaseDTO getDTO() {
		ContactUsDTO bean=new ContactUsDTO();
		bean.setId(id);
		bean.setName(name);
		bean.setEmail(email);
		bean.setMessage(message);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		ContactUsDTO bean=(ContactUsDTO) bdto;
		id=bean.getId();
		name=bean.getName();
		email=bean.getEmail();
		message=bean.getMessage();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
