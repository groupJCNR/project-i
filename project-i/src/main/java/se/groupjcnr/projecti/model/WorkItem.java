package se.groupjcnr.projecti.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WORKITEM", schema = "PROJECTI")
public class WorkItem extends AbstractEntity {

	@Column(nullable = false)
	private String title;
	
	@Lob
	@Column(columnDefinition="CLOB NOT NULL")
	private String description;
	
	@Column(nullable = false)
	private int priority;
	
	@OneToMany
	private List<Issue> issues;
	
	@Column
	private Status status;
	
	public enum Status {
		OPEN, ASSIGNED, DOING, DONE, REMOVED
	}
	
	public WorkItem(String title, String description) {
		this.title = title;
		this.description = description;
		this.issues = new ArrayList<>();
		this.status = status.OPEN;
	}

	protected WorkItem() {
		super();
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

}
