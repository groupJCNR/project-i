package se.groupjcnr.projecti.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS", schema = "PROJECTI")
@NamedQueries(value = { 
		@NamedQuery(name = "User.getAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.getUserByFirstName", query = "SELECT u FROM User u WHERE u.firstName =:firstname"),
		@NamedQuery(name = "User.getUserByLastName", query = "SELECT u FROM User u WHERE u.lastName =:lastname"),
		@NamedQuery(name = "User.getUserByUsername", query = "SELECT u FROM User u WHERE u.username =:username"),
		@NamedQuery(name = "User.getUserByUserId", query = "SELECT u FROM User u WHERE u.userId =:userid"),
		@NamedQuery(name = "User.getUserByTeam", query = "SELECT u FROM User u WHERE u.teams =:teams")})
public class User extends AbstractEntity {

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private Status status;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String userId;

	@ManyToMany
	private List<Team> teams;

	@OneToMany
	private List<WorkItem> workItems;

	public enum Status {
		ACTIVE, INACTIVE, REMOVED
	}

	protected User() {
		super();
	}

	public User(String username, String firstName, String lastName) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = Status.ACTIVE;
		this.userId = username + this.getId();
		this.teams = new ArrayList<>();
		this.workItems = new ArrayList<>();
	}

	public User(Long id, String firstName, String lastName, Status status, String userName, String userId,
			List<Team> teams, List<WorkItem> workItems) {
		this.setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
		this.username = userName;
		this.userId = userId;
		this.teams = teams;
		this.workItems = workItems;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public User setTeams(List<Team> teams) {
		this.teams = teams;
		return this;
	}

	public List<WorkItem> getWorkItems() {
		return workItems;
	}

	public User setWorkItems(List<WorkItem> workItems) {
		this.workItems = workItems;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public User setStatus(Status status) {
		this.status = status;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public User setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	
	public User addWorkItem(WorkItem workItem) {
		workItems.add(workItem);
		return this;
	}
	
	public User addTeam(Team team) {
		teams.add(team);
		return this;
	}
}