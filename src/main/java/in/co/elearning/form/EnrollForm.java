package in.co.elearning.form;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import in.co.elearning.dto.BaseDTO;
import in.co.elearning.dto.CourseDTO;
import in.co.elearning.dto.EnrollDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollForm extends BaseForm {

	private long userId;
	private long courseId;
	

	@Override
	public BaseDTO getDTO() {
		EnrollDTO bean=new EnrollDTO();
		bean.setId(id);
		bean.setUserId(userId);
		bean.setCourseId(courseId);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
	  EnrollDTO	bean=(EnrollDTO) bdto;
		id=bean.getId();
		userId=bean.getUserId();
		courseId=bean.getCourseId();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
