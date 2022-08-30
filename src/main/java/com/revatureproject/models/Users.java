package com.revatureproject.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Users
{
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private int exp;
    //@NotBlank
    private String role;

    @NotBlank
    @Length(max=50)
    @Pattern(regexp="[a-zA-Z][a-zA-Z0-9]*")
    @Column(unique=true)
    private String username;

    @NotBlank
    @Column(name="pwd")
    private String password;

    @Email
    @Column(unique=true)
    private String email;




}
