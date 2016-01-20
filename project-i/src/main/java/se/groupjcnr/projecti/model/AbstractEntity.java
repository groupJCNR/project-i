package se.groupjcnr.projecti.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	public AbstractEntity() {
		super();
	}
	
	public Long getId() {
		return this.Id;
	}
}
