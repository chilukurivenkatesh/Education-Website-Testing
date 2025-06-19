package in.co.elearning.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.elearning.dto.EnrollDTO;
import in.co.elearning.exception.DuplicateRecordException;


public interface EnrollServiceInt {

	public long add(EnrollDTO dto) throws DuplicateRecordException;

	public void delete(EnrollDTO dto);

	public EnrollDTO findBypk(long pk);

	public EnrollDTO findByName(String name);

	public void update(EnrollDTO dto) throws DuplicateRecordException;

	public List<EnrollDTO> list();

	public List<EnrollDTO> list(int pageNo, int pageSize);

	public List<EnrollDTO> search(EnrollDTO dto);

	public List<EnrollDTO> search(EnrollDTO dto, int pageNo, int pageSize);
	
	public EnrollDTO findByCourseIdAndUserId(long courseId, long userId);
	

}
