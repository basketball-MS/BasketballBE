package com.revatureproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coach")
public class Coach
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coach_id;

    private String firstname;
    private String lastname;

    private int exp;
    private int team_id;
    private int roleId;
    private int userId;

}
