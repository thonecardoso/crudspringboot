package com.springboot.springboot.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome não pode ser nulo!")
    @NotEmpty(message = "Nome não pode ser vázio!")
    private String login;

    @NotNull(message = "Nome não pode ser nulo!")
    @NotEmpty(message = "Nome não pode ser vázio!")
    private String senha;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_role",
            uniqueConstraints =@UniqueConstraint(columnNames = {"usuario_id",  "role_id"}, name = "unique_role_user"),
            joinColumns = @JoinColumn(
                    name = "usuario_id",
                    referencedColumnName = "id",
                    table = "usuario"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id",
                    table = "role"
            )
    )
    private List<Role> roles;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name="usuario_role", uniqueConstraints = @UniqueConstraint(columnNames = { "usuario_id", "role_id" }, name = "unique_role_user"),
//            joinColumns = @JoinColumn(name="usuario_id",referencedColumnName = "id",table="usuario"),
//            inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id",table="role"))
//    private List<Role> roles;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
