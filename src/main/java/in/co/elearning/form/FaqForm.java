package in.co.elearning.form;

import javax.validation.constraints.NotEmpty;

import in.co.elearning.dto.BaseDTO;
import in.co.elearning.dto.FaqDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaqForm extends BaseForm {

	@NotEmpty(message = "Question is required")
	private String question;
	
	@NotEmpty(message = "Ans is required")
	private String ans;

	@Override
	public BaseDTO getDTO() {
		FaqDTO bean = new FaqDTO();
		bean.setId(id);
		bean.setQuestion(question);
		bean.setAns(ans);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		FaqDTO bean=(FaqDTO)bDto;
		id=bean.getId();
	    question  = bean.getQuestion();
	    ans = bean.getAns();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();	
		
	}

}
