package in.co.elearning.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CONTENT")
@Getter
@Setter
public class ContentDTO extends BaseDTO {
	
	@Column(name="TITLE",length = 225)
	private String title;
	
	@Column(name="VIDEO",columnDefinition ="LONGBLOB")
	private byte[] video;
	
	@Column(name="MATERIAL",columnDefinition ="LONGBLOB")
	private byte[] material;

	@ManyToOne
	@JoinColumn(name="COURSE",nullable = false)
	private CourseDTO course;
	
	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
