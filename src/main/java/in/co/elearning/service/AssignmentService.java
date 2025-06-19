package in.co.elearning.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import in.co.elearning.dao.AssignmentDAO;
import in.co.elearning.dto.AssignmentDTO;
import in.co.elearning.exception.DuplicateRecordException;

@Service
public class AssignmentService {

	@Autowired
	private AssignmentDAO dao;

	public AssignmentDTO add(AssignmentDTO dto) throws DuplicateRecordException {

		AssignmentDTO existDTO = dao.findByTitle(dto.getTitle());
		if (existDTO != null)
			throw new DuplicateRecordException("Name Already Exist");
		AssignmentDTO assignmentDTO = dao.save(dto);
		return assignmentDTO;
	}

	public void delete(long id) {

		dao.deleteById(id);

	}

	public AssignmentDTO findBypk(long pk) {

		AssignmentDTO dto = dao.findById(pk);

		return dto;
	}

	public void update(AssignmentDTO dto) throws DuplicateRecordException {

		AssignmentDTO existDTO = dao.findByTitle(dto.getTitle());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Name Already Exist");
		dao.saveAndFlush(dto);

	}

	public List<AssignmentDTO> list() {

		List<AssignmentDTO> list = dao.findAll();

		return list;
	}
	
	
	public void submitAssignment(AssignmentDTO dto) throws DuplicateRecordException {
		//AssignmentDTO existDTO = dao.findById(dto.getId());
		dao.saveAndFlush(dto);

	}


	public Blob getAfileById(long id) throws SerialException, SQLException {		
		AssignmentDTO assignmentDTO = dao.findById(id);
		byte[] blob = assignmentDTO.getAfile();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}
	
	public Blob getSubmitfileById(long id) throws SerialException, SQLException {		
		AssignmentDTO assignmentDTO = dao.findById(id);
		byte[] blob = assignmentDTO.getAnsAssignment();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}
	
	
}
