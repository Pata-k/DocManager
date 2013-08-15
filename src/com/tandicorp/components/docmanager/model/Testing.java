package com.tandicorp.components.docmanager.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.io.File;

import javax.persistence.EntityManager;

import com.tandicorp.components.docmanager.implementations.database.CRUDDocumentsImplDatabase;
import com.tandicorp.components.docmanager.persistence.JPAHelper;

public class Testing {

	public static void main(String[] args) throws Exception{
		
		JPAHelper jpa = new JPAHelper();
		EntityManager em = jpa.buildEntityManager();
		CRUDDocumentsImplDatabase obj = new CRUDDocumentsImplDatabase();
		
//		Propietary prop1 = em.find(Propietary.class, "654321");
		
		//Codigo para insertar nuevos documentos y su propietario
		Propietary prop2 = new Propietary("Andres", "1717120503", "POLIZA");
		File pdf = new File("C:\\Arch\\ConnectDBOracle.txt");
		Timestamp ts1 = new Timestamp(System.currentTimeMillis());
		
		Tandidocument document1 = new Tandidocument(ts1, pdf, "PDF Prueba");
		List<Tandidocument> listTD = Arrays.asList(document1);
		
		obj.saveDocument(prop2, listTD, em);
		
		//Codigo para verificar el hashcode que se genera al crear un documento
//		Tandidocument document2 = new Tandidocument();
//		document2.setName("David VV");
//		document2.setDescription("Documento de Prueba");
//		document2.setCreationdate(new Timestamp(System.currentTimeMillis()));
//		document2.setPropietary(null);
//		System.out.println("El hashcode de document1 es: " + document2.hashCode());
		
		//Codigo para realizar busqueda de una lista documentos por la referencia de su propietario
		List<Tandidocument> listaDeDocs = obj.searchDocumentsByReference("1717120503", em);
		for(Tandidocument t : listaDeDocs) {
			System.out.println("\nDOCUMENTO:");
			System.out.println(t.getId());
			System.out.println(t.getName());
			System.out.println(t.getDescription());
			System.out.println(t.getCreationdate() + " " +t.getPropietary().getName());
			System.out.println(t.getLastmodifieddate());
			System.out.println(t.getVersion());
			System.out.println(t.getHashcode());
			System.out.println(t.getPath());
			System.out.println("\nPROPIETARIO:");
			System.out.println(t.getPropietary().getId());
			System.out.println(t.getPropietary().getName());
			System.out.println(t.getPropietary().getReference());
			System.out.println(t.getPropietary().getType());
		}
		//System.out.println(org.hibernate.Version.getVersionString());
		
		//Codigo para borrar un documento por su id
//		obj.deleteDocument(6, em);
		
		//Codigo para comprobar la version mas actual de un documento
//		int max;
//		max = (Integer) em.createQuery("SELECT max(t.version) FROM Tandidocument t WHERE t.propietary.reference=?1").setParameter(1, "123456789").getSingleResult();
//		System.out.println(max);
		
		//Codigo para comprobar la busqueda de un documento por la reference de su propietario
//		Tandidocument archivo = obj.searchDocumentByReference("234567890", em);
//		System.out.println(archivo.getName());
//		System.out.println(archivo.getDescription());
//		System.out.println(archivo.getVersion());
	}
}
