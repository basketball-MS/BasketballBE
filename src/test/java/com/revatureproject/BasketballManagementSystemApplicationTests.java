package com.revatureproject;

import com.revatureproject.exception.UserNotFoundException;
import com.revatureproject.models.Lineup;
import com.revatureproject.models.Players;
import com.revatureproject.models.Teams;
import com.revatureproject.models.Users;
import com.revatureproject.repository.LineupRepo;
import com.revatureproject.repository.PlayerRepo;
import com.revatureproject.repository.TeamsRepo;
import com.revatureproject.repository.UserRepo;
import com.revatureproject.service.LineupService;
import com.revatureproject.service.PlayersService;
import com.revatureproject.service.TeamService;
import com.revatureproject.service.UserService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

//@SpringBootTest
//class BasketballManagementSystemApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
@ExtendWith(MockitoExtension.class)
public class BasketballManagementSystemApplicationTests{

	@Mock
	LineupRepo mockLineupRepo;
	@Mock
	PlayerRepo mockPlayerRepo;
	@Mock
	TeamsRepo mockTeamsRepo;
	@Mock
	UserRepo mockUserRepo;

	@InjectMocks
	LineupService lServe;
	@InjectMocks
	PlayersService pServe;
	@InjectMocks
	TeamService tServe;
	@InjectMocks
	UserService uServe;

	Lineup dummyLineup;
	Players dummyPlayers;
	Teams dummyTeams;
	Users dummyUsers;

	@BeforeEach
	void setUp() throws Exception{
		this.dummyLineup = new Lineup(1, "dummyName",
				1,2,3,4,5);

		this.dummyPlayers = new Players(1,"dummyfirstname","dummylastname","dummyposition",
			10,100,6.4,
				5,6,7,8,9, new Teams(1, 1, "dummylocation", "dummyteamName", 10,
				.5, 3, 3.5));

		this.dummyTeams = new Teams(1,1,"dummylocation","dummyteamName",10,
				.5,3,3.5);
		this.dummyUsers = new Users(1,"dummyfirstname","dummylastname",100,"dummyRole",
				"dummyusername","dummypassword","dummy@mail.com");
	}

	@AfterEach
	void tearDown() throws Exception{
		this.dummyLineup = null;
		this.dummyPlayers = null;
		this.dummyTeams = null;
		this.dummyUsers = null;
	}

	@Test
	void testLineupGetById_Success(){
		int lineup_id = 1;
		given(this.mockLineupRepo.findById(lineup_id)).willReturn(Optional.of(this.dummyLineup));
		Lineup expected = this.dummyLineup;
		Lineup actual = this.lServe.getById(lineup_id);

		assertEquals(expected,actual);
	}

	@Test
	void testPlayersGetById_Success(){
		int id = 1;
		given(this.mockPlayerRepo.findById(id)).willReturn(Optional.of(this.dummyPlayers));
		Players expected = this.dummyPlayers;
		Players actual = this.pServe.getById(id);

		assertEquals(expected,actual);
	}
	@Test
	void testTeamsGetById_Success(){
		int id = 1;
		given(this.mockTeamsRepo.findById(id)).willReturn(Optional.of(this.dummyTeams));
		Teams expected = this.dummyTeams;
		Teams actual = this.tServe.getById(id);

		assertEquals(expected,actual);
	}
	@Test
	void testUsersGetById_Success(){
		int id = 1;
		given(this.mockUserRepo.findById(id)).willReturn(Optional.of(this.dummyUsers));
		Users expected = this.dummyUsers;
		Users actual = this.uServe.getById(id);

		assertEquals(expected,actual);
	}
	///////////////////////////////////////
	//Failure Checks
	///////////////////////////////////////
	@Test
	void testLineupGetById_Failure_IdLessThanZero(){
		int id = -1;
		assertNull(this.lServe.getById(id));
	}
	@Test
	void testLineupGetById_Failure_UserNotFoundException(){
		int id = 2;
		given(this.mockLineupRepo.findById(id)).willReturn(Optional.empty());

		try{
			this.lServe.getById(id);
		}catch (Exception e){
			assertEquals(UserNotFoundException.class, e.getClass());
		}
	}

	@Test
	void testPlayersGetById_Failure_IdLessThanZero(){
		int id = -1;
		assertNull(this.pServe.getById(id));
	}
	@Test
	void testPlayersGetById_Failure_UserNotFoundException(){
		int id = 2;
		given(this.mockPlayerRepo.findById(id)).willReturn(Optional.empty());

		try{
			this.pServe.getById(id);
		}catch (Exception e){
			assertEquals(UserNotFoundException.class, e.getClass());
		}
	}

	@Test
	void testTeamsGetById_Failure_IdLessThanZero(){
		int id = -1;
		assertNull(this.tServe.getById(id));
	}
	@Test
	void testTeamsGetById_Failure_UserNotFoundException(){
		int id = 2;
		given(this.mockTeamsRepo.findById(id)).willReturn(Optional.empty());

		try{
			this.tServe.getById(id);
		}catch (Exception e){
			assertEquals(UserNotFoundException.class, e.getClass());
		}
	}

	@Test
	void testUsersGetById_Failure_IdLessThanZero(){
		int id = -1;
		assertNull(this.uServe.getById(id));
	}
	@Test
	void testUsersGetById_Failure_UserNotFoundException(){
		int id = 2;
		given(this.mockUserRepo.findById(id)).willReturn(Optional.empty());

		try{
			this.uServe.getById(id);
		}catch (Exception e){
			assertEquals(UserNotFoundException.class, e.getClass());
		}
	}


	/////////////////////////
	//Get Checks for Users
	/////////////////////////


	//You cannot get by X if there is no optional for it
	@Test
	void testGetUsernames_Success(){
		String username = "dummyusername";
		given(this.mockUserRepo.findByUsername(username)).willReturn(Optional.of(this.dummyUsers));

		Users expected = this.dummyUsers;
		Users actual = this.uServe.getByUsername(username);

		assertEquals(expected,actual);

	}
	@Test
	void testGetUsernames_Failure_UserNotFoundException(){
		String username = "johnwest";
		given(this.mockUserRepo.findByUsername(username)).willReturn(Optional.empty());

		try{
			this.uServe.getByUsername(username);
		}catch(Exception e){
			assertEquals(UserNotFoundException.class,e.getClass());
		}
	}



}
