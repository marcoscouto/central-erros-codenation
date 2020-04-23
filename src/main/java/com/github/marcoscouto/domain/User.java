package com.github.marcoscouto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.marcoscouto.domain.enums.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome é obrigatório")
    private String name;

    @NotEmpty(message = "CPF é obrigatório")
    @CPF(message = "CPF precisa ser válido")
    @Size(max = 11, min = 11, message = "CPF deve ter 11 caracteres")
    @Column(unique = true)
    private String cpf;

    @NotNull(message = "Data de nascimento obrigatória")
    @Past(message = "Data de nascimento não pode ser data futura")
    private LocalDate dateBirth;

    @NotEmpty(message = "Email é obrigatório")
    @Email(message = "Email precisa ser válido")
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @NotEmpty(message = "Senha é obrigatória")
    private String password;

    private Integer profile;

    public ProfileEnum getProfile() {
        return ProfileEnum.toEnum(profile);
    }

    public void setProfile(ProfileEnum profile) {
        this.profile = profile.getCode();
    }
}
