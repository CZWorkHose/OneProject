package com.ssh.sakila.dao;

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

import com.ssh.sakila.pojo.FilmText;

/**
 * A data access object (DAO) providing persistence and search support for
 * FilmText entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ssh.sakila.pojo.FilmText
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FilmTextDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FilmTextDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";

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

	public void save(FilmText transientInstance) {
		log.debug("saving FilmText instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FilmText persistentInstance) {
		log.debug("deleting FilmText instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FilmText findById(java.lang.Short id) {
		log.debug("getting FilmText instance with id: " + id);
		try {
			FilmText instance = (FilmText) getCurrentSession().get(
					"com.ssh.sakila.pojo.FilmText", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<FilmText> findByExample(FilmText instance) {
		log.debug("finding FilmText instance by example");
		try {
			List<FilmText> results = (List<FilmText>) getCurrentSession()
					.createCriteria("com.ssh.sakila.pojo.FilmText")
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
		log.debug("finding FilmText instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from FilmText as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<FilmText> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<FilmText> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all FilmText instances");
		try {
			String queryString = "from FilmText";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FilmText merge(FilmText detachedInstance) {
		log.debug("merging FilmText instance");
		try {
			FilmText result = (FilmText) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FilmText instance) {
		log.debug("attaching dirty FilmText instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FilmText instance) {
		log.debug("attaching clean FilmText instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FilmTextDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FilmTextDAO) ctx.getBean("FilmTextDAO");
	}
}