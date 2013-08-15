package com.tandicorp.components.docmanager.implementations.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tandicorp.components.docmanager.interfaces.CRUDDocuments;
import com.tandicorp.components.docmanager.model.Propietary;
import com.tandicorp.components.docmanager.model.Tandidocument;
import com.tandicorp.components.docmanager.persistence.JPAHelper;

public class CRUDDocumentsImplDatabase implements CRUDDocuments {

	@Override
	public List<Tandidocument> searchDocumentsByReference(String reference, EntityManager em) {
		
		List<Tandidocument> documentsFound = null;
		try {
			Query query = em.createQuery("SELECT t FROM Tandidocument t \n"
					+ "where t.propietary.reference=?1").setParameter(1, reference);
			documentsFound = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			em.close();
		}
		return documentsFound;
	}
	
	@Override
	public Tandidocument searchDocumentByReference(String reference, EntityManager em) {
		
		Tandidocument documentFound = null;
		try{
			Query query = em.createQuery("SELECT t FROM Tandidocument t \n"
					+ "where t.propietary.reference=?1 \n"
					+ "and t.version = (SELECT max(td.version) FROM Tandidocument td \n"
					+ "where td.propietary.id = t.propietary.id)").setParameter(1, reference);

			documentFound = (Tandidocument) query.getSingleResult();
		}catch (Throwable e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return documentFound;
	}

	@Override
	public void saveDocument(Propietary newPropietary, List<Tandidocument> documentsToSave, EntityManager em) throws Exception {
		
		boolean flag;
		int lastVersion;
		Integer result;

		try {
			em.getTransaction().begin();
			
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
					if(result == null || result == 0){
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
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			em.close();
		}
	}

	@Override
	public void deleteDocument(int documentId, EntityManager em) {
		
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
		try {
			Query qry = em.createQuery("SELECT t FROM Tandidocument t where t.hashcode = ?1 and t.propietary.reference=?2").setParameter(1, document.getHashcode()).setParameter(2, reference);
			List<Tandidocument> results = qry.getResultList();
			if (!results.isEmpty()) {
				exists = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}

}
