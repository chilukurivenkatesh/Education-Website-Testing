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

import in.co.elearning.dao.HelpDeskDAOInt;
import in.co.elearning.dto.HelpDeskDTO;
import in.co.elearning.exception.DuplicateRecordException;

@Service
public class HelpDeskServiceImpl implements HelpDeskServiceInt {

	private static Logger log = Logger.getLogger(HelpDeskServiceImpl.class.getName());

	@Autowired
	private HelpDeskDAOInt dao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	@Transactional
	public long add(HelpDeskDTO dto) throws DuplicateRecordException {
		log.info("HelpDeskServiceImpl Add method start");
		
		long pk = dao.add(dto);
		log.info("HelpDeskServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(HelpDeskDTO dto) {
		log.info("HelpDeskServiceImpl Delete method start");
		dao.delete(dto);
		log.info("HelpDeskServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public HelpDeskDTO findBypk(long pk) {
		log.info("HelpDeskServiceImpl findBypk method start");
		HelpDeskDTO dto = dao.findBypk(pk);
		log.info("HelpDeskServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public HelpDeskDTO findByName(String name) {
		log.info("HelpDeskServiceImpl findByHelpDeskName method start");
		HelpDeskDTO dto = dao.findByName(name);
		log.info("HelpDeskServiceImpl findByHelpDeskName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(HelpDeskDTO dto) throws DuplicateRecordException {
		log.info("HelpDeskServiceImpl update method start");
				dao.update(dto);
		log.info("HelpDeskServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<HelpDeskDTO> list() {
		log.info("HelpDeskServiceImpl list method start");
		List<HelpDeskDTO> list = dao.list();
		log.info("HelpDeskServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<HelpDeskDTO> list(int pageNo, int pageSize) {
		log.info("HelpDeskServiceImpl list method start");
		List<HelpDeskDTO> list = dao.list(pageNo, pageSize);
		log.info("HelpDeskServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<HelpDeskDTO> search(HelpDeskDTO dto) {
		log.info("HelpDeskServiceImpl search method start");
		List<HelpDeskDTO> list = dao.search(dto);
		log.info("HelpDeskServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<HelpDeskDTO> search(HelpDeskDTO dto, int pageNo, int pageSize) {
		log.info("HelpDeskServiceImpl search method start");
		List<HelpDeskDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("HelpDeskServiceImpl search method end");
		return list;
	}
	
	


}
