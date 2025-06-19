package in.co.elearning.dao;

import java.util.List;


import in.co.elearning.dto.ContactUsDTO;


public interface ContactUsDAOInt {

	public long add(ContactUsDTO dto);
	
	public void delete(ContactUsDTO dto);
	
	public ContactUsDTO findBypk(long pk);
	
	public ContactUsDTO findByName(String name);
	
	public void update(ContactUsDTO dto);
	
	public List<ContactUsDTO> list();
	
	public List<ContactUsDTO>list(int pageNo,int pageSize);
	
	public List<ContactUsDTO> search(ContactUsDTO dto);
	
	public List<ContactUsDTO> search(ContactUsDTO dto,int pageNo,int pageSize);
	
	
	
}
