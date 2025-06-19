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

import in.co.elearning.dto.ContentDTO;

@Repository
public class ContentDAOImpl implements ContentDAOInt {

	private static Logger log = Logger.getLogger(ContentDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(ContentDTO dto) {
		log.info("ContentDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("ContentDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(ContentDTO dto) {
		log.info("ContentDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("ContentDAOImpl Delete method End");

	}

	@Override
	public ContentDTO findBypk(long pk) {
		log.info("ContentDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		ContentDTO dto = (ContentDTO) session.get(ContentDTO.class, pk);
		log.info("ContentDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public ContentDTO findByName(String name) {
		log.info("ContentDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ContentDTO.class);
		criteria.add(Restrictions.eq("title", name));
		ContentDTO dto = (ContentDTO) criteria.uniqueResult();
		log.info("ContentDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(ContentDTO dto) {
		log.info("ContentDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("ContentDAOImpl update method End");
	}

	@Override
	public List<ContentDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<ContentDTO> list(int pageNo, int pageSize) {
		log.info("ContentDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<ContentDTO> query = session.createQuery("from ContentDTO", ContentDTO.class);
		List<ContentDTO> list = query.getResultList();
		log.info("ContentDAOImpl List method End");
		return list;
	}

	@Override
	public List<ContentDTO> search(ContentDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<ContentDTO> search(ContentDTO dto, int pageNo, int pageSize) {
		log.info("ContentDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from ContentDTO as u where 1=1 ");
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
		Query<ContentDTO> query = session.createQuery(hql.toString(), ContentDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<ContentDTO> list = query.getResultList();
		log.info("ContentDAOImpl Search method End");
		return list;
	}

	@Override
	public Blob getVideoById(long id) throws SerialException, SQLException {

		Session session = entityManager.unwrap(Session.class);
		ContentDTO person = (ContentDTO) session.get(ContentDTO.class, id);
		byte[] blob = person.getVideo();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}

	@Override
	public Blob getMaterialById(long id) throws SerialException, SQLException {
		Session session = entityManager.unwrap(Session.class);
		ContentDTO person = (ContentDTO) session.get(ContentDTO.class, id);
		byte[] blob = person.getMaterial();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}

}
