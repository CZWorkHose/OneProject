package com.ssh.sakila.service;

import java.util.List;

import com.ssh.sakila.dao.FilmActorDAO;
import com.ssh.sakila.pojo.Actor;
import com.ssh.sakila.pojo.FilmActor;

public class FilmActorService {
	private FilmActorDAO filmActorDao;
	
	public List<?> findAll(){
		return this.filmActorDao.findAll();
	}
	
	public List<?> findFilmActor(Actor ac){
		return this.filmActorDao.findAFByActor(ac);
	}

	public FilmActorDAO getFilmActorDao() {
		return filmActorDao;
	}

	public void setFilmActorDao(FilmActorDAO filmActorDao) {
		this.filmActorDao = filmActorDao;
	}
	
}
