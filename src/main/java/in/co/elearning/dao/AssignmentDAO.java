package in.co.elearning.dao;

import java.sql.Blob;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.elearning.dto.AssignmentDTO;

public interface AssignmentDAO extends JpaRepository<AssignmentDTO, Long> {
	
	public AssignmentDTO findByTitle(String title);
	public AssignmentDTO findById(long id);
	public AssignmentDTO findAfileById(long id);
	
	
	

}
