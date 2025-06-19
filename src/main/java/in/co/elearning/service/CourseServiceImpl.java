package in.co.elearning.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.elearning.dao.CourseDAOInt;
import in.co.elearning.dto.CourseDTO;
import in.co.elearning.exception.DuplicateRecordException;

@Service
public class CourseServiceImpl implements CourseServiceInt {

	private static Logger log = Logger.getLogger(CourseServiceImpl.class.getName());

	@Autowired
	private CourseDAOInt dao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	@Transactional
	public long add(CourseDTO dto) throws DuplicateRecordException {
		log.info("CourseServiceImpl Add method start");
		CourseDTO existDTO = dao.findByName(dto.getName());
		if (existDTO != null)
			throw new DuplicateRecordException("Name Already Exist");
		long pk = dao.add(dto);
		log.info("CourseServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(CourseDTO dto) {
		log.info("CourseServiceImpl Delete method start");
		dao.delete(dto);
		log.info("CourseServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public CourseDTO findBypk(long pk) {
		log.info("CourseServiceImpl findBypk method start");
		CourseDTO dto = dao.findBypk(pk);
		log.info("CourseServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public CourseDTO findByName(String name) {
		log.info("CourseServiceImpl findByCourseName method start");
		CourseDTO dto = dao.findByName(name);
		log.info("CourseServiceImpl findByCourseName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(CourseDTO dto) throws DuplicateRecordException {
		log.info("CourseServiceImpl update method start");
		CourseDTO existDTO = dao.findByName(dto.getName());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Name Already Exist");
		dao.update(dto);
		log.info("CourseServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<CourseDTO> list() {
		log.info("CourseServiceImpl list method start");
		List<CourseDTO> list = dao.list();
		log.info("CourseServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<CourseDTO> list(int pageNo, int pageSize) {
		log.info("CourseServiceImpl list method start");
		List<CourseDTO> list = dao.list(pageNo, pageSize);
		log.info("CourseServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<CourseDTO> search(CourseDTO dto) {
		log.info("CourseServiceImpl search method start");
		List<CourseDTO> list = dao.search(dto);
		log.info("CourseServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<CourseDTO> search(CourseDTO dto, int pageNo, int pageSize) {
		log.info("CourseServiceImpl search method start");
		List<CourseDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("CourseServiceImpl search method end");
		return list;
	}
	
	@Override
	@Transactional
	public Blob getImageById(long id) throws SerialException, SQLException {
		return dao.getImageById(id);
	}


}
