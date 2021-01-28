package com.springboot.springboot.Repository;

import com.springboot.springboot.model.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.login = ?1")
    Usuario findUserByLogin(String login);

    @Modifying
    @Query(
            value = "INSERT INTO usuario_role (usuario_id, role_id) VALUES ((SELECT MAX (id) from usuario),1)",
            nativeQuery = true)
    void salveRole();

    @Query(
            value = "SELECT MAX (id) from usuario",
            nativeQuery = true)
    Long lastId();

    @Query(
            value = "INSERT INTO public.usuario(login, senha) VALUES (?, ?)",
            nativeQuery = true
    )
    boolean salvarUsuario(String login, String senha);
}
