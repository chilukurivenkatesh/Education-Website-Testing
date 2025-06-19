package in.co.elearning.dto;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class UserDTO extends BaseDTO {

	@Column(name = "FIRST_NAME", length = 225)
	private String firstName;

	@Column(name = "LAST_NAME", length = 225)
	private String lastName;

	@Column(name = "LOGIN" ,length = 225)
	private String login;

	@Column(name = "PASSWORD", length = 225)
	private String password;

	@Transient
	private String confirmPassword;

	@Column(name = "ROLE_ID", length = 225)
	private long roleId;

	@Column(name = "EMAIL_ID", length = 225)
	private String emailId;
	
	@Column(name = "CONTACT_NO", length = 225)
	private String contactNo;
	
	@Column(name = "GENDER", length = 225)
	private String gender;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
	private Set<EnrollDTO> enrolls;
	
	
	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return firstName + " " + lastName;
	}

}
