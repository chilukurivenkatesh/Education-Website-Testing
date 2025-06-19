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

import in.co.elearning.dao.ContentDAOInt;
import in.co.elearning.dto.ContentDTO;
import in.co.elearning.exception.DuplicateRecordException;

@Service
public class ContentServiceImpl implements ContentServiceInt {

	private static Logger log = Logger.getLogger(ContentServiceImpl.class.getName());

	@Autowired
	private ContentDAOInt dao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	@Transactional
	public long add(ContentDTO dto) throws DuplicateRecordException {
		log.info("ContentServiceImpl Add method start");
		ContentDTO existDTO = dao.findByName(dto.getTitle());
		if (existDTO != null)
			throw new DuplicateRecordException("Name Already Exist");
		long pk = dao.add(dto);
		log.info("ContentServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(ContentDTO dto) {
		log.info("ContentServiceImpl Delete method start");
		dao.delete(dto);
		log.info("ContentServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public ContentDTO findBypk(long pk) {
		log.info("ContentServiceImpl findBypk method start");
		ContentDTO dto = dao.findBypk(pk);
		log.info("ContentServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public ContentDTO findByName(String name) {
		log.info("ContentServiceImpl findByContentName method start");
		ContentDTO dto = dao.findByName(name);
		log.info("ContentServiceImpl findByContentName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(ContentDTO dto) throws DuplicateRecordException {
		log.info("ContentServiceImpl update method start");
		ContentDTO existDTO = dao.findByName(dto.getTitle());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("Name Already Exist");
		dao.update(dto);
		log.info("ContentServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<ContentDTO> list() {
		log.info("ContentServiceImpl list method start");
		List<ContentDTO> list = dao.list();
		log.info("ContentServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ContentDTO> list(int pageNo, int pageSize) {
		log.info("ContentServiceImpl list method start");
		List<ContentDTO> list = dao.list(pageNo, pageSize);
		log.info("ContentServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ContentDTO> search(ContentDTO dto) {
		log.info("ContentServiceImpl search method start");
		List<ContentDTO> list = dao.search(dto);
		log.info("ContentServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<ContentDTO> search(ContentDTO dto, int pageNo, int pageSize) {
		log.info("ContentServiceImpl search method start");
		List<ContentDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("ContentServiceImpl search method end");
		return list;
	}
	
	@Override
	@Transactional
	public Blob getVideoById(long id) throws SerialException, SQLException {
		return dao.getVideoById(id);
	}
	
	@Override
	@Transactional
	public Blob getMaterialById(long id) throws SerialException, SQLException {
		return dao.getMaterialById(id);
	}


}
