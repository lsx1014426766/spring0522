package com.bjsxt.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.bjsxt.dao.UserDAO;
import com.bjsxt.model.User;
@Component("userDAO")
public class UserDAOImpl implements UserDAO {
	/* (non-Javadoc)
	 * @see com.bjsxt.dao.UserDAO#save(com.bjsxt.model.User)
	 */
	
	private int daoId;
	private String daoStatus;
	private Set<String> sets;
	private List<String> lists;
	private Map<String , String> maps;
	
	
	public Set<String> getSets() {
		return sets;
	}
	



	public void setSets(Set<String> sets) {
		this.sets = sets;
	}



	public List<String> getLists() {
		return lists;
	}



	public void setLists(List<String> lists) {
		this.lists = lists;
	}



	public Map<String, String> getMaps() {
		return maps;
	}



	public void setMaps(Map<String, String> maps) {
		this.maps = maps;
	}

	public int getDaoId() {
		return daoId;
	}

	public void setDaoId(int daoId) {
		this.daoId = daoId;
	}

	public String getDaoStatus() {
		return daoStatus;
	}

	public void setDaoStatus(String daoStatus) {
		this.daoStatus = daoStatus;
	}

	public void save(User user) {
		System.out.println("user saved!");
	}



	@Override
	public String toString() {
		return "UserDAOImpl [daoId=" + daoId + ", daoStatus=" + daoStatus
				+ ", sets=" + sets + ", lists=" + lists + ", maps=" + maps
				+ "]";
	}
	public void init(){
		   System.out.println("daoimpl init");
		   
	   }
	   public void destroy(){
		   System.out.println("daoimpl destroy");
		   
	   }




	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
}
