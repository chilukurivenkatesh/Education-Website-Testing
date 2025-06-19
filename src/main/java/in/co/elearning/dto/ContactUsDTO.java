package in.co.elearning.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CONTACT_US")
@Getter
@Setter
public class ContactUsDTO extends BaseDTO {

	@Column(name="NAME",length = 225)
	private String name;
	@Column(name="EMAIL",length = 225)
	private String email;
	@Column(name="MESSAGE",length = 225)
	private String message;
	
	

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
