package valerioFarriciello.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import valerioFarriciello.Controller.League;
import valerioFarriciello.Model.*;

/**
 * <p>Class responsible to get the values for removing players and managers from a team<br></p>
 * @author Valerio Farriciello
 *
 */
public class RemoveTab extends Tab{

	private League league;
	private GridPane removeGridPane;
	private Button selectTeamBtn;
	
	/**
	 * <p>Generates a gridpane that contains 1 single button for the user to select a team.<br></p>
	 * @param league(League)
	 */
	public RemoveTab(League league) {
		this.league = league;
		this.removeGridPane = new GridPane();
		this.setClosable(false);
		this.setContent(this.removeGridPane);
		this.setText("REMOVE FROM A TEAM");
		
			
		this.selectTeamBtn = new Button("SELECT TEAM");
		this.selectTeamBtn.setMinWidth(250);
		this.selectTeamBtn.setOnAction(e ->{
			selectTeam();
		});
		
		this.removeGridPane.add(this.selectTeamBtn, 0, 0);
		this.removeGridPane.setAlignment(Pos.CENTER);
	}
	
	
	/**
	 * <p>Once the user has selected a team, it will be passed into this method that will give the user the option<br>
	 * to select a player to be removed.</p>
	 * @param team (Team)
	 */
	private void removePlayerBox(Team team) {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Remove a player from a team");
		window.setMinWidth(450);
		window.setResizable(false);
		
		
		Label selectPlayerLabel = new Label();
		selectPlayerLabel.setText("Select a player");
		ComboBox<Player> playersBox = new ComboBox<Player>();
		playersBox.setMinWidth(200);
		playersBox.getItems().addAll(team.getPlayers());
		
		Button closeButton = new Button("REMOVE PLAYER FROM " + team.getName().toUpperCase());
		
		closeButton.setOnAction(e ->{
			if(playersBox.getValue() != null) {
				this.league.removePlayerFromTheTeam((Player)playersBox.getValue(), true);
				Main.alertBox("Removing a player", "The player has been removed from the team", "Continue");
			}
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(selectPlayerLabel, playersBox, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	
	/**
	 * <p>Small window that prompts the user to the selection of a team and if she/he wants to remove a player or a manager.<br></p>
	 */
	private void selectTeam() {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Select a team");
		window.setMinWidth(450);
		window.setResizable(false);
		
		Label selectTeamLabel = new Label();
		selectTeamLabel.setText("SELECT A TEAM");
		
		
		ComboBox<Team> teamsBox = new ComboBox<Team>();
		teamsBox.setMinWidth(200);
		teamsBox.getItems().addAll(league.getAllTheTeams());
		
		Button removePlayerButton = new Button("REMOVE PLAYER");
		Button removeManagerButton = new Button("REMOVE MANAGER");
		
	
		
		removePlayerButton.setOnAction(e ->{
			if(teamsBox.getValue() != null) {
				removePlayerBox((Team) teamsBox.getValue());
				window.close();
			}	
		});
		
		removeManagerButton.setOnAction(e ->{
			if((teamsBox.getValue() != null) && (((Team)teamsBox.getValue()).getManager() != null )) {
				this.league.removeManagerFromTheTeam(((Team)teamsBox.getValue()).getManager(), true);
				Main.alertBox("Removing a manager", "The manager has been removed from the team", "Continue");
				window.close();
			}
			else {
				Main.alertBox("Removing a manager", "Please select a team that has a manager", "Continue");
				window.close();
			}
			
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(selectTeamLabel, teamsBox, removePlayerButton, removeManagerButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
