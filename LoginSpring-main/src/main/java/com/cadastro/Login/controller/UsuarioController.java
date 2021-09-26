package com.cadastro.Login.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cadastro.Login.models.usuarios.Usuario;
import com.cadastro.Login.repository.UsuarioRepository;
import com.cadastro.Login.service.ServiceUsuario;
import com.cadastro.Login.util.Util;




@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private ServiceUsuario su;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	
	
	@PostMapping("/login")
	public ModelAndView login(Usuario usuario, HttpSession session, BindingResult br) throws NoSuchAlgorithmException, Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		if(br.hasErrors()) {
			mv.setViewName("login");
		}
		Usuario usuarioLogin = su.loginUsuario(usuario.getLogin(), Util.md5(usuario.getSenha()));
		if(usuarioLogin == null) {
			mv.addObject("msg", "Usuario não encontrado tente novamente");
		}
		if(usuarioLogin != null) {
	
		mv.addObject("msgLogin", "Você está logado");
		session.setAttribute("usuarioLogado", usuarioLogin);
		return mv;
}
			return mv;
	}
	@PostMapping("/cadastrar")
	public ModelAndView cadastrar(Usuario usuario, BindingResult br) throws Exception {
		ModelAndView mv = new ModelAndView("login");
		
		if(ur.findUsuario(usuario.getLogin()) != null) {
			mv.addObject("msg2", "Esse usuario já esta cadastrado");
			return mv;
		}
		
			su.salvarUsuario(usuario);
			
		
		return mv;
	}
	
	@GetMapping("/sair")
	public String sair(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	
}