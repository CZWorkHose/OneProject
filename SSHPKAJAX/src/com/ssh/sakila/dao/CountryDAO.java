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

import com.ssh.sakila.pojo.Country;

/**
 * A data access object (DAO) providing persistence and search support for
 * Country entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ssh.sakila.pojo.Country
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CountryDAO {
	private static final Logger log = LoggerFactory.getLogger(CountryDAO.class);
	// property constants
	public static final String COUNTRY = "country";

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

	public void save(Country transientInstance) {
		log.debug("saving Country instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Country persistentInstance) {
		log.debug("deleting Country instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Country findById(java.lang.Short id) {
		log.debug("getting Country instance with id: " + id);
		try {
			Country instance = (Country) getCurrentSession().get(
					"com.ssh.sakila.pojo.Country", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Country> findByExample(Country instance) {
		log.debug("finding Country instance by example");
		try {
			List<Country> results = (List<Country>) getCurrentSession()
					.createCriteria("com.ssh.sakila.pojo.Country")
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
		log.debug("finding Country instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Country as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Country> findByCountry(Object country) {
		return findByProperty(COUNTRY, country);
	}

	public List findAll() {
		log.debug("finding all Country instances");
		try {
			String queryString = "from Country";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Country merge(Country detachedInstance) {
		log.debug("merging Country instance");
		try {
			Country result = (Country) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Country instance) {
		log.debug("attaching dirty Country instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Country instance) {
		log.debug("attaching clean Country instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CountryDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CountryDAO) ctx.getBean("CountryDAO");
	}
}