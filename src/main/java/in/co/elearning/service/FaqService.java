package in.co.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.co.elearning.dao.FaqDAO;
import in.co.elearning.dto.FaqDTO;
import in.co.elearning.exception.DuplicateRecordException;

@Service
public class FaqService {

	@Autowired
	private FaqDAO dao;

	public FaqDTO add(FaqDTO dto) throws DuplicateRecordException {

		FaqDTO existDTO = dao.findByQuestion(dto.getQuestion());
		if (existDTO != null)
			throw new DuplicateRecordException("Question Already Exist");
		FaqDTO faqDTO = dao.save(dto);
		return faqDTO;
	}

	public void delete(long id) {

		dao.deleteById(id);

	}

	public FaqDTO findBypk(long pk) {

		FaqDTO dto = dao.findById(pk);

		return dto;
	}

	public void update(FaqDTO dto) throws DuplicateRecordException {

		FaqDTO existDTO = dao.findByQuestion(dto.getQuestion());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Name Already Exist");
		dao.saveAndFlush(dto);

	}

	public List<FaqDTO> list() {

		List<FaqDTO> list = dao.findAll();

		return list;
	}

}
