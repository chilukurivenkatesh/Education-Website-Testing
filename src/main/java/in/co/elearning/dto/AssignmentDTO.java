package in.co.elearning.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="assignment")
@Getter
@Setter
public class AssignmentDTO extends BaseDTO{
	
	@Column(name="title",length = 225)
	private String title;
	
	@Column(name="description",length = 755)
	private String description;
	
	@Column(name="status",length = 755)
	private String status;
	
	@Column(name="afile",columnDefinition ="LONGBLOB")
	private byte[] afile;
	
	@Column(name="ansAssignment",columnDefinition ="LONGBLOB")
	private byte[] ansAssignment;

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
