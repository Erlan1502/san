package com.sanreg.sanatorium.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 50, message = "Имя не должно превышать 50 символов")
    @NotEmpty(message = "Имя не должно быть пустым")
    @NotNull(message = "Имя не должно быть пустым")
    private String username;
    @Size(max = 50, message = "Почта не должна превышать 50 символов")
    @NotEmpty(message = "Почта не должна быть пустой")
    @NotNull(message = "Почта не должна быть пустой")
    private String email;
    @NotEmpty(message = "Пароль не должен быть пустой")
    private String password;
    private boolean active;
    private String activationCode;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<Role> roles = new ArrayList<>();

}
