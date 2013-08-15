package com.tandicorp.components.docmanager.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

import com.tandicorp.components.docmanager.model.Propietary;
import com.tandicorp.components.docmanager.model.Tandidocument;;

public interface CRUDDocuments {
	
	public List<Tandidocument> searchDocumentsByReference(String reference, EntityManager em);
	
	public Tandidocument searchDocumentByReference(String reference, EntityManager em);
	
	public void saveDocument(Propietary propietary, List<Tandidocument> tandidocuments, EntityManager em) throws Exception;
	
	public void deleteDocument(int documentId, EntityManager em);

}
