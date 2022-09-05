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

    private int userId;
    private String location;
    private String teamName;
    private int rosterSize;
    private double winRate;
    private double ppg;
    private double papg;
//    @OneToMany(mappedBy="team", fetch=FetchType.LAZY)
//    private List<Player> players;


}
