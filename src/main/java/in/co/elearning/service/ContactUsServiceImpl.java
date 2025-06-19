package in.co.elearning.service;

import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.elearning.dao.ContactUsDAOInt;
import in.co.elearning.dto.ContactUsDTO;
import in.co.elearning.exception.DuplicateRecordException;

@Service
public class ContactUsServiceImpl implements ContactUsServiceInt {

	private static Logger log = Logger.getLogger(ContactUsServiceImpl.class.getName());

	@Autowired
	private ContactUsDAOInt dao;


	@Override
	@Transactional
	public long add(ContactUsDTO dto) throws DuplicateRecordException {
		log.info("ContactUsServiceImpl Add method start");
		
		long pk = dao.add(dto);
		log.info("ContactUsServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(ContactUsDTO dto) {
		log.info("ContactUsServiceImpl Delete method start");
		dao.delete(dto);
		log.info("ContactUsServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public ContactUsDTO findBypk(long pk) {
		log.info("ContactUsServiceImpl findBypk method start");
		ContactUsDTO dto = dao.findBypk(pk);
		log.info("ContactUsServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public ContactUsDTO findByName(String name) {
		log.info("ContactUsServiceImpl findByContactUsName method start");
		ContactUsDTO dto = dao.findByName(name);
		log.info("ContactUsServiceImpl findByContactUsName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(ContactUsDTO dto) throws DuplicateRecordException {
		log.info("ContactUsServiceImpl update method start");
				dao.update(dto);
		log.info("ContactUsServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<ContactUsDTO> list() {
		log.info("ContactUsServiceImpl list method start");
		List<ContactUsDTO> list = dao.list();
		log.info("ContactUsServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ContactUsDTO> list(int pageNo, int pageSize) {
		log.info("ContactUsServiceImpl list method start");
		List<ContactUsDTO> list = dao.list(pageNo, pageSize);
		log.info("ContactUsServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ContactUsDTO> search(ContactUsDTO dto) {
		log.info("ContactUsServiceImpl search method start");
		List<ContactUsDTO> list = dao.search(dto);
		log.info("ContactUsServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<ContactUsDTO> search(ContactUsDTO dto, int pageNo, int pageSize) {
		log.info("ContactUsServiceImpl search method start");
		List<ContactUsDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("ContactUsServiceImpl search method end");
		return list;
	}
	
	


}
