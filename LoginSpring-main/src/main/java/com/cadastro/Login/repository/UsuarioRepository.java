package com.cadastro.Login.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cadastro.Login.models.usuarios.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	@Query("select u from Usuario u where u.login = :login and u.senha = :senha")
	Usuario findLogin(String login, String senha);

	@Query("select u from Usuario u where u.login = :login")
	Usuario findUsuario(String login);
}
