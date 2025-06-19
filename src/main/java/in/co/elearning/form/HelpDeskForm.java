package in.co.elearning.form;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import in.co.elearning.dto.BaseDTO;
import in.co.elearning.dto.ContentDTO;
import in.co.elearning.dto.CourseDTO;
import in.co.elearning.dto.HelpDeskDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelpDeskForm extends BaseForm {

	@NotEmpty(message = "Problem is required")
	private String problem;
	@NotEmpty(message = "Description is required")
	private String description;
	private String comment;
	

	@Override
	public BaseDTO getDTO() {
		HelpDeskDTO bean=new HelpDeskDTO();
		bean.setId(id);
		bean.setProblem(problem);
		bean.setDescription(description);
		bean.setComment(comment);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		HelpDeskDTO bean=(HelpDeskDTO) bdto;
		id=bean.getId();
		problem=bean.getProblem();
		description=bean.getDescription();
		comment=bean.getComment();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
