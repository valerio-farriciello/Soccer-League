package valerioFarriciello.Controller;


import java.util.ArrayList;
import java.util.Collections;

import valerioFarriciello.Model.*;


/**
 * @author Valerio Farriciello
 * <p>This class is in charge of taking, validating and passing the data between the database and the view components.</p>
 *
 */
public class League {
	private Database db;
	private ArrayList<Player> allThePlayers;
	private ArrayList<Manager> allTheManagers;
	private ArrayList<Team> allTheTeams;
	private ArrayList<PlayersAssignment> allTheAssignments;
	
	/**
	 * <p>Sets all the private attributes to null and implements them by calling the loadDB method.<br></p>
	 */
	public League() {
		this.db = null;
		this.allThePlayers = null;
		this.allTheManagers = null;
		this.allTheTeams = null;
		this.allTheAssignments = null;
		loadDB();
	}
	
	
	
	
	/**
	 * <p>Validates the attributes for the creation of a player object. If the attributes are valid, it sends them to<br>
	 * to the database object that will store them into the database.<br>
	 * Note that the second parameter playerID works as a flag to indicate if the player exists.<br>
	 * If it is the case then only the attributes of the player will be updated inside the database.
	 * </p>
	 * @param playerDetails (array of Objects that will be casted into the corresponding parameters)
	 * @param playerID
	 * @return boolean
	 */
	public boolean createANewPlayer(Object[] playerDetails, String playerID) {
		Player newPlayer = null;
		String firstName, middleName, lastName, phone, email;
		int goals;
		boolean isGoalie = false;
		
		if((((String)playerDetails[0]).length() > 2) && (((String)playerDetails[0]).length() <= 40)) {
			firstName = ((String) playerDetails[0]).substring(0, 1).toUpperCase() + ((String) playerDetails[0]).substring(1);
		} else return false;
		
		if(((String)playerDetails[1]).length() < 40) {
			middleName = (String) playerDetails[1];
			if(middleName.length() > 2) {
				middleName = middleName.substring(0, 1).toUpperCase() + middleName.substring(1);
			}
			else middleName = null;
		} else return false;
		
		if((((String)playerDetails[2]).length() > 2) && (((String)playerDetails[2]).length() <= 40)) {
			lastName = ((String) playerDetails[2]).substring(0, 1).toUpperCase() + ((String) playerDetails[2]).substring(1);
		} else return false;
		
		if((((String)playerDetails[3]).length() > 3) && (((String)playerDetails[3]).length() <= 25)) {
			phone = (String) playerDetails[3];
		} else return false;
		
		if((((String)playerDetails[4]).length() > 9) && (((String)playerDetails[4]).length() <= 25)) {
			email = (String) playerDetails[4];
		} else return false;
		
		try {
			goals = Integer.parseInt(((String)playerDetails[5]));
			
			if(goals < 0 || goals > 100 ) {
				System.out.print("ERROR: GOALS NOT VALID");
				return false;	
			}
		}
		catch(NumberFormatException e) {
			System.out.print("ERROR: ONLY NUMBERS ALLOWED");
			return false;
		}
		
		
		
		if(playerDetails[6] == null) return false;
		else if(((String)playerDetails[6]).equals("YES")) {
			isGoalie = true;		
		}
		else if (((String)playerDetails[6]).equals("NO")){
			isGoalie = false;
		}
		
		
		if(playerID == null) {
			//generating a new playerID	
			int id;
			
			if(this.allThePlayers.size() == 0) {
				id = 0;
			}
			else {
				id = Integer.parseInt(this.allThePlayers.get(this.allThePlayers.size()-1).getPlayerID().substring(3));
			}
			
			playerID = "PL_" + (id+1);
			
			newPlayer = new Player(playerID, new Name(firstName, middleName, lastName), phone, email, goals, isGoalie);
			this.db.createAPlayer(newPlayer);
		}
		else {	
			this.db.modifyAPlayer(playerID, firstName, middleName, lastName, phone, email, goals, isGoalie);
		}
		
		this.loadDB();
		
		return true;
	}
	
	/**
	 * <p>Validates the attributes for the creation of a manager object. If the attributes are valid, it sends them to<br>
	 * to the database object that will store them into the database.<br>
	 * Note that the second parameter managerID works as a flag to indicate if the manager exists.<br>
	 * If it is the case then only the attributes of the manager will be updated inside the database.
	 * </p>
	 * @param managerDetails (array of Objects that will be casted into the corresponding parameters)
	 * @param managerID
	 * @return boolean
	 */
	public boolean createANewManager(Object[] managerDetails, String managerID) {
		Manager newManager = null;
		String firstName, middleName, lastName, phone, email, date;
		int starRating;
		
		if((((String)managerDetails[0]).length() > 2) && (((String)managerDetails[0]).length() <= 40)) {
			firstName = ((String) managerDetails[0]).substring(0, 1).toUpperCase() + ((String) managerDetails[0]).substring(1);
		} else return false;
		
		if(((String)managerDetails[1]).length() <= 40) {
			middleName = (String) managerDetails[1];
			if(middleName.length() > 2) {
				middleName = middleName.substring(0, 1).toUpperCase() + middleName.substring(1);
			}
			else middleName = null;
			
		} else return false;
		
		if((((String)managerDetails[2]).length() > 2) && (((String)managerDetails[2]).length() <= 40)) {
			lastName = ((String) managerDetails[2]).substring(0, 1).toUpperCase() + ((String) managerDetails[2]).substring(1);
		} else return false;
		
		if((((String)managerDetails[3]).length() > 3) && (((String)managerDetails[3]).length() <= 25)) {
			phone = (String) managerDetails[3];
		} else return false;
		
		if((((String)managerDetails[4]).length() > 9) && (((String)managerDetails[4]).length() <= 25)) {
			email = (String) managerDetails[4];
		} else return false;
		
		if(managerDetails[5] != null) {
			date = (String) managerDetails[5];
		} else return false;
		
		try {
			starRating = Integer.parseInt(((String)managerDetails[6]));
			
			if(starRating < 1 || starRating > 10 ) {
				System.out.print("ERROR: STAR RATING NOT VALID");
				return false;	
			}
		}
		catch(NumberFormatException e) {
			System.out.print("ERROR: ONLY NUMBERS ALLOWED");
			return false;
		}
		
		if(managerID == null) {
			//generating a new managerID
			int id;
			if(this.allTheManagers.size() == 0) {
				id = 0;
			}
			else {
			
				id = Integer.parseInt(this.allTheManagers.get(this.allTheManagers.size()-1).getManagerID().substring(3));
			}
			
			managerID = "MA_" + (id+1);
			
			newManager = new Manager(managerID, new Name(firstName, middleName, lastName), phone, email, date, starRating);
			
			this.db.createAManager(newManager);
		}
		else {
			this.db.modifyAManager(managerID, firstName, middleName, lastName, phone, email, date, starRating);
		}

		loadDB();
		return true;
		
	}
	
	
	/**
	 * <p>Takes an array of objects that will be validated and casted into the corresponding Team attributes.<br>
	 * If the attributes are valid, the method will pass them to the database object that will store them into<br>
	 * the database and it will return true</p>
	 * @param teamDetails (array of Objects)
	 * @return boolean
	 */
	public boolean createANewTeam(Object[] teamDetails) {
		Team newTeam = null;
		String teamID, name, jerseyColour;
		
		if((((String)teamDetails[0]).length() > 2) && (((String)teamDetails[0]).length() <= 40)) {
			name = (String) teamDetails[0];
		} else return false;
		
		if((((String)teamDetails[1]).length() > 2) && (((String)teamDetails[1]).length() <= 40)) {
			jerseyColour = (String) teamDetails[1];
		} else return false;
		
		//generating a new teamID
		int id;
		if(this.allTheTeams.size() == 0) {
			id = 0;
		}
		else {
		
			id = Integer.parseInt(this.allTheTeams.get(this.allTheTeams.size()-1).getTeamID().substring(3));
		}
		
		teamID = "TM_" + (id+1);
		
		newTeam = new Team(teamID, name, jerseyColour, null);
		
		this.db.createATeam(newTeam);
		loadDB();
		return true;
	}
	
	
	
	/**
	 * <p>This method is responsible for the loading the database.<br>
	 * It is responsible for taking the data from the database (accessed by the database object)<br>
	 * and assign each single player and manager to a team <br></p>
	 * 
	 */
	public void loadDB() {
		this.db = new Database();
		this.allThePlayers = this.db.getAllThePlayers();
		this.allTheManagers = this.db.getAllTheManagers();
		this.allTheTeams = this.db.getAllTheTeams();
		this.allTheAssignments = this.db.getPlayersAssignment();
		
		for(int i = 0; i < allTheAssignments.size(); i++) {
			Player playerToBeAssigned = null;
			Team teamToBeAssigned = null;
			
			//getting the player that has to be assigned
			for(int j = 0; j < this.allThePlayers.size(); j++) {
				if(allTheAssignments.get(i).getPlayerID().equals(this.allThePlayers.get(j).getPlayerID())) {
					playerToBeAssigned = this.allThePlayers.get(j);
					//getting the team
					for(int z = 0; z < this.allTheTeams.size(); z++) {
						if(allTheAssignments.get(i).getTeamID().equals(this.allTheTeams.get(z).getTeamID())) {
							teamToBeAssigned = this.allTheTeams.get(z);
							//if team is found then break;
							break;
						}
					}
					//if player is found then break
					break;
				}
			}
			
			//assigning player and team
			teamToBeAssigned.addPlayer(playerToBeAssigned);
		}
			
		//assign managers
		for(int i = 0; i < this.allTheTeams.size(); i++) {
			for(int j = 0; j < this.allTheManagers.size(); j++) {
				if(this.allTheTeams.get(i).getManagerID() == null) break;
				else if(this.allTheTeams.get(i).getManagerID().equals(this.allTheManagers.get(j).getManagerID())) {
					this.allTheTeams.get(i).addManager(this.allTheManagers.get(j));
					break;
				}
			}
		}
		
	}
	
	
	/**
	 * <p>This method is responsible to validate the player assignment to a team. It communicates to the team object and<br>
	 * if the assignment is possible, it will communicate to the database object that will create a new instance<br>
	 * in the database under the table PlayersAssignment<br></p>
	 * @param player (Player)
	 * @param team (Team)
	 * @return boolean
	 */
	public boolean assignPlayerToATeam(Player player, Team team) {
		
		//checking if the player is not already in the team and if it's possible to add him/her
		if(team.addPlayer(player)) {
			
			/*first of all I call the method inside the controller class that will remove 
			 * any instance of association between player and team from the database in order to save space
			*/
			this.removePlayerFromTheTeam(player, false);
			
			this.db.addPlayerToATeam(player.getPlayerID(), team.getTeamID());
			this.loadDB();
			return true;
		}
		
		return false;
	}


	/**
	 * <p>Responsible to validate the manager assignment for the team. If the manager is already in the team<br>
	 * it will return false, otherwise it will remove the manager from his/her current team, if present.<br>
	 * Then it will add the manager to the new team<br></p>
	 * @param manager (Manager)
	 * @param team (Team)
	 * @return boolean
	 */
	public boolean assignManagerToATeam(Manager manager, Team team) {
		
		//checking if the manager is not already in the team and if it's possible to add him/her
		if(team.addManager(manager)) {
			
			//Removes any instance of association between manager and team from the database in order to save space
			this.removeManagerFromTheTeam(manager, false);
			
			this.db.addManagerToATeam(manager.getManagerID(), team.getTeamID());
			this.loadDB();
			return true;
		}
		
		return false;
	}

	
	
	/**
	 * <p>Removes the player from the team.<br>
	 * It first will find the assignmentID in order to be passed into the database object. Once the assignment ID<br>
	 * is found, the database object will remove that instance.<br>
	 * The refreshDB boolean value is used to know if the application needs to be refreshed and updated.<br>
	 * The reason why this is an option is that we don't always want to refresh the database immediately, since<br>
	 * this method will be called anyway right after a new assignment, therefore it will be a waste of time<br>
	 * refreshing the database twice after a few milliseconds</p>
	 * @param player (Player)
	 * @param refreshDB (boolean)
	 */
	public void removePlayerFromTheTeam(Player player, boolean refreshDB) {
		if(player.getTeam() != null) {
			ArrayList<PlayersAssignment> playersAssignment = this.db.getPlayersAssignment();
			for(int i = 0; i < playersAssignment.size(); i++) {
				if(playersAssignment.get(i).getPlayerID().equals(player.getPlayerID())) {
					this.db.removePlayerFromTheTeam(playersAssignment.get(i).getPlayerAssignmentID());
					if(refreshDB) loadDB();
					return;
				}
			}
		}
		


	}
	
	
	/**
	 * <p>Checks if the manager is in the team already, if it is the case the database object will be called<br>
	 * and it will remove the manager from the team.<br>
	 * The reason why this is an option is that we don't always want to refresh the database immediately, since<br>
	 * this method will be called anyway right after a new assignment, therefore it will be a waste of time<br>
	 * refreshing the database twice after a few milliseconds</p>
	 * @param manager
	 * @param refreshDB
	 */
	public void removeManagerFromTheTeam(Manager manager, boolean refreshDB) {
		if(manager.getTeam() != null) {
			this.db.removeManagerFromTheTeam(manager.getTeam().getTeamID());
			
		}
		
		if(refreshDB) this.loadDB();
	}
	

	public ArrayList<Player> getAllThePlayers() {
		return allThePlayers;
	}




	public ArrayList<Manager> getAllTheManagers() {
		return allTheManagers;
	}




	public ArrayList<Team> getAllTheTeams() {
		return allTheTeams;
	}
	
	
	
	/**
	 * <p>This method is used to get a String that will be used to list all the players.<br>
	 * This method is used in the DisplayTab class in order to display the players into the text area.<br></p>
	 * @return String
	 */
	public String getAllThePlayersToDisplay() {
		String result = "";
		
		if(this.allThePlayers.size() == 0) return "0 RESULTS...PLEASE TRY AGAIN";
		
		for(int i = 0; i < this.allThePlayers.size(); i++) {
			result += this.allThePlayers.get(i).toString() + "\n----------------------------------------------------------------------------------------------------------------------------------------\n";
		}
		
		return result;
	}
	
	/**
	 * <p>This method is used to get a String that will be used to list all the managers.<br>
	 * This method is used in the DisplayTab class in order to display the managers into the text area.<br></p>
	 * @return String
	 */
	public String getAllTheManagersToDisplay() {
		String result = "";
		
		if(this.allTheManagers.size() == 0) return "0 RESULTS...PLEASE TRY AGAIN";
		
		for(int i = 0; i < this.allTheManagers.size(); i++) {
			result += this.allTheManagers.get(i).toString() + "\n----------------------------------------------------------------------------------------------------------------------------------------\n";
		}
		
		return result;
	}
	
	/**
	 * <p>This method is used to get a String that will be used to list all the managers.<br>
	 * This method is used in the DisplayTab class in order to display the managers into the text area ORDERED BY STAR RATING.<br></p>
	 * @return String
	 */
	public String getAllTheManagersToDisplayOrderedByStars() {
		String result = "";
		if(this.allTheManagers.size() == 0) return "0 RESULTS...PLEASE TRY AGAIN";
		
		ArrayList<Manager> managersOrderedByStarRating = new ArrayList<>();
		
		for(int i = 0; i < this.allTheManagers.size(); i++) {
			managersOrderedByStarRating.add(this.allTheManagers.get(i));
		}
		
		Collections.sort(managersOrderedByStarRating);
		
		
		for(int i = 0; i < managersOrderedByStarRating.size(); i++) {
			result += managersOrderedByStarRating.get(i).toString() + "\n----------------------------------------------------------------------------------------------------------------------------------------\n";
		}
		
		return result;
	}
	
	/**
	 * <p>This method is used to get a String that will be used to list all the players from a specific team.<br>
	 * This method is used in the DisplayTab class in order to display the players into the text area ORDERED BY THEIR NAMES.<br></p>
	 * @return String
	 */
	public String getAllThePlayersToDisplayInATeam(Team team) {
		String result = "";
		ArrayList<Player> players = team.getPlayers();
		
		Collections.sort(players);
		
		if(players.size() == 0) return "0 RESULTS...PLEASE TRY AGAIN";
		
		for(int i = 0; i < players.size(); i++) {
			result += players.get(i).toString() + "\n----------------------------------------------------------------------------------------------------------------------------------------\n";
		}
		
		return result;
	}
	
	/**
	 * <p>This method is used to get a String that will be used to "list" all the teams.<br>
	 * This method is used in the DisplayTab class in order to display the teams into the text area.<br></p>
	 * @return String
	 */
	public String getTeamsToDisplay() {
		String result = "";
	
		if(this.allTheTeams.size() == 0) return "NO TEAMS AT THE MOMENT";
		
		for(int i = 0; i < this.allTheTeams.size(); i++) {
			result += this.allTheTeams.get(i).getAllTheInfo() + "\n";
		}
		
		return result;
	}
	
	
	
	/**
	 * <p>Takes a String as parameter that will be passed in the database object that will query all the<br>
	 * results of players and manager that have the characters passed from the String inside their names.<br>
	 * Then all the players and managers retrieved from the database will be returned in a form of String (list)<br>
	 * in order to be displayed.</p>
	 * @param name (String)
	 * @return String
	 */
	public String getPlayersAndManagersByName(String name){
		String result = "";
		ArrayList<Player> players = this.db.queryPlayersByName(name);
		ArrayList<Manager> managers = this.db.queryManagersByName(name);
		
		if((players.size() + managers.size()) == 0) return "0 RESULTS...PLEASE TRY AGAIN";
		
		for(int i = 0; i < players.size(); i++) {
			result += players.get(i).toString() + "\n----------------------------------------------------------------------------------------------------------------------------------------\n";
		}
		
		for(int i = 0; i < managers.size(); i++) {
			result += managers.get(i).toString() + "\n----------------------------------------------------------------------------------------------------------------------------------------\n";
		}
		
		return result;
	}
	
	
	
	/**
	 * <p>It will take a player object that has been selected from the VIEW components and will find any possible assignment in a team.<br>
	 * In case an assignment is present, then both the playerID and assignmentID will be passed to the database object in order <br>
	 * to be deleted.</p>
	 * @param playerToDelete (Player)
	 */
	public void deletePlayer(Player playerToDelete) {
		String playerID = playerToDelete.getPlayerID();
		int assignmentID = -1;
		for(int i = 0; i < this.allTheAssignments.size(); i++) {
			if(this.allTheAssignments.get(i).getPlayerID().equals(playerID)) {
				assignmentID = this.allTheAssignments.get(i).getPlayerAssignmentID();
				break;
			}
		}
		
		this.db.deletePlayer(playerID, assignmentID);
		this.loadDB();
	}
	
	/**
	 * <p>It will take a manager object that has been selected from the VIEW components and checks if he/she is in a team.<br>
	 * If the team is present, then both the managerID and playerID will be passed to the database object in order <br>
	 * to delete the instance of the manager and set the value managerID inside the team table to null.</p>
	 * @param managerToDelete (Manager)
	 */
	public void deleteManager(Manager managerToDelete) {
		String teamID = null;
		if(managerToDelete.getTeam() != null) {
			teamID = managerToDelete.getTeam().getTeamID();
		}
		
		this.db.deleteManager(managerToDelete.getManagerID(), teamID);
		this.loadDB();
	}
	
	
	
	/**
	 * <p>Get a team object selected from the VIEW components and then passes it to the database object that will be <br>
	 * in charge of removing all the existing instances in the PlayersAssignment table.</p>
	 * @param teamToDelete (Team)
	 */
	public void deleteTeam(Team teamToDelete) {
		this.db.deleteTeam(teamToDelete.getTeamID());
		this.loadDB();
	}
	
	
	public void close() {
		this.db.closeConnection();
	}
}
