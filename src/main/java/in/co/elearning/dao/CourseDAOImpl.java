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

import in.co.elearning.dto.CourseDTO;

@Repository
public class CourseDAOImpl implements CourseDAOInt {

	private static Logger log = Logger.getLogger(CourseDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(CourseDTO dto) {
		log.info("CourseDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("CourseDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(CourseDTO dto) {
		log.info("CourseDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("CourseDAOImpl Delete method End");

	}

	@Override
	public CourseDTO findBypk(long pk) {
		log.info("CourseDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		CourseDTO dto = (CourseDTO) session.get(CourseDTO.class, pk);
		log.info("CourseDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public CourseDTO findByName(String name) {
		log.info("CourseDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(CourseDTO.class);
		criteria.add(Restrictions.eq("name", name));
		CourseDTO dto = (CourseDTO) criteria.uniqueResult();
		log.info("CourseDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(CourseDTO dto) {
		log.info("CourseDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("CourseDAOImpl update method End");
	}

	@Override
	public List<CourseDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<CourseDTO> list(int pageNo, int pageSize) {
		log.info("CourseDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<CourseDTO> query = session.createQuery("from CourseDTO", CourseDTO.class);
		List<CourseDTO> list = query.getResultList();
		log.info("CourseDAOImpl List method End");
		return list;
	}

	@Override
	public List<CourseDTO> search(CourseDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<CourseDTO> search(CourseDTO dto, int pageNo, int pageSize) {
		log.info("CourseDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from CourseDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}

			if (dto.getUserId() > 0) {
				hql.append("and u.userId = " + dto.getUserId());
			}

			if (dto.getName() != null && dto.getName().length() > 0) {
				hql.append("and u.name like '%" + dto.getName() + "%'");
			}

		}
		Query<CourseDTO> query = session.createQuery(hql.toString(), CourseDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<CourseDTO> list = query.getResultList();
		log.info("CourseDAOImpl Search method End");
		return list;
	}

	@Override
	public Blob getImageById(long id) throws SerialException, SQLException {

		Session session = entityManager.unwrap(Session.class);
		CourseDTO person = (CourseDTO) session.get(CourseDTO.class, id);
		byte[] blob = person.getImage();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}

}
