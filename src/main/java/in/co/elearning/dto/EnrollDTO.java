package in.co.elearning.dto;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ENROLL")
@Getter
@Setter
public class EnrollDTO extends BaseDTO {
	
	private long courseId;
	private long userId;
	
	@ManyToOne
	@JoinColumn(name="COURSE",nullable = false)
	private CourseDTO course;
	@ManyToOne
	@JoinColumn(name="USER",nullable = false)
	private UserDTO user;
	

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
