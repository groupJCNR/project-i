package se.groupjcnr.projecti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ISSUE", schema = "PROJECTI")
public class Issue extends AbstractEntity {

	@Column(nullable = false)
	private String title;
	
	@ManyToOne
	private WorkItem workItem;
	
	@Column
	private Status status;
	
	public enum Status {
		RESOLVED, UNRESOLVED;
	}

	protected Issue() {
		super();
	}
	
	public Issue(String title, WorkItem workItem) {
		this.title = title;
		this.workItem = workItem;
		this.status = status.UNRESOLVED;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public WorkItem getWorkItem() {
		return workItem;
	}

	public void setWorkItem(WorkItem workItem) {
		this.workItem = workItem;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
