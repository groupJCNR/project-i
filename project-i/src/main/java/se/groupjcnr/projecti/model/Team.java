package se.groupjcnr.projecti.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TEAM", schema = "PROJECTI")
@NamedQuery(name = "Team.getAll", query = "SELECT t FROM Team t")
public class Team extends AbstractEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Status status;

	@OneToMany
	private List<WorkItem> workItems;

	@ManyToMany
	private List<User> users;

	public enum Status {
		ACTIVE, INACTIVE, REMOVED
	}

	protected Team() {
		super();
	}

	public Team(String name) {
		this.name = name;
		this.status = Status.ACTIVE;
		this.workItems = new ArrayList<WorkItem>();
		this.users = new ArrayList<User>();
	}

	public Team(Long id, String name, Status status, List<WorkItem> items, List<User> users) {
		this.setId(id);
		this.name = name;
		this.status = status;
		this.workItems = items;
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public Team setName(String name) {
		this.name = name;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public Team setStatus(Status status) {
		this.status = status;
		return this;
	}

	public List<WorkItem> getWorkItems() {
		return workItems;
	}

	public Team setWorkItems(List<WorkItem> itemList) {
		this.workItems = itemList;
		return this;
	}

	public List<User> getUsers() {
		return users;
	}

	public Team setUsers(List<User> users) {
		this.users = users;
		return this;
	}

	public Team addUser(User user) {
		users.add(user);
		return this;
	}

	public Team addWorkItem(WorkItem workItem) {
		workItems.add(workItem);
		return this;
	}

}