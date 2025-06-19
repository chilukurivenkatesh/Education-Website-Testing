package in.co.elearning.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.elearning.dto.ContentDTO;


public interface ContentDAOInt {

	public long add(ContentDTO dto);
	
	public void delete(ContentDTO dto);
	
	public ContentDTO findBypk(long pk);
	
	public ContentDTO findByName(String name);
	
	public void update(ContentDTO dto);
	
	public List<ContentDTO> list();
	
	public List<ContentDTO>list(int pageNo,int pageSize);
	
	public List<ContentDTO> search(ContentDTO dto);
	
	public List<ContentDTO> search(ContentDTO dto,int pageNo,int pageSize);
	
	public Blob getVideoById(long id) throws SerialException, SQLException;
	
	public Blob getMaterialById(long id) throws SerialException, SQLException;
	
	
}
