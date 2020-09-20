package valerioFarriciello.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Valerio Farriciello
 * <p>Entity class PlayersAssignment<br></p>
 *
 */
@Entity
public class PlayersAssignment {
	@Id
	private int playerAssignmentID;
	private String teamID;
	private String playerID;
	
	public PlayersAssignment(int playerAssignmentID, String teamID, String playerID) {
		this.playerAssignmentID = playerAssignmentID;
		this.teamID = teamID;
		this.playerID = playerID;
	}
	//DEFAULT CONSTRUCTOR FOR JPA
	public PlayersAssignment() {
		this.playerAssignmentID = -1;
		this.teamID = "default-ID";
		this.playerID = "default-ID";
	}

	public int getPlayerAssignmentID() {
		return playerAssignmentID;
	}

	public void setPlayerAssignmentID(int playerAssignmentID) {
		this.playerAssignmentID = playerAssignmentID;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}
	
	
	
}
