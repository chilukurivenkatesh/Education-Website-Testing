package in.co.elearning.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.elearning.dto.EnrollDTO;


public interface EnrollDAOInt {

	public long add(EnrollDTO dto);
	
	public void delete(EnrollDTO dto);
	
	public EnrollDTO findBypk(long pk);
	
	public EnrollDTO findByName(String name);
	
	public EnrollDTO findByCourseIdAndUserId(long courseId,long userId);
	
	public void update(EnrollDTO dto);
	
	public List<EnrollDTO> list();
	
	public List<EnrollDTO>list(int pageNo,int pageSize);
	
	public List<EnrollDTO> search(EnrollDTO dto);
	
	public List<EnrollDTO> search(EnrollDTO dto,int pageNo,int pageSize);
	
	
	
}
