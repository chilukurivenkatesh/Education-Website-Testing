package in.co.elearning.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.elearning.dto.ContactUsDTO;

@Repository
public class ContactUsDAOImpl implements ContactUsDAOInt {

	private static Logger log = Logger.getLogger(ContactUsDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(ContactUsDTO dto) {
		log.info("ContactUsDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("ContactUsDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(ContactUsDTO dto) {
		log.info("ContactUsDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("ContactUsDAOImpl Delete method End");

	}

	@Override
	public ContactUsDTO findBypk(long pk) {
		log.info("ContactUsDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		ContactUsDTO dto = (ContactUsDTO) session.get(ContactUsDTO.class, pk);
		log.info("ContactUsDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public ContactUsDTO findByName(String name) {
		log.info("ContactUsDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ContactUsDTO.class);
		criteria.add(Restrictions.eq("name", name));
		ContactUsDTO dto = (ContactUsDTO) criteria.uniqueResult();
		log.info("ContactUsDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(ContactUsDTO dto) {
		log.info("ContactUsDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("ContactUsDAOImpl update method End");
	}

	@Override
	public List<ContactUsDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<ContactUsDTO> list(int pageNo, int pageSize) {
		log.info("ContactUsDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<ContactUsDTO> query = session.createQuery("from ContactUsDTO", ContactUsDTO.class);
		List<ContactUsDTO> list = query.getResultList();
		log.info("ContactUsDAOImpl List method End");
		return list;
	}

	@Override
	public List<ContactUsDTO> search(ContactUsDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<ContactUsDTO> search(ContactUsDTO dto, int pageNo, int pageSize) {
		log.info("ContactUsDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from ContactUsDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}

			if (dto.getName() != null && dto.getName().length() > 0) {
				hql.append("and u.name like '%" + dto.getName() + "%'");
			}

		}
		Query<ContactUsDTO> query = session.createQuery(hql.toString(), ContactUsDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<ContactUsDTO> list = query.getResultList();
		log.info("ContactUsDAOImpl Search method End");
		return list;
	}

}
