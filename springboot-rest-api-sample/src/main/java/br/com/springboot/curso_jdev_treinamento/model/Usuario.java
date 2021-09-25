package br.com.springboot.curso_jdev_treinamento.model; //criação de models para BD

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity   //sempre quando usar com o banco de dados 
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class  Usuario implements Serializable {

	
	private static final long serialVersionUID = 1L;
	//dados para criação de tabela Usarios
	private Long id;
	
	private String nome;
	
	private int idade;
	
	//ID para primarykey
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_usuario")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	

}
