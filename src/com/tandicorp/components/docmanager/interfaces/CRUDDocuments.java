package com.tandicorp.components.docmanager.interfaces;

import java.util.List;

import com.tandicorp.components.docmanager.model.Propietary;
import com.tandicorp.components.docmanager.model.Tandidocument;;

public interface CRUDDocuments {
	
	public List<Tandidocument> searchDocumentByPropietaryId(String propietaryId);
	
	public byte[] searchDocumentByReference(String reference);
	
	public void saveDocument(Propietary propietary, List<Tandidocument> tandidocuments) throws Exception;
	
	public void deleteDocument(int documentId);

}
