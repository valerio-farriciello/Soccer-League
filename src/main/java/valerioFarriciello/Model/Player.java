package valerioFarriciello.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Valerio Farriciello
 * <p>Entity class Player<br></p>
 *
 */
@Entity
public class Player extends Person implements Comparable<Object>{
	@Id
	private String playerID;
	private int goals;
	private boolean isGoalie;
	@Transient
	private Team team;
	
	public Player(String playerID, Name name, String phone, String email, int goals, boolean isGoalie) {
		super(name, phone, email);
		this.playerID = playerID;
		this.goals = goals;
		this.isGoalie = isGoalie;
		this.team = null;
		
	}
	
	//DEFAULT CONSTRUCTOR FOR JPA
	public Player() {
		super(new Name("default-fname", "default-mname", "default-lname"), "default-phone", "default-email");
		this.playerID = "default-ID";
		this.goals = -1;
		this.isGoalie = false;
		this.team = null;
		
	}
	
	




	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public boolean isGoalie() {
		return isGoalie;
	}

	public void setGoalie(boolean isGoalie) {
		this.isGoalie = isGoalie;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		String myTeam = "Team: NO TEAM";
		if(this.team != null)  myTeam = "Team: " + this.team.getName() + ".";
		if(this.isGoalie) {
			return this.getName() + "- PhoneNumber: " + super.getPhone() + " - Email: " + super.getEmail() +" - Goalie: YES - Goals conceded: " + this.goals + " - " + myTeam;
		}
		
		return this.getName() + "- PhoneNumber: " + super.getPhone() + " - Email: " + super.getEmail() + " - Goalie: NO - Goals scored: " + this.goals + " - " + myTeam;
	}

	@Override
	public int compareTo(Object o) {
		return this.getName().compareTo(((Player)o).getName());
	}


	
}
