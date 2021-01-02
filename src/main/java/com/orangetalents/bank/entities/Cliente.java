package com.orangetalents.bank.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name="CLIENTE")
public class Cliente {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotNull
	@Size(min = 5, max = 60)
	private String Nome;
	
	@NotNull
	@Column(unique=true)
	private String email;
	
	@NotNull
	@Column(unique=true)
	private String cpf;
	
	@NotNull
	@Past
	private Date dataNascimento;
	
	public Cliente () {}

	

	public Cliente(Long id, @Size(min = 5, max = 60) String nome, String email, String cpf, @Past Date dataNascimento) {
		super();
		Id = id;
		Nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}



	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}
	
	

}
