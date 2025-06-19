package in.co.elearning.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="HELP_DESK")
@Getter
@Setter
public class HelpDeskDTO extends BaseDTO {
	
	@Column(name="PROBLEM",length = 755)
	private String problem;
	@Column(name="DESCRIPTION",length = 1500)
	private String description;
	@Column(name="COMMENT",length = 1500)
	private String comment;
	
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
