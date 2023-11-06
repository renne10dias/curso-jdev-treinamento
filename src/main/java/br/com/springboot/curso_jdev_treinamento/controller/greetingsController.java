package br.com.springboot.curso_jdev_treinamento.controller;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class greetingsController {

    @Autowired/* IC/CD  ou CDI - Injeção de dependencia*/
    private UsuarioRepository usuarioRepository;


    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornoOlaMundo(@PathVariable String nome) {

        Usuario usuario = new Usuario();
        usuario.setNome(nome);

        usuarioRepository.save(usuario); /*gravar no banco de dados*/

        return "Ola Mundo " + nome;
    }
    @GetMapping(value = "Listatodos") /* Nosso primeiro método de API*/
    @ResponseBody /*retorna os dados do banco para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> ListaUsuario(){
        List<Usuario> usuarios = usuarioRepository.findAll(); /*execulta a consulta no banco de dados*/
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /*retorna a lista em json*/
    }

    @PostMapping(value = "salvar") // mapeia a URL
    @ResponseBody // faz um retorno pra minha requisição  == descriçao da resposta
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){ // recebe os dados para salvar
        Usuario user = usuarioRepository.save(usuario);
        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "atualizar") // mapeia a URL
    @ResponseBody // faz um retorno pra minha requisição  == descriçao da resposta
    public ResponseEntity<?> atualizar(@RequestBody @NotNull Usuario usuario){ // recebe os dados para salvar
        if (usuario.getId() == 0){
            return new ResponseEntity<String>("id não informado para a atualização", HttpStatus.OK);
        }
        Usuario user = usuarioRepository.saveAndFlush(usuario);
        return new ResponseEntity<Usuario>(user, HttpStatus.OK);

    }


    @DeleteMapping(value = "delete") // mapeia a URL
    @ResponseBody // faz um retorno pra minha requisição  == descriçao da resposta
    public ResponseEntity<String> delete(@RequestParam long iduser){ // recebe os dados para salvar
        usuarioRepository.deleteById(iduser);
        return new ResponseEntity<String>("Usuario deletedo com sucesso", HttpStatus.OK);
    }

    @GetMapping(value = "buscaruserid") // mapeia a URL
    @ResponseBody // faz um retorno pra minha requisição  == descriçao da resposta
    public ResponseEntity<Usuario> buscauserid(@RequestParam(name = "iduser") long iduser){ // recebe os dados para salvar
        Usuario usuario = usuarioRepository.findById(iduser).get();
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }


    @GetMapping(value = "buscarPorNome") // mapeia a URL
    @ResponseBody // faz um retorno pra minha requisição  == descriçao da resposta
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){ // recebe os dados para salvar
        List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase()); // .trim() retira os espaços
        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }


    /*
    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public String  check(){

        return "STATUS: Online";
    }

     */

}

