package com.ssh.sakila.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.sakila.pojo.FilmCategory;

/**
 * A data access object (DAO) providing persistence and search support for
 * FilmCategory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ssh.sakila.pojo.FilmCategory
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FilmCategoryDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FilmCategoryDAO.class);
	// property constants

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

	public void save(FilmCategory transientInstance) {
		log.debug("saving FilmCategory instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FilmCategory persistentInstance) {
		log.debug("deleting FilmCategory instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FilmCategory findById(com.ssh.sakila.pojo.FilmCategoryId id) {
		log.debug("getting FilmCategory instance with id: " + id);
		try {
			FilmCategory instance = (FilmCategory) getCurrentSession().get(
					"com.ssh.sakila.pojo.FilmCategory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<FilmCategory> findByExample(FilmCategory instance) {
		log.debug("finding FilmCategory instance by example");
		try {
			List<FilmCategory> results = (List<FilmCategory>) getCurrentSession()
					.createCriteria("com.ssh.sakila.pojo.FilmCategory")
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
		log.debug("finding FilmCategory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FilmCategory as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all FilmCategory instances");
		try {
			String queryString = "from FilmCategory";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FilmCategory merge(FilmCategory detachedInstance) {
		log.debug("merging FilmCategory instance");
		try {
			FilmCategory result = (FilmCategory) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FilmCategory instance) {
		log.debug("attaching dirty FilmCategory instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FilmCategory instance) {
		log.debug("attaching clean FilmCategory instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FilmCategoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (FilmCategoryDAO) ctx.getBean("FilmCategoryDAO");
	}
}