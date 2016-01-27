package se.groupjcnr.projecti.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WORKITEM", schema = "PROJECTI")
@NamedQueries(value = { 
		@NamedQuery(name = "WorkItem.getWorkItemsByStatus", query = "SELECT w FROM WorkItem w WHERE w.status = :status"),
		@NamedQuery(name = "WorkItem.getWorkItemsByTeam", query = "SELECT w FROM WorkItem w WHERE w.team = :team"),
	//	@NamedQuery(name = "WorkItem.getWorkItemsByPartDesc", query = "SELECT w FROM WorkItem w WHERE partialDesc LIKE = :partialDesc"),
		@NamedQuery(name = "WorkItem.getAll", query = "SELECT w FROM WorkItem w")})
public class WorkItem extends AbstractEntity {

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private int priority;

	@OneToMany
	private List<Issue> issues;

	@ManyToOne
	private User user;

	@ManyToOne
	private Team team;

	@Column(nullable = false)
	private Status status;
	
	public enum Status {
		OPEN, ASSIGNED, DOING, DONE, REMOVED
	}

	protected WorkItem() {
		super();
	}

	public WorkItem(String title, String description) {
		this.title = title;
		this.description = description;
		this.issues = new ArrayList<>();
		this.status = Status.OPEN;
	}

	public WorkItem(Long id, String title, String description, int priority, List<Issue> issues, User user, Team team,
			Status status) {
		this.setId(id);
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.issues = issues;
		this.user = user;
		this.team = team;
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public User getUser() {
		return user;
	}

	public WorkItem setUser(User user) {
		this.user = user;
		return this;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public WorkItem addIssue(Issue issue) {
		issues.add(issue);
		return this;
	}

}