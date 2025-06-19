package in.co.elearning.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="COURSE")
@Getter
@Setter
public class CourseDTO extends BaseDTO {
	
	@Column(name="NAME",length = 225)
	private String name;
	@Column(name="DESCRIPTION",length = 1500)
	private String description;
	@Column(name="INSTRUCTOR",length = 225)
	private String instructor;
	@Column(name="LANGUAGE",length = 225)
	private String language;
	
	@Column(name="ABOUT_COURSE",length = 2000)
	private String about;
	
	@Column(name="IMAGE",columnDefinition = "LONGBLOB")
	private byte[] image;
	
	@Column(name="USER_ID")
	private long userId;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
	private Set<ContentDTO> contents;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
	private Set<EnrollDTO> enrolls;
	
	
	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
