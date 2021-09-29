package br.com.springboot.curso_jdev_treinamento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired /* CD/IC/CDI - inhenção de depedencia */

	private UsuarioRepository usuarioRepository;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	@RequestMapping(value = "/meu/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Hello " + name + "!";
	}

	@RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String Teste(@PathVariable String nome) {

		Usuario usuario = new Usuario();
		usuario.setNome(nome);

		usuarioRepository.save(usuario); /* grava no banco de dados */

		return "Joao na voz " + nome;
	}

	@GetMapping(value = "listatodos") /* Primeiro Metodo de API, Buscando do banco de dados */
	@ResponseBody /* Retorna os dados para o corpo da resposta */
	public ResponseEntity<java.util.List<Usuario>> listarUsuario() {

		java.util.List<Usuario> usuarios = usuarioRepository.findAll(); // FIndALl Retorna tudo do banco de dados
																		// /*Executa a consulta no banco de dados*/

		return new ResponseEntity<java.util.List<Usuario>>(usuarios, HttpStatus.OK); // Retorna a lista em JSON
	}

	@PostMapping(value = "salvar") /* Mapeia a URL Enviar os dados dentro da requisição via post */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<Usuario>salvar(@RequestBody Usuario usuario) {

		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

	@PutMapping(value = "atualizar") /* Mapeia a URL Enviar os dados dentro da requisição via post */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) { // ? tendo esse sinal podemos retronar qualquer
																		// coisa

		if (usuario.getId() == null) { /* IF para ser obrigado a passar um ID */
			return new ResponseEntity<String>("ID nao encotrado por favor informar novamente",
					HttpStatus.OK); /* retorno do if */
		}

		Usuario user = usuarioRepository.saveAndFlush(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@DeleteMapping(value = "delete") /* Mapeia a URL Enviar os dados dentro da requisição via post */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<String> delete(@RequestParam Long iduser) { /* è passado um parametro Long ID */

		usuarioRepository.deleteById(iduser);

		return new ResponseEntity<String>("Usuario deleteado com Sucesso", HttpStatus.OK);

	}

	@GetMapping(value = "buscaruserid")
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<Usuario> buscaruserid(
			@RequestParam(name = "iduser") Long iduser) { /* Recebe os dados para consultar */

		Usuario usuario = usuarioRepository.findById(iduser).get();

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@GetMapping(value = "buscarPorNome")
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<java.util.List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){
			

		java.util.List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());

		return new ResponseEntity<java.util.List<Usuario>>(usuario, HttpStatus.OK);
	}
}