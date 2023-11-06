package br.com.springboot.curso_jdev_treinamento.repository;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // FAZ PARTE DA FUNÇÃO QUE VAI BUSCAR PELO NOME
    @Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
    List<Usuario> buscarPorNome(String name);
}

// upper - transforma pra maiusculo
// trim - retira os esoaços
