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

import in.co.elearning.dao.EnrollDAOInt;
import in.co.elearning.dto.EnrollDTO;
import in.co.elearning.exception.DuplicateRecordException;

@Service
public class EnrollServiceImpl implements EnrollServiceInt {

	private static Logger log = Logger.getLogger(EnrollServiceImpl.class.getName());

	@Autowired
	private EnrollDAOInt dao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	@Transactional
	public long add(EnrollDTO dto) throws DuplicateRecordException {
		log.info("EnrollServiceImpl Add method start");

		long pk = dao.add(dto);
		log.info("EnrollServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(EnrollDTO dto) {
		log.info("EnrollServiceImpl Delete method start");
		dao.delete(dto);
		log.info("EnrollServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public EnrollDTO findBypk(long pk) {
		log.info("EnrollServiceImpl findBypk method start");
		EnrollDTO dto = dao.findBypk(pk);
		log.info("EnrollServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public EnrollDTO findByName(String name) {
		log.info("EnrollServiceImpl findByEnrollName method start");
		EnrollDTO dto = dao.findByName(name);
		log.info("EnrollServiceImpl findByEnrollName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(EnrollDTO dto) throws DuplicateRecordException {
		log.info("EnrollServiceImpl update method start");
		dao.update(dto);
		log.info("EnrollServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<EnrollDTO> list() {
		log.info("EnrollServiceImpl list method start");
		List<EnrollDTO> list = dao.list();
		log.info("EnrollServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<EnrollDTO> list(int pageNo, int pageSize) {
		log.info("EnrollServiceImpl list method start");
		List<EnrollDTO> list = dao.list(pageNo, pageSize);
		log.info("EnrollServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<EnrollDTO> search(EnrollDTO dto) {
		log.info("EnrollServiceImpl search method start");
		List<EnrollDTO> list = dao.search(dto);
		log.info("EnrollServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<EnrollDTO> search(EnrollDTO dto, int pageNo, int pageSize) {
		log.info("EnrollServiceImpl search method start");
		List<EnrollDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("EnrollServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public EnrollDTO findByCourseIdAndUserId(long courseId, long userId) {
		// TODO Auto-generated method stub
		return dao.findByCourseIdAndUserId(courseId, userId);
	}




}
