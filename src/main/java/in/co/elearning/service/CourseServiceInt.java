package in.co.elearning.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.elearning.dto.CourseDTO;
import in.co.elearning.exception.DuplicateRecordException;


public interface CourseServiceInt {

	public long add(CourseDTO dto) throws DuplicateRecordException;

	public void delete(CourseDTO dto);

	public CourseDTO findBypk(long pk);

	public CourseDTO findByName(String name);

	public void update(CourseDTO dto) throws DuplicateRecordException;

	public List<CourseDTO> list();

	public List<CourseDTO> list(int pageNo, int pageSize);

	public List<CourseDTO> search(CourseDTO dto);

	public List<CourseDTO> search(CourseDTO dto, int pageNo, int pageSize);
	
	public Blob getImageById(long id) throws SerialException, SQLException;

}
