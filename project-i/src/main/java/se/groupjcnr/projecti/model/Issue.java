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

	@Column(nullable = false)
	private Status status;
	
	public enum Status {
		RESOLVED, UNRESOLVED, REMOVED;
	}

	protected Issue() {
		super();
	}

	public Issue(String title) {
		this.title = title;
		this.workItem = null;
		this.status = Status.UNRESOLVED;
	}

	public Issue(Long id, String title, WorkItem workItem, Status status) {
		this.setId(id);
		this.title = title;
		this.workItem = workItem;
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public Issue setTitle(String title) {
		this.title = title;
		return this;
	}

	public WorkItem getWorkItem() {
		return workItem;
	}

	public Issue setWorkItem(WorkItem workItem) {
		this.workItem = workItem;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public Issue setStatus(Status status) {
		this.status = status;
		return this;
	}
	
}