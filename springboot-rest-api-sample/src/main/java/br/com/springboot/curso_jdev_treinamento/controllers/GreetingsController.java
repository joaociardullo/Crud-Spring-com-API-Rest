package br.com.springboot.curso_jdev_treinamento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@PostMapping(value = "salvar")/*Mapeia a URL   Enviar os dados dentro da requisição via post*/ 
    @ResponseBody/*Descrição da resposta*/
    public ResponseEntity<Usuario>salvar(@RequestBody Usuario usuario){
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
   	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }

}
