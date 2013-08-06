package com.tandicorp.components.docmanager.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHelper {
	
	EntityManagerFactory emf;
	
	public EntityManager buildEntityManager(){
		emf = Persistence.createEntityManagerFactory("DocManager");
		return emf.createEntityManager();
	}

}
