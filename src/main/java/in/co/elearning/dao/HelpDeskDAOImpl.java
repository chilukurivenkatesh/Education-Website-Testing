package in.co.elearning.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.elearning.dto.HelpDeskDTO;

@Repository
public class HelpDeskDAOImpl implements HelpDeskDAOInt {

	private static Logger log = Logger.getLogger(HelpDeskDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(HelpDeskDTO dto) {
		log.info("HelpDeskDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);

		log.info("HelpDeskDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(HelpDeskDTO dto) {
		log.info("HelpDeskDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("HelpDeskDAOImpl Delete method End");

	}

	@Override
	public HelpDeskDTO findBypk(long pk) {
		log.info("HelpDeskDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		HelpDeskDTO dto = (HelpDeskDTO) session.get(HelpDeskDTO.class, pk);
		log.info("HelpDeskDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public HelpDeskDTO findByName(String name) {
		log.info("HelpDeskDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(HelpDeskDTO.class);
		criteria.add(Restrictions.eq("name", name));
		HelpDeskDTO dto = (HelpDeskDTO) criteria.uniqueResult();
		log.info("HelpDeskDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(HelpDeskDTO dto) {
		log.info("HelpDeskDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("HelpDeskDAOImpl update method End");
	}

	@Override
	public List<HelpDeskDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<HelpDeskDTO> list(int pageNo, int pageSize) {
		log.info("HelpDeskDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<HelpDeskDTO> query = session.createQuery("from HelpDeskDTO", HelpDeskDTO.class);
		List<HelpDeskDTO> list = query.getResultList();
		log.info("HelpDeskDAOImpl List method End");
		return list;
	}

	@Override
	public List<HelpDeskDTO> search(HelpDeskDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<HelpDeskDTO> search(HelpDeskDTO dto, int pageNo, int pageSize) {
		log.info("HelpDeskDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from HelpDeskDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}

			/*
			 * if (dto.getUserId() > 0) { hql.append("and u.userId = " + dto.getUserId()); }
			 *
			 * if (dto.getName() != null && dto.getName().length() > 0) {
			 * hql.append("and u.name like '%" + dto.getName() + "%'"); }
			 */

		}
		Query<HelpDeskDTO> query = session.createQuery(hql.toString(), HelpDeskDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<HelpDeskDTO> list = query.getResultList();
		log.info("HelpDeskDAOImpl Search method End");
		return list;
	}


}
