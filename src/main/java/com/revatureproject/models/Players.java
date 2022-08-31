package com.revatureproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "Players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Players
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int id;

    private String firstname;
    private String lastname;
    private String position;
    private double weight;
    private double height_inches;
    private double height_feets;
    private double ppg;
    private double apg;
    private double spg;
    private double rpg;
    private double bpg;
    @JoinColumn(name = "team_id")
    private int team_id;
//    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)JoinColumn(name="team_foreign_key")
//    private Team team;


}
