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

import com.ssh.sakila.pojo.Actor;
import com.ssh.sakila.pojo.FilmActor;

/**
 * A data access object (DAO) providing persistence and search support for
 * FilmActor entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ssh.sakila.pojo.FilmActor
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FilmActorDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FilmActorDAO.class);
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

	public void save(FilmActor transientInstance) {
		log.debug("saving FilmActor instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FilmActor persistentInstance) {
		log.debug("deleting FilmActor instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FilmActor findById(com.ssh.sakila.pojo.FilmActorId id) {
		log.debug("getting FilmActor instance with id: " + id);
		try {
			FilmActor instance = (FilmActor) getCurrentSession().get(
					"com.ssh.sakila.pojo.FilmActor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<FilmActor> findByExample(FilmActor instance) {
		log.debug("finding FilmActor instance by example");
		try {
			List<FilmActor> results = (List<FilmActor>) getCurrentSession()
					.createCriteria("com.ssh.sakila.pojo.FilmActor")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/**
	 * 根据演员ID查询演员和电影关联数据
	 * @param ac
	 * @return
	 */
	public List<?> findAFByActor(Actor ac){
		
		try{
			String hql = "from FilmActor as fa where fa.actor.actorId = " + ac.getActorId();
			
			List<?> results = (List<?>) getCurrentSession().createQuery(hql).list();
			
			return  results;
			
		}catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding FilmActor instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from FilmActor as model where model."
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
		log.debug("finding all FilmActor instances");
		try {
			String queryString = "from FilmActor";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FilmActor merge(FilmActor detachedInstance) {
		log.debug("merging FilmActor instance");
		try {
			FilmActor result = (FilmActor) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FilmActor instance) {
		log.debug("attaching dirty FilmActor instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FilmActor instance) {
		log.debug("attaching clean FilmActor instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FilmActorDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FilmActorDAO) ctx.getBean("FilmActorDAO");
	}
}