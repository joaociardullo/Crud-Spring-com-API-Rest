package com.cadastro.Login.models.usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
 
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
@Column
private String login;
@Column
private String senha;





public Usuario(String login, String senha) {
	super();
	this.login = login;
	this.senha = senha;
}

public Usuario() {
	
}

public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getSenha() {
	return senha;
}
public void setSenha(String senha) {
	this.senha = senha;
}


}
