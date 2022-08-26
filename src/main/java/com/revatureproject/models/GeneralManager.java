package com.revatureproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GM")
public class GeneralManager
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gm_id;
    private String name;
    private String firstname;
    private String lastname;
    private int roleId;
    private int userId;
}
