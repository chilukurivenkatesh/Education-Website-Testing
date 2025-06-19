package in.co.elearning.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.elearning.dto.HelpDeskDTO;
import in.co.elearning.exception.DuplicateRecordException;


public interface HelpDeskServiceInt {

	public long add(HelpDeskDTO dto) throws DuplicateRecordException;

	public void delete(HelpDeskDTO dto);

	public HelpDeskDTO findBypk(long pk);

	public HelpDeskDTO findByName(String name);

	public void update(HelpDeskDTO dto) throws DuplicateRecordException;

	public List<HelpDeskDTO> list();

	public List<HelpDeskDTO> list(int pageNo, int pageSize);

	public List<HelpDeskDTO> search(HelpDeskDTO dto);

	public List<HelpDeskDTO> search(HelpDeskDTO dto, int pageNo, int pageSize);
	

}
