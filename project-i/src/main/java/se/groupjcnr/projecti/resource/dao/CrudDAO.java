package se.groupjcnr.projecti.resource.dao;

import se.groupjcnr.projecti.model.AbstractEntity;

public interface CrudDAO <E extends AbstractEntity> {
	
	E save(E entity);
	
	E remove(E entity);
	
	E findById(Long id);

}
