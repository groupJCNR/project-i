package se.groupjcnr.projecti.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User extends AbstractEntity {

	public enum Status {
		ACTIVE, INACTIVE
	}

	private String firstName, lastName;
	private List<Team> teamList;
	private List<WorkItem> workList;
	private Status status;

	public User() {
		super();
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

}
