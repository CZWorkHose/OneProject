package com.ssh.sakila.service;

import java.util.List;

import com.ssh.sakila.dao.ActorDAO;
import com.ssh.sakila.pojo.Actor;
import com.ssh.sakila.util.PageBean;

public class ActorService {
	private ActorDAO actorDao;

	/**
	 * @return the actorDao
	 */
	public ActorDAO getActorDao() {
		return actorDao;
	}

	/**
	 * @param actorDao the actorDao to set
	 */
	public void setActorDao(ActorDAO actorDao) {
		this.actorDao = actorDao;
	}
	
	/**
	 * 查询所有演员
	 * @return
	 */
	public List<Actor> findAll(){
		return this.actorDao.findAll();
	}
	
	/**
	 * 分页查询演员信息
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<?> findActorByPage(int page, int rows){
		String hql = "from Actor";
		
		return this.actorDao.findActorByPage((page-1)*rows, rows, hql);
	}
	
	/**
	 * 分页查询演员信息AJAX
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<?> findActorByPageAJAX(int page, int rows){
		String hql = "select ac.actorId, ac.firstName, ac.lastName from Actor as ac ";
		
		return this.actorDao.findActorByPage((page-1)*rows, rows, hql);
	}
}
