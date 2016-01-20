package se.groupjcnr.projecti.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	public AbstractEntity() {
		super();
	}
	
	public Long getId() {
		return this.id;
	}
}
