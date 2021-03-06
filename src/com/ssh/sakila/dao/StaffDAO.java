package com.ssh.sakila.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.sakila.pojo.Staff;

/**
 * A data access object (DAO) providing persistence and search support for Staff
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.ssh.sakila.pojo.Staff
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class StaffDAO {
	private static final Logger log = LoggerFactory.getLogger(StaffDAO.class);
	// property constants
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String PICTURE = "picture";
	public static final String EMAIL = "email";
	public static final String ACTIVE = "active";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Staff transientInstance) {
		log.debug("saving Staff instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Staff persistentInstance) {
		log.debug("deleting Staff instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Staff findById(java.lang.Short id) {
		log.debug("getting Staff instance with id: " + id);
		try {
			Staff instance = (Staff) getCurrentSession().get(
					"com.ssh.sakila.pojo.Staff", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Staff> findByExample(Staff instance) {
		log.debug("finding Staff instance by example");
		try {
			List<Staff> results = (List<Staff>) getCurrentSession()
					.createCriteria("com.ssh.sakila.pojo.Staff")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Staff instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Staff as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Staff> findByFirstName(Object firstName) {
		return findByProperty(FIRST_NAME, firstName);
	}

	public List<Staff> findByLastName(Object lastName) {
		return findByProperty(LAST_NAME, lastName);
	}

	public List<Staff> findByPicture(Object picture) {
		return findByProperty(PICTURE, picture);
	}

	public List<Staff> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<Staff> findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List<Staff> findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List<Staff> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findAll() {
		log.debug("finding all Staff instances");
		try {
			String queryString = "from Staff";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Staff merge(Staff detachedInstance) {
		log.debug("merging Staff instance");
		try {
			Staff result = (Staff) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Staff instance) {
		log.debug("attaching dirty Staff instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Staff instance) {
		log.debug("attaching clean Staff instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StaffDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StaffDAO) ctx.getBean("StaffDAO");
	}
}