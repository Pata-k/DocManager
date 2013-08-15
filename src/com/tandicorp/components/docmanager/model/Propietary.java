package com.tandicorp.components.docmanager.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the propietary database table.
 * 
 */
@Entity
@NamedQuery(name="Propietary.findAll", query="SELECT p FROM Propietary p")
public class Propietary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	private String reference;

	private String type;

	//bi-directional many-to-one association to Tandidocument
	@OneToMany(mappedBy="propietary")
	private List<Tandidocument> tandidocuments;

	public Propietary() {
	}
	
	public Propietary(String name, String reference, String type) {

		this.name = name;
		this.reference = reference;
		this.type = type;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Tandidocument> getTandidocuments() {
		return this.tandidocuments;
	}

	public void setTandidocuments(List<Tandidocument> tandidocuments) {
		this.tandidocuments = tandidocuments;
	}

	public Tandidocument addTandidocument(Tandidocument tandidocument) {
		getTandidocuments().add(tandidocument);
		tandidocument.setPropietary(this);

		return tandidocument;
	}

	public Tandidocument removeTandidocument(Tandidocument tandidocument) {
		getTandidocuments().remove(tandidocument);
		tandidocument.setPropietary(null);

		return tandidocument;
	}

}
