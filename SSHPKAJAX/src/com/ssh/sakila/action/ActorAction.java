package com.ssh.sakila.action;


import java.util.List;

import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.ssh.sakila.pojo.Actor;
import com.ssh.sakila.pojo.TestAuto;
import com.ssh.sakila.service.ActorService;
import com.ssh.sakila.service.FilmActorService;
import com.ssh.sakila.util.PageBean;

public class ActorAction {
	private ActorService actorService;
	private FilmActorService filmActorService;

	private Actor actor;
	
	private PageBean pageBean;
	
	private int page;
	private int pageSize = 10;
	
	private String jsonResult;
	
	private TestAuto strTest;

	/**
	 * @return the actorService
	 */
	public ActorService getActorService() {
		return actorService;
	}

	/**
	 * @param actorService
	 *            the actorService to set
	 */
	public void setActorService(ActorService actorService) {
		this.actorService = actorService;
	}

	/**
	 * @return the actor
	 */
	public Actor getActor() {
		return actor;
	}

	/**
	 * @param actor
	 *            the actor to set
	 */
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	/**
	 * 查询所有演员
	 * @return
	 */
	public String findAllActor() {
		List<Actor> actorList = this.actorService.findAll();

		if (null != actorList) {
			ActionContext.getContext().put("actorList", actorList);
			
			return "success";
		}

		return "error";
	}
	
	public String testAutowired(){
		
		if(null != strTest){
			System.out.println(strTest);
		}
		
		return "success";
	}
	
	/**
	 * 分页查询演员
	 * @return
	 */
	public String findActorByPage(){
		List<?> actorList = this.actorService.findActorByPage(page, pageSize);
		
		if(null != actorList){
			ActionContext.getContext().put("actorList", actorList);
			
			return "pages";
		}
		return "error";
	}
	
	/**
	 * 分页查询演员ajax
	 * @return
	 */
	public String findActorByPageAJAX(){
		
		List<?> actorList = this.actorService.findActorByPageAJAX(page, pageSize);
		
		if(null != actorList){
			JSONArray jsArray = JSONArray.fromObject(actorList);
			
			jsonResult = jsArray.toString();
			
			System.out.println("jsonResult:"+jsonResult);
			
			return "success";
		}
		return "error";
	}
	
	/**
	 * 查询某个演员、电影信息
	 * @return
	 */
	public String findFilmActor() {
		
		Actor ac = new Actor();
		ac.setActorId((short) 1);
		
		List<?> filmActorList = this.filmActorService.findFilmActor(ac);
		
		if (null != filmActorList) {
			
			ActionContext.getContext().put("filmActorList", filmActorList);
			
			return "success";
		}
		
		return "error";
	}

	public FilmActorService getFilmActorService() {
		return filmActorService;
	}

	public void setFilmActorService(FilmActorService filmActorService) {
		this.filmActorService = filmActorService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

}
