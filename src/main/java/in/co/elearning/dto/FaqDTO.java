package in.co.elearning.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="faq")
@Getter
@Setter
public class FaqDTO extends BaseDTO {
	
	@Column(name="question",length = 225)
	private String question;
	
	@Column(name="ans",length = 1500)
	private String ans;

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
