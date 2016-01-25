package se.groupjcnr.projecti.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "TEAM", schema = "PROJECTI")
public class Team extends AbstractEntity {

	@Column(nullable = false)
    private String name;

	@Column(nullable = false)
    private Status status;

	@OneToMany
    private List<WorkItem> items;

	@ManyToMany
    private List<User> users;
    
	public enum Status{
        ACTIVE, INACTIVE, REMOVED  
    }

    protected Team() {
    	super();
    }
   
    public Team(String name){
        this.name = name;
        this.status = Status.ACTIVE;
        this.items = new ArrayList<WorkItem>();
        this.users = new ArrayList<User>();
    }
    
    public Team(Long id, String name, Status status, List<WorkItem> items, List<User> users) {
		this.setId(id);
		this.name = name;
		this.status = status;
		this.items = items;
		this.users = users;
	}

	public String getName(){
        return name;
    }
   
    public void setName(String name){
        this.name = name;
    }
   
    public Status getStatus(){
        return status;
    }
   
    public void setStatus(Status status){
        this.status = status;
    }
   
    public List<WorkItem> getWorkItems(){
        return items;
    }
   
    public void setItemList(List<WorkItem> itemList){
        this.items = itemList;
    }
   
    public List<User> getUsers(){
        return users;
    }
   
    public void setUserList(List<User> userList){
        this.users = userList;
    }
}