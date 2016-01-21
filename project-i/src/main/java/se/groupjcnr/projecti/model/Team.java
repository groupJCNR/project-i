package se.groupjcnr.projecti.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Team extends AbstractEntity {
	
	public enum Status{
        ACTIVE, INACTIVE   
    };
   
    @Column(nullable = false)
    private String name;
    
    private List<WorkItem> items;
    private List<User> users;
    private Status status;
   
    public Team(String name){
        this.name = name;
        this.items = new ArrayList<WorkItem>();
        this.users = new ArrayList<User>();
    }
    
    protected Team() {
    	super();
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
   
    public List<WorkItem> getItems(){
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
