package se.groupjcnr.projecti.model;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {

	private String firstName;
	private String lastName;
	private List<Team> teams;
	private List<WorkItem> workItems;
	private boolean Active;
	
	protected User() {
		super();
	}
	
	public User(String firstName, String lastName) {
		
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public List<WorkItem> getWorkItems() {
		return workItems;
	}

	public boolean isActive() {
		return Active;
	}
	
	
}
