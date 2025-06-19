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

import in.co.elearning.dto.EnrollDTO;

@Repository
public class EnrollDAOImpl implements EnrollDAOInt {

	private static Logger log = Logger.getLogger(EnrollDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(EnrollDTO dto) {
		log.info("EnrollDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("EnrollDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(EnrollDTO dto) {
		log.info("EnrollDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("EnrollDAOImpl Delete method End");

	}

	@Override
	public EnrollDTO findBypk(long pk) {
		log.info("EnrollDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		EnrollDTO dto = (EnrollDTO) session.get(EnrollDTO.class, pk);
		log.info("EnrollDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public EnrollDTO findByName(String name) {
		log.info("EnrollDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(EnrollDTO.class);
		criteria.add(Restrictions.eq("name", name));
		EnrollDTO dto = (EnrollDTO) criteria.uniqueResult();
		log.info("EnrollDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public EnrollDTO findByCourseIdAndUserId(long courseId, long userId) {
		log.info("EnrollDAOImpl findByCourseIdAndUserId method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(EnrollDTO.class);
		criteria.add(Restrictions.eq("courseId", courseId));
		criteria.add(Restrictions.eq("userId",userId));
		EnrollDTO dto = (EnrollDTO) criteria.uniqueResult();
		log.info("EnrollDAOImpl findByCourseIdAndUserId method End");
		return dto;
	}

	@Override
	public void update(EnrollDTO dto) {
		log.info("EnrollDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("EnrollDAOImpl update method End");
	}

	@Override
	public List<EnrollDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<EnrollDTO> list(int pageNo, int pageSize) {
		log.info("EnrollDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<EnrollDTO> query = session.createQuery("from EnrollDTO", EnrollDTO.class);
		List<EnrollDTO> list = query.getResultList();
		log.info("EnrollDAOImpl List method End");
		return list;
	}

	@Override
	public List<EnrollDTO> search(EnrollDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<EnrollDTO> search(EnrollDTO dto, int pageNo, int pageSize) {
		log.info("EnrollDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from EnrollDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}

			if (dto.getUserId() > 0) {
				hql.append("and u.userId = " + dto.getUserId());
			}


		}
		Query<EnrollDTO> query = session.createQuery(hql.toString(), EnrollDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<EnrollDTO> list = query.getResultList();
		log.info("EnrollDAOImpl Search method End");
		return list;
	}



}
