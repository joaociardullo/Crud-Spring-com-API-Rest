package com.cadastro.Login.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastro.Login.models.usuarios.Usuario;
import com.cadastro.Login.repository.UsuarioRepository;
import com.cadastro.Login.util.Util;


@Service
public class ServiceUsuario {

	@Autowired
	private UsuarioRepository ur;
	
	public void salvarUsuario(Usuario usuario) throws Exception {
		
			
			usuario.setSenha(Util.md5(usuario.getSenha()));
		
		ur.save(usuario);
	}
	
	public Usuario loginUsuario(String login, String senha) throws Exception{
		Usuario usuarioLogin = ur.findLogin(login, senha);
		return usuarioLogin;
	}
	
}
