package in.co.elearning.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import in.co.elearning.dto.BaseDTO;
import in.co.elearning.dto.CourseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;
	@NotEmpty(message = "Description is required")
	private String description;
	@NotEmpty(message = "Instructor is required")
	private String instructor;
	@NotEmpty(message = "Language is required")
	private String language;
	
	@NotEmpty(message = "About Course is required")
	private String about;
	
	
	private MultipartFile image;
	

	@Override
	public BaseDTO getDTO() {
		CourseDTO bean=new CourseDTO();
		bean.setId(id);
		bean.setName(name);
		bean.setAbout(about);
		bean.setInstructor(instructor);
		bean.setLanguage(language);
		bean.setDescription(description);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		CourseDTO bean=(CourseDTO) bdto;
		id=bean.getId();
		about=bean.getAbout();
		instructor=bean.getInstructor();
		language=bean.getLanguage();
		name=bean.getName();
		about=bean.getAbout();
		description=bean.getDescription();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
