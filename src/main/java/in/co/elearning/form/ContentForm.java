package in.co.elearning.form;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import in.co.elearning.dto.BaseDTO;
import in.co.elearning.dto.ContentDTO;
import in.co.elearning.dto.CourseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentForm extends BaseForm {

	@NotEmpty(message = "Title is required")
	private String title;

	private MultipartFile video;
	private MultipartFile material;


	@Override
	public BaseDTO getDTO() {
		ContentDTO bean=new ContentDTO();
		bean.setId(id);
		bean.setTitle(title);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		ContentDTO bean=(ContentDTO) bdto;
		id=bean.getId();
		title=bean.getTitle();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
