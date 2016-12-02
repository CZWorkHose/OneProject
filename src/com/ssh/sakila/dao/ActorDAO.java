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

import com.ssh.sakila.pojo.Actor;
import com.ssh.sakila.util.PageBean;

/**
 * A data access object (DAO) providing persistence and search support for Actor
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.ssh.sakila.pojo.Actor
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ActorDAO {
	private static final Logger log = LoggerFactory.getLogger(ActorDAO.class);
	// property constants
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";

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

	public void save(Actor transientInstance) {
		log.debug("saving Actor instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Actor persistentInstance) {
		log.debug("deleting Actor instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Actor findById(java.lang.Short id) {
		log.debug("getting Actor instance with id: " + id);
		try {
			Actor instance = (Actor) getCurrentSession().get(
					"com.ssh.sakila.pojo.Actor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Actor> findByExample(Actor instance) {
		log.debug("finding Actor instance by example");
		try {
			List<Actor> results = (List<Actor>) getCurrentSession()
					.createCriteria("com.ssh.sakila.pojo.Actor")
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
		log.debug("finding Actor instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Actor as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Actor> findByFirstName(Object firstName) {
		return findByProperty(FIRST_NAME, firstName);
	}

	public List<Actor> findByLastName(Object lastName) {
		return findByProperty(LAST_NAME, lastName);
	}

	public List findAll() {
		log.debug("finding all Actor instances");
		try {
			String queryString = "from Actor";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * 演员分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<?> findActorByPage(int page,int rows,String hql){
		try{
			Query q = getCurrentSession().createQuery(hql);
			
			if(null != q){
				q.setFirstResult(page);
				q.setMaxResults(rows);
				return q.list();
			}
			return null;
		}catch (RuntimeException re) {
			log.error("find pages failed", re);
			throw re;
		}
	}
	
	public Actor merge(Actor detachedInstance) {
		log.debug("merging Actor instance");
		try {
			Actor result = (Actor) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Actor instance) {
		log.debug("attaching dirty Actor instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Actor instance) {
		log.debug("attaching clean Actor instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ActorDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ActorDAO) ctx.getBean("ActorDAO");
	}
}