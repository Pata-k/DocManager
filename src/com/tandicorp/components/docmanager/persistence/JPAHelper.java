package com.tandicorp.components.docmanager.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHelper {
	
	EntityManagerFactory emf;
	EntityManager em;
	
	public EntityManager buildEntityManager(){
		emf = Persistence.createEntityManagerFactory("DocManager");
		em =  emf.createEntityManager();
		return em;
	}
}
