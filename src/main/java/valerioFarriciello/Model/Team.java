package valerioFarriciello.Model;

import java.util.ArrayList;
/**
 * @author Valerio Farriciello (R00180956)
 * <p>Entity class Team<br></p>
 *
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
/**
 * @author Valerio Farriciello
 */
@Entity
public class Team {
	@Id
	private String teamID;
	private String name;
	private String jerseyColour;
	private String managerID;
	@Transient
	private final int MAXPLAYERS;
	@Transient
	private ArrayList<Player> players;
	@Transient
	private Manager manager;
	
	public Team(String teamID, String name, String jerseyColour, String managerID) {
		this.teamID = teamID;
		this.name = name;
		this.jerseyColour = jerseyColour;
		this.managerID = managerID;
		this.MAXPLAYERS = 28;
		this.players = new ArrayList<>();
		this.manager = null;
	}
	
	//DEFAULT CONSTRUCTOR FOR JPA
	public Team() {
		this.teamID = "default-ID";
		this.name = "default-name";
		this.jerseyColour = "default-colour";
		this.managerID = "default-ID";
		this.MAXPLAYERS = 28;
		this.players = new ArrayList<>();
		this.manager = null;
	}
	
	/**
	 * <p>If the team is not full and the player is not already in the team, it will add the Player object to its arrayList of players.<br></p>
	 * @param newPlayer (Player) 
	 * @return boolean
	 */
	public boolean addPlayer(Player newPlayer) {
		//Finds if a player is already in the team.
		int check = findPlayer(newPlayer);
		
		if((this.players.size() < this.MAXPLAYERS) && (check == -1)) {
			
			this.players.add(newPlayer);
			newPlayer.setTeam(this);
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * <p>A private method that checks if a player is present in the team.<br>
	 * If present it will return his/her index, otherwise -1.</p>
	 * @param player (Player)
	 * @return int (index)
	 */
	private int findPlayer(Player player) {
		
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getPlayerID().equals(player.getPlayerID())) {
				return i;
			}
		}
		return -1;
	}

	
	/**
	 * <p>Sets the manager pointer to the new manager object and the team pointer (inside the Manager class) <br>
	 * pointing at THIS team. If the manager is already in the team, returns false.</p>
	 * @param newManager (Manager)
	 * @return boolean
	 */
	public boolean addManager(Manager newManager) {
		//if this team has a manager already, check if the user is not trying to add the same manager
		if(this.manager != null) {
			if(this.manager.getManagerID().equals(newManager.getManagerID())) return false;
		}
		
		//then I add this manager to this team and set his team as THIS
		newManager.setTeam(this);
		this.manager = newManager;
		return true;
	}



	/**
	 * <p>Similar to the toString method, which returns just the basic information about the class.<br>
	 * This method instead returns all the components such as players and manager.</p>
	 * @return String
	 */
	public String getAllTheInfo() {
		String displayTeam = "TEAM: " + this.name + "- JERSEY COLOUR: " + this.jerseyColour + "\nPLAYERS:\n";
		for(int i = 0; i < players.size(); i++) {
			displayTeam += (i+1) + ") " + this.players.get(i).getName() + "\n";
		}
		if(this.manager != null) {
			displayTeam += "MANAGER: " + this.manager.getName();
		}
		else displayTeam += "NO MANAGER AT THE MOMENT";
		
		displayTeam += "\n------------------------------------------";
		return displayTeam;
	}
	
	@Override
	public String toString() {
		return "TEAM: " + this.name + "- JERSEY COLOUR: " + this.jerseyColour;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJerseyColour() {
		return jerseyColour;
	}

	public void setJerseyColour(String jerseyColour) {
		this.jerseyColour = jerseyColour;
	}

	public String getManagerID() {
		return managerID;
	}

	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public int getMAXPLAYERS() {
		return MAXPLAYERS;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

}
