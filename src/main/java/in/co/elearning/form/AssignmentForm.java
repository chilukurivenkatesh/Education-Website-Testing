package in.co.elearning.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import in.co.elearning.dto.AssignmentDTO;
import in.co.elearning.dto.BaseDTO;
import in.co.elearning.dto.ContentDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentForm extends BaseForm{
	
	@NotEmpty(message = "Title is required")
	private String title;
	
	@NotEmpty(message = "Description is required")
	private String description;

	private MultipartFile afile;
	
	private MultipartFile ansAssignment;

	@Override
	public BaseDTO getDTO() {
		AssignmentDTO bean=new AssignmentDTO();
		bean.setId(id);
		bean.setTitle(title);
		bean.setDescription(description);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		AssignmentDTO bean=(AssignmentDTO)bDto;
		id=bean.getId();
		title=bean.getTitle();
		description= bean.getDescription();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();		
	}

}
