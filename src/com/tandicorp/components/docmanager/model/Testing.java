package com.tandicorp.components.docmanager.model;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.collections.ArrayStack;
import org.hibernate.dialect.TimesTenDialect;

import com.tandicorp.components.docmanager.implementations.database.CRUDDocumentsImplDatabase;
import com.tandicorp.components.docmanager.persistence.JPAHelper;

public class Testing {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
//		JPAHelper jpah = new JPAHelper();
//		EntityManager em = jpah.buildEntityManager();
		
		
		//Propietary prop = em.find(Propietary.class, "654321");
		
		//Codigo para insertar nuevos documentos y su propietario 
		/*Propietary prop = new Propietary("Valentina", "123456789", "ITEM");
		
		File pdf = new File("C:\\Arch\\CD-2078.pdf");
		
		Timestamp ts1 = new Timestamp(System.currentTimeMillis());
		
		Tandidocument document1 = new Tandidocument(ts1, pdf, "Prueba 3 Tesis");
		
		List<Tandidocument> listTD = Arrays.asList(document1);
		
		CRUDDocumentsImplDatabase obj = new CRUDDocumentsImplDatabase();
		obj.saveDocument(prop, listTD);*/
		
		/*Tandidocument document1 = new Tandidocument();
		document1.setName("David VV");
		document1.setDescription("Documento de Prueba");
		document1.setCreationdate(new Timestamp(System.currentTimeMillis()));
		document1.setVersion("1.0");
		document1.setPropietary(null);
		
		System.out.println("El hashcode de document  es: " + document.hashCode());
		System.out.println("El hashcode de document1 es: " + document1.hashCode());*/
		
		
		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("DocManager");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Tandidocument> consulta = em.createQuery("Select t from Tandidocument t", Tandidocument.class);
		List<Tandidocument> lista = consulta.getResultList();
		File prueba = null;
		for(Tandidocument t : lista){
			System.out.println("\nDOCUMENTO:");
			System.out.println(t.getId());
			System.out.println(t.getName());
			System.out.println(t.getDescription());
			System.out.println(t.getCreationdate() + " " +t.getPropietary().getName());
			System.out.println(t.getVersion());
			System.out.println(t.getPropietary().getId());
			System.out.println(t.getPath());
			System.out.println("\nPROPIETARIO:");
			System.out.println(t.getPropietary().getId());
			System.out.println(t.getPropietary().getName());
			System.out.println(t.getPropietary().getReference());
			System.out.println(t.getPropietary().getType());
			System.out.println(t.getPropietary().getTandidocuments());
		}*/
		//Query qry = em.createQuery("Select t from Tandidocument t where t.Propietary.id = ?1").setParameter(1, "654321");
		
		/*Query consulta = 
				em.createQuery("SELECT t.id FROM Tandidocument t where t.propietary.id=?1").setParameter(1, "654321");
		List<Tandidocument> listaDeDocs = consulta.getResultList();
		Iterator<Tandidocument> it = listaDeDocs.iterator(); 
		for(int i = 0; i<listaDeDocs.size(); i++) {
			System.out.println("Prueba " + listaDeDocs.get(i));
		}*/
		
		
		//Codigo para comprobar existencia de documento
//		Propietary prop = new Propietary("Valentina", "789456123", "ITEM");
//		
//		File txt = new File("C:\\Arch\\CD-2078.pdf");
//		
//		Timestamp ts1 = Timestamp.valueOf("2013-07-25 15:07:50");
//		
//		/*DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//		java.util.Date date = (java.util.Date) dateFormat.parse("25/07/2013 15:07:50");
//		long time = date.getTime();
//		Timestamp ts1 = new Timestamp(time);*/
//				
//		Tandidocument document1 = new Tandidocument(ts1, txt, "Archivo TXT");
//		
//		System.out.println(document1.getHashcode());
//		
		CRUDDocumentsImplDatabase obj = new CRUDDocumentsImplDatabase();
//		boolean bandera = obj.documentExistsBDD(document1, prop.getReference(), em);
		obj.deleteDocument(6);
		
		//Codigo para comprobar la version mas actual de un documento
//		int max;
//		max = (Integer) em.createQuery("SELECT max(t.version) FROM Tandidocument t WHERE t.propietary.reference=?1").setParameter(1, "1").getSingleResult();
//		System.out.println(max);
//		byte[] archivo = obj.searchDocumentByReference("234567890");
//		System.out.println(archivo.length);
		
	}
}
