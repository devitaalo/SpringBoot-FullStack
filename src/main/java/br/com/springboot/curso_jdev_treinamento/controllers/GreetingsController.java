package br.com.springboot.curso_jdev_treinamento.controllers;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GreetingsController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaHelloWorld(@PathVariable String nome) {

        Usuario usuario = new Usuario();

        usuario.setNome(nome);

        usuarioRepository.save(usuario);

        return "Olá mundo para você: " + nome;
    }

    @GetMapping(value = "/listatodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuario(){

        List<Usuario> usuarios = usuarioRepository.findAll();

        return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
    }

    @PostMapping(value = "/salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        Usuario user = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(user,HttpStatus.CREATED);
    }

    @PostMapping(value = "/atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){

        if(usuario.getId() == null){
            return new ResponseEntity<String>("ID não foi informado",HttpStatus.BAD_REQUEST);
        }

        Usuario user = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<Usuario>(user,HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long id){
        usuarioRepository.deleteById(id);

        return new ResponseEntity<String>("user deletado com sucesso",HttpStatus.OK);
    }

    @GetMapping(value = "/buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser){
        Usuario usuario = usuarioRepository.findById(iduser).get();

        return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
    }

    @GetMapping(value = "/buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) {

        List<Usuario> usuarios = usuarioRepository.buscarPorNome(name.trim().toUpperCase());

        return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
    }
}
