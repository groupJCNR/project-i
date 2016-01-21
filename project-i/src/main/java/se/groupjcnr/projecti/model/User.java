package se.groupjcnr.projecti.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User extends AbstractEntity {

	public enum Status {
		ACTIVE, INACTIVE
	}

	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private Status status;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private Long userId;
	
	private List<Team> teamList;
	private List<WorkItem> workList;

	protected User() {
		super();
	}
	
	protected User(String firstName, String lastName, Status status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Team> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}

	public List<WorkItem> getWorkList() {
		return workList;
	}

	public void setWorkList(List<WorkItem> workList) {
		this.workList = workList;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
