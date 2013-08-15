package com.tandicorp.components.docmanager.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;



/**
 * The persistent class for the tandidocument database table.
 * 
 */
@Entity
@NamedQuery(name="Tandidocument.findAll", query="SELECT t FROM Tandidocument t")
public class Tandidocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

//	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp creationdate;
	
	private Timestamp lastmodifieddate;

	@Lob
	private byte[] data;

	private String description;

	private String name;

	private String path;

	private int version;

	private int hashcode;

	//bi-directional many-to-one association to Propietary
	@ManyToOne
	@JoinColumn(name="PROPIETARYID")
	private Propietary propietary;

	public Tandidocument(){

	}
	
	/*public Tandidocument(Timestamp creationdate, byte[] data,
			String description, String name, String path, String version,
			Propietary propietary) {

		this.creationdate = creationdate;
		this.data = data;
		this.description = description;
		this.name = name;
		this.path = path;
		this.version = version;
		this.propietary = propietary;
	}*/
	
	public Tandidocument(Timestamp creationdate, File data,
			String description
			//, String name
			//, String version
			//, Propietary propietary
			) throws IOException {

		this.creationdate = creationdate;
		this.lastmodifieddate = longAsTimestamp(data.lastModified());
		this.data = fileAsByte(data);
		this.description = description;
		this.name = data.getName();
		this.path = data.getPath();
		this.version = 1;
		this.hashcode = hashCode();
		//this.propietary = propietary;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreationdate() {
		return this.creationdate;
	}

	public void setCreationdate(Timestamp creationdate) {
		this.creationdate = creationdate;
	}

	public Timestamp getLastmodifieddate() {
		return lastmodifieddate;
	}

	public void setLastmodifieddate(Timestamp lastmodifieddate) {
		this.lastmodifieddate = lastmodifieddate;
	}

	public byte[] getData() {
		return this.data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setData(File file) throws IOException {
		//codigo para leer File y graba en data
		FileInputStream fis = new FileInputStream(file);
		if (file.exists() && file.isFile()) {
			this.data = new byte[(int)file.length()];
			fis.read(this.data);
			fis.close();
		} else {
			throw new IOException("El archivo que se desea grabar no existe o no es un archivo propiamente");
		}
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public int getHashcode() {
		return hashcode;
	}

	public void setHashcode(int hashcode) {
		this.hashcode = hashcode;
	}
	
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash + this.name.hashCode();
		hash = hash + (this.lastmodifieddate.toString()).hashCode();
		hash = hash + (this.data.length);
		return hash;
	}

	public Propietary getPropietary() {
		return this.propietary;
	}

	public void setPropietary(Propietary propietary) {
		this.propietary = propietary;
	}
	
	private Timestamp longAsTimestamp(long date){
		Timestamp newDate = new Timestamp(date);
		return newDate;
	}
	
	private byte[] fileAsByte(File file) throws IOException {
		byte[] dataByte;
		FileInputStream fis = new FileInputStream(file);
		if (file.exists() && file.isFile()) {
			dataByte = new byte[(int)file.length()];
			fis.read(dataByte);
			fis.close();
		} else {
			throw new IOException("El archivo que se desea grabar no existe o no es un archivo propiamente");
		}
		return dataByte;
	}

}