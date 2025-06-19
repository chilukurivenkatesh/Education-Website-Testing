package in.co.elearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.elearning.dto.AssignmentDTO;
import in.co.elearning.dto.FaqDTO;

public interface FaqDAO extends JpaRepository<FaqDTO, Long>{
	
	public FaqDTO findByQuestion(String question);
	public FaqDTO findById(long id);

}
