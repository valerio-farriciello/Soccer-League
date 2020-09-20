package valerioFarriciello.Model;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Valerio Farriciello
 * <p>This class is responsible to communicate directcly with the database</p>
 *
 */
public class Database {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction tx;
	
	/**
	 * <p>Creates a connection between the class and the persistence.xml file by creating an entityManagerFactory<br></p>
	 */
	public Database() {
		this.emf = Persistence.createEntityManagerFactory("LeagueDatabase");
		this.em = this.emf.createEntityManager();
		this.tx = em.getTransaction();
	}
	
	
	/**
	 * <p>Retrieves all the instances of Player inside the database<br></p>
	 * @return ArrayList of Player
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Player> getAllThePlayers(){return (ArrayList<Player>) this.em.createQuery("from Player").getResultList();}
	
	/**
	 * <p>Retrieves all the instances of Manager inside the database<br></p>
	 * @return ArrayList of Manager
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Manager> getAllTheManagers(){return (ArrayList<Manager>) this.em.createQuery("from Manager").getResultList();}
	
	/**
	 * <p>Retrieves all the instances of Team inside the database<br></p>
	 * @return ArrayList of Team
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Team> getAllTheTeams(){return (ArrayList<Team>) this.em.createQuery("from Team").getResultList();}
	
	/**
	 * <p>Retrieves all the instances of PlayersAssignment inside the database<br></p>
	 * @return ArrayList of PlayersAssignment
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<PlayersAssignment> getPlayersAssignment(){return (ArrayList<PlayersAssignment>) this.em.createQuery("from PlayersAssignment").getResultList();}
	
	/**
	 * <p>Adds a new instance of Player inside the database<br></p>
	 * @param newPlayer (Player)
	 */
	public void createAPlayer(Player newPlayer) {
		this.tx.begin();
		this.em.createNativeQuery("INSERT INTO Player (playerID, firstName, middleName, lastName, phone, email, goals, isGoalie) VALUES (?,?,?,?,?,?,?,?)")
	      .setParameter(1, newPlayer.getPlayerID())
	      .setParameter(2, newPlayer.getName().getFirstName())
	      .setParameter(3, newPlayer.getName().getMiddleName())
	      .setParameter(4, newPlayer.getName().getLastName())
	      .setParameter(5, newPlayer.getPhone())
	      .setParameter(6, newPlayer.getEmail())
	      .setParameter(7, newPlayer.getGoals())
	      .setParameter(8, newPlayer.isGoalie())
	      .executeUpdate();
		this.tx.commit();
	}
	
	
	/**
	 * <p>Adds a new instance of manager inside the database<br></p>
	 * @param newManager (Manager)
	 */
	public void createAManager(Manager newManager) {
		this.tx.begin();
		this.em.createNativeQuery("INSERT INTO Manager (managerID, firstName, middleName, lastName, phone, email, dateOfBirth, starRating) VALUES (?,?,?,?,?,?,?,?)")
	      .setParameter(1, newManager.getManagerID())
	      .setParameter(2, newManager.getName().getFirstName())
	      .setParameter(3, newManager.getName().getMiddleName())
	      .setParameter(4, newManager.getName().getLastName())
	      .setParameter(5, newManager.getPhone())
	      .setParameter(6, newManager.getEmail())
	      .setParameter(7, newManager.getDateOfBirth())
	      .setParameter(8, newManager.getStarRating())
	      .executeUpdate();
		this.tx.commit();
	}
	
	
	/**
	 * <p>Adds a new instance of Team inside the database <br></p>
	 * @param newTeam (Team)
	 */
	public void createATeam(Team newTeam) {
		this.tx.begin();
		this.em.createNativeQuery("INSERT INTO Team (teamID, name, jerseyColour) VALUES (?,?,?)")
	      .setParameter(1, newTeam.getTeamID())
	      .setParameter(2, newTeam.getName())
	      .setParameter(3, newTeam.getJerseyColour())
	      .executeUpdate();
		this.tx.commit();
	}
	
	
	/**
	 * <p>Adds a new instance of PlayerAssignment inside the database<br></p>
	 * @param playerID (String)
	 * @param teamID (String)
	 */
	public void addPlayerToATeam(String playerID, String teamID) {
		this.tx.begin();
		this.em.createNativeQuery("INSERT INTO PlayersAssignment (teamID, playerID) VALUES (?,?)")
	      .setParameter(1, teamID)
	      .setParameter(2, playerID)
	      .executeUpdate();
		this.tx.commit();
	}
	
	
	/**
	 * <p>Assigns a manager to a team in the database<br></p>
	 * @param managerID (String)
	 * @param teamID (String)
	 */
	public void addManagerToATeam(String managerID, String teamID) {
		Team team = this.em.find(Team.class, teamID);
		this.tx.begin();
		this.em.persist(team);
		team.setManagerID(managerID);
		this.tx.commit();
	}
	
	
	
	/**
	 * <p>Removes an instance of PlayersAssignment from the database<br></p>
	 * @param playerAssignmentID (String)
	 */
	public void removePlayerFromTheTeam(int playerAssignmentID) {
		 PlayersAssignment playerAssignment = em.find(PlayersAssignment.class, playerAssignmentID);
		 this.tx.begin();
		 this.em.remove(playerAssignment);
		 this.tx.commit();
	}
	
	/**
	 * Sets the value managerID in the Team table to null.
	 * @param teamID (String)
	 */
	public void removeManagerFromTheTeam(String teamID) {
		 Team team = em.find(Team.class, teamID);
		 this.tx.begin();
		 this.em.persist(team);
		 team.setManagerID(null);
		 this.tx.commit();
	}
	
	
	
	/**
	 * <p>It queries all the players that have the parameter that has been passed in their firstName, middleName or lastName<br></p>
	 * @param name (String)
	 * @return ArrayList of Player
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Player> queryPlayersByName(String name){
		return (ArrayList<Player>) this.em.createQuery("FROM Player WHERE firstName LIKE '%"+name+
				"%' OR middleName LIKE '%"+name+"%' OR lastName LIKE '%"+name+"%'")
	      .getResultList();
	}	

	/**
	 * <p>It queries all the managers that have the parameter that has been passed in their firstName, middleName or lastName<br></p>
	 * @param name (String)
	 * @return ArrayList of Manager
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Manager> queryManagersByName(String name){
		return (ArrayList<Manager>) this.em.createQuery("FROM Manager WHERE firstName LIKE '%"+name+
				"%' OR middleName LIKE '%"+name+"%' OR lastName LIKE '%"+name+"%'")
	      .getResultList();
	}	

	
	/**
	 * <p>Delete an instance of Player and the corresponding (if present) instance of playersAssignment<br></p>
	 * @param playerID (String)
	 * @param assignmentID (int)
	 */
	public void deletePlayer(String playerID, int assignmentID) {
		Player player = this.em.find(Player.class, playerID);
		tx.begin();
		if(assignmentID != -1) {
			PlayersAssignment assignment = this.em.find(PlayersAssignment.class, assignmentID);
			this.em.persist(assignment);
			this.em.remove(assignment);
		}

		this.em.persist(player);
		this.em.remove(player);
		tx.commit();

	}
	
	/**
	 * <p>Delete an instance of Manager and sets the attribute managerID in his/her team (if present) to null<br></p>
	 * @param managerID (String)
	 * @param teamID (String)
	 */
	public void deleteManager(String managerID, String teamID) {
		Manager manager = this.em.find(Manager.class, managerID);
		tx.begin();
		if(teamID != null) {
			Team team = this.em.find(Team.class, teamID);
			this.em.persist(team);
			team.setManagerID(null);
		}
		
		this.em.persist(manager);
		this.em.remove(manager);
		tx.commit();
	}
	
	/**
	 * <p>Deletes an instance of team and its corresponding players assignment from the database<br></p>
	 * @param teamID (String)
	 */
	@SuppressWarnings("unchecked")
	public void deleteTeam(String teamID) {
		
		ArrayList<PlayersAssignment> assignmentsToDelete = (ArrayList<PlayersAssignment>) this.em.createQuery("from PlayersAssignment WHERE teamID = '"+teamID+"'").getResultList();
		Team team = this.em.find(Team.class, teamID);
		
		tx.begin();
		for(int i = 0; i < assignmentsToDelete.size(); i++) {
			this.em.persist(assignmentsToDelete.get(i));
			this.em.remove(assignmentsToDelete.get(i));
		}
		
		this.em.persist(team);
		this.em.remove(team);
		tx.commit();
	}
	
	
	/**
	 * <p>Updates the attributes of an instance of Player with the passed values.<br></p>
	 * @param playerID (String)
	 * @param firstName (String)
	 * @param middleName (String)
	 * @param lastName (String)
	 * @param phone (String)
	 * @param email (String) 
	 * @param goals (int)
	 * @param isGoalie (boolean)
	 */
	public void modifyAPlayer(String playerID, String firstName, String middleName, String lastName, String phone, String email, int goals, boolean isGoalie) {
		Player playerToModify = this.em.find(Player.class, playerID);
		tx.begin();
		this.em.persist(playerToModify);
		playerToModify.setName(new Name(firstName, middleName, lastName));
		playerToModify.setPhone(phone);
		playerToModify.setEmail(email);
		playerToModify.setGoals(goals);
		playerToModify.setGoalie(isGoalie);
		tx.commit();		
	}
	
	/**
	 * <p>Updates the attributes of an instance of Manager with the passed values<br></p>
	 * @param managerID (String)
	 * @param firstName (String)
	 * @param middleName (String)
	 * @param lastName (String)
	 * @param phone (String)
	 * @param email (String)
	 * @param dateOfBirth (String)
	 * @param starRating (int)
	 */
	public void modifyAManager(String managerID, String firstName, String middleName, String lastName, String phone, String email, String dateOfBirth, int starRating ) {
		Manager managerToModify = this.em.find(Manager.class, managerID);
		tx.begin();
		this.em.persist(managerToModify);
		managerToModify.setName(new Name(firstName, middleName, lastName));
		managerToModify.setPhone(phone);
		managerToModify.setEmail(email);
		managerToModify.setDateOfBirth(dateOfBirth);
		managerToModify.setStarRating(starRating);
		tx.commit();
	}
	
	
	public void closeConnection() {
		this.em.close();
		this.emf.close();
	}
	
}
