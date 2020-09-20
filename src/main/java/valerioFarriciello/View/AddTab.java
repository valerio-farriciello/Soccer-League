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
 * <p>Class responsible to get the values for players and managers assignments<br></p>
 * @author Valerio Farriciello
 *
 */
public class AddTab extends Tab{

	private League league;
	private GridPane addGridPane;
	private Button addPlayerToaATeamBtn;
	private Button addManagerToATeamBtn;
	private VBox buttonsBox;
	
	
	/**
	 * <p>Generates a gridpane that contains 2 buttons, one for add a manger to a team and<br>
	 * the other one to add a manager to a team.</p>
	 * @param league(League)
	 */
	public AddTab(League league) {
		this.league = league;
		this.addGridPane = new GridPane();
		this.setClosable(false);
		this.setContent(this.addGridPane);
		this.setText("ADD TO A TEAM");
		
		
		this.addPlayerToaATeamBtn = new Button("ADD A PLAYER TO A TEAM");
		this.addPlayerToaATeamBtn.setMinWidth(250);
		this.addPlayerToaATeamBtn.setOnAction(e -> {
			addAPlayerToATeamBox();
	    });
		
		this.addManagerToATeamBtn = new Button("ADD A MANAGER TO A TEAM");
		this.addManagerToATeamBtn.setMinWidth(250);
		this.addManagerToATeamBtn.setOnAction(e -> {
			addAManagerToATeamBox();
	    });
		
		this.buttonsBox = new VBox();
		this.buttonsBox.setSpacing(50);
		
		this.buttonsBox.getChildren().addAll(this.addPlayerToaATeamBtn, this.addManagerToATeamBtn);
		
		this.addGridPane.add(this.buttonsBox, 0, 0);
		this.addGridPane.setAlignment(Pos.CENTER);
		
	}
	
	
	
	/**
	 * <p>A small window that will allow the user to pick a team and a player.<br>
	 * Then these 2 will be passed to the controller object</p>
	 */
	private void addAPlayerToATeamBox() {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Add a player to a team");
		window.setMinWidth(450);
		window.setResizable(false);
		
		Label selectPlayerLabel = new Label();
		selectPlayerLabel.setText("Select a player");
		ComboBox<Player> playersBox = new ComboBox<Player>();
		playersBox.setMinWidth(200);
		playersBox.getItems().addAll(league.getAllThePlayers());
		
		
		Label selectTeamLabel = new Label();
		selectTeamLabel.setText("Select a team");
		ComboBox<Team> teamsBox = new ComboBox<Team>();
		teamsBox.setMinWidth(200);
		teamsBox.getItems().addAll(league.getAllTheTeams());
		
		
		Button closeButton = new Button("ADD");
		
		closeButton.setOnAction(e ->{
			
			if((teamsBox.getValue() != null) && (playersBox.getValue() != null)) {
				if(this.league.assignPlayerToATeam((Player)playersBox.getValue(), (Team)teamsBox.getValue())) {
					Main.alertBox("Assigning a player", "Congratulations! The player has been successfully added to the selected team.", "CONTINUE");
				}
				else {
					Main.alertBox("Assigning a player", "Oops! Something went wrong...maybe the team is full or"
							+ " the player that you have selected is already in the team.", "CONTINUE");
				}
				window.close();
			}
			
			
			
			
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(selectPlayerLabel, playersBox, selectTeamLabel, teamsBox, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	/**
	 * <p>A small window that will allow the user to pick a team and a manager.<br>
	 * Then these 2 will be passed to the controller object</p>
	 */
	private void addAManagerToATeamBox() {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Add a manager to a team");
		window.setMinWidth(450);
		window.setResizable(false);
		
		Label selectManagerLabel = new Label();
		selectManagerLabel.setText("Select a manager");
		ComboBox<Manager> managersBox = new ComboBox<Manager>();
		managersBox.setMinWidth(200);
		managersBox.getItems().addAll(league.getAllTheManagers());
		
		Label selectTeamLabel = new Label();
		selectTeamLabel.setText("Select a team");
		ComboBox<Team> teamsBox = new ComboBox<Team>();
		teamsBox.setMinWidth(200);
		teamsBox.getItems().addAll(league.getAllTheTeams());
		
		Button closeButton = new Button("ADD");
		
		closeButton.setOnAction(e ->{
			if((teamsBox.getValue() != null) && (managersBox.getValue() != null)) {
				if(this.league.assignManagerToATeam((Manager)managersBox.getValue(), (Team)teamsBox.getValue())) {
					Main.alertBox("Assigning a manager", "Congratulations! The manager has been successfully added to the selected team.", "CONTINUE");
				}
				else {
					Main.alertBox("Assigning a manager", "Oops! Something went wrong...maybe the manager is already in the team.", "CONTINUE");
				}
				window.close();
			}
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(selectManagerLabel, managersBox, selectTeamLabel, teamsBox, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
}
