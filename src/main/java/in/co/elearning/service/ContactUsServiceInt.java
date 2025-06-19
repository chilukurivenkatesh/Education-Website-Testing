package in.co.elearning.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.elearning.dto.ContactUsDTO;
import in.co.elearning.exception.DuplicateRecordException;


public interface ContactUsServiceInt {

	public long add(ContactUsDTO dto) throws DuplicateRecordException;

	public void delete(ContactUsDTO dto);

	public ContactUsDTO findBypk(long pk);

	public ContactUsDTO findByName(String name);

	public void update(ContactUsDTO dto) throws DuplicateRecordException;

	public List<ContactUsDTO> list();

	public List<ContactUsDTO> list(int pageNo, int pageSize);

	public List<ContactUsDTO> search(ContactUsDTO dto);

	public List<ContactUsDTO> search(ContactUsDTO dto, int pageNo, int pageSize);
	

}
