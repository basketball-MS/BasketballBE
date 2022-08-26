package com.revatureproject.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teams
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int id;

    private String teamName;

    private String location;
    private int rosterSize;
    private double winRate;
    private double ppg;
    private double papg;
    @OneToMany(mappedBy = "teams")
    private Set<Players> players;


}
