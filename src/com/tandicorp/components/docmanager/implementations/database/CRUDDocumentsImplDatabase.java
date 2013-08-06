package com.tandicorp.components.docmanager.implementations.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.tandicorp.components.docmanager.interfaces.CRUDDocuments;
import com.tandicorp.components.docmanager.model.Propietary;
import com.tandicorp.components.docmanager.model.Tandidocument;
import com.tandicorp.components.docmanager.persistence.JPAHelper;

public class CRUDDocumentsImplDatabase implements CRUDDocuments {

	@Override
	public List<Tandidocument> searchDocumentByPropietaryId(String propietaryId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public byte[] searchDocumentByReference(String reference) {
		
		JPAHelper emb = new JPAHelper();
		EntityManager em = emb.buildEntityManager();
		
		byte[] fileArray = null;
		try{
			Query query = em.createQuery("SELECT t FROM Tandidocument t \n"
					+ "where t.propietary.reference=?1 \n"
					+ "and t.version = (SELECT max(td.version) FROM Tandidocument td \n"
					+ "where td.propietary.id = t.propietary.id)").setParameter(1, reference);

			Tandidocument documentFound = (Tandidocument) query.getSingleResult();
			fileArray = documentFound.getData();
		}catch (Throwable e){
			e.printStackTrace();
		}
		return fileArray;
	}

	@Override
	public void saveDocument(Propietary newPropietary, List<Tandidocument> documentsToSave) throws Exception {
		
		JPAHelper emb = new JPAHelper();
		EntityManager em = emb.buildEntityManager();
		
		boolean flag;
		int lastVersion;
		Integer result;
		
		//EntityTransaction tx = null;
		//tx = em.getTransaction();
		em.getTransaction().begin();
		//tx.begin();
		
		Propietary finalPropietary = new Propietary();
		Query query = em.createQuery("SELECT p FROM Propietary p where p.reference=?1").setParameter(1, newPropietary.getReference());
		List<Propietary> propietaries = query.getResultList();
		
		if(propietaries.isEmpty()){
			em.persist(newPropietary);
			finalPropietary = newPropietary;
		}else{
			finalPropietary = propietaries.get(0);
		}
		
		for(Tandidocument t : documentsToSave){
			flag = documentExistsBDD(t, finalPropietary.getReference(), em);
			if(flag == true){
				throw new Exception("El documento no se puede guardar porque ya existe en la BDD");
			}else{
				t.setPropietary(finalPropietary);
				result = (Integer) em.createQuery("SELECT max(t.version) FROM Tandidocument t WHERE t.propietary.reference=?1").setParameter(1, finalPropietary.getReference()).getSingleResult();
				if(result == null){
					em.persist(t);
				}else{
					lastVersion = result;
					lastVersion += 1;
					t.setVersion(lastVersion);
					em.persist(t);
				}
			}
		}
		
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteDocument(int documentId) {

		JPAHelper emb = new JPAHelper();
		EntityManager em = emb.buildEntityManager();
		
		try {
			em.getTransaction().begin();
			Tandidocument docToDelete = em.find(Tandidocument.class, documentId);
			em.remove(docToDelete);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			em.close();
		}
	}
	
	public boolean documentExistsBDD(Tandidocument document, String reference, EntityManager em){
		
		boolean exists = false;
		Query qry = em.createQuery("SELECT t FROM Tandidocument t where t.hashcode = ?1 and t.propietary.reference=?2").setParameter(1, document.getHashcode()).setParameter(2, reference);
		List<Tandidocument> results = qry.getResultList();
		if (!results.isEmpty()) {
			exists = true;
		}
		return exists;
	}

}
