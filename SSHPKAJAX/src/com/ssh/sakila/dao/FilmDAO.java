package com.ssh.sakila.dao;

import java.sql.Timestamp;
import java.util.Date;
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

import com.ssh.sakila.pojo.Film;

/**
 * A data access object (DAO) providing persistence and search support for Film
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.ssh.sakila.pojo.Film
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FilmDAO {
	private static final Logger log = LoggerFactory.getLogger(FilmDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String RENTAL_DURATION = "rentalDuration";
	public static final String RENTAL_RATE = "rentalRate";
	public static final String LENGTH = "length";
	public static final String REPLACEMENT_COST = "replacementCost";
	public static final String RATING = "rating";
	public static final String SPECIAL_FEATURES = "specialFeatures";

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

	public void save(Film transientInstance) {
		log.debug("saving Film instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Film persistentInstance) {
		log.debug("deleting Film instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Film findById(java.lang.Short id) {
		log.debug("getting Film instance with id: " + id);
		try {
			Film instance = (Film) getCurrentSession().get(
					"com.ssh.sakila.pojo.Film", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Film> findByExample(Film instance) {
		log.debug("finding Film instance by example");
		try {
			List<Film> results = (List<Film>) getCurrentSession()
					.createCriteria("com.ssh.sakila.pojo.Film")
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
		log.debug("finding Film instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Film as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Film> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<Film> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Film> findByRentalDuration(Object rentalDuration) {
		return findByProperty(RENTAL_DURATION, rentalDuration);
	}

	public List<Film> findByRentalRate(Object rentalRate) {
		return findByProperty(RENTAL_RATE, rentalRate);
	}

	public List<Film> findByLength(Object length) {
		return findByProperty(LENGTH, length);
	}

	public List<Film> findByReplacementCost(Object replacementCost) {
		return findByProperty(REPLACEMENT_COST, replacementCost);
	}

	public List<Film> findByRating(Object rating) {
		return findByProperty(RATING, rating);
	}

	public List<Film> findBySpecialFeatures(Object specialFeatures) {
		return findByProperty(SPECIAL_FEATURES, specialFeatures);
	}

	public List findAll() {
		log.debug("finding all Film instances");
		try {
			String queryString = "from Film";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Film merge(Film detachedInstance) {
		log.debug("merging Film instance");
		try {
			Film result = (Film) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Film instance) {
		log.debug("attaching dirty Film instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Film instance) {
		log.debug("attaching clean Film instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FilmDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FilmDAO) ctx.getBean("FilmDAO");
	}
}