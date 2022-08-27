package com.revatureproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Lineup")
public class Lineup
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lineup_id;
    private String name;

    private int player_id1;
    private int player_id2;
    private int player_id3;
    private int player_id4;
    private int player_id5;

}
