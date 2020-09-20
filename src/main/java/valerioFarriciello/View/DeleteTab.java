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
 * <p>Class responsible to get the values for the deletion of players, managers and teams<br></p>
 * @author Valerio Farriciello
 *
 */
public class DeleteTab extends Tab{

	private League league;
	private GridPane deleteGridPane;
	private Button deletePlayerBtn;
	private Button deleteManagerBtn;
	private Button deleteTeamBtn;
	private VBox buttonsBox;
	
	/**
	 * <p>Generates a gridpane that contains 3 buttons, one to delete a manager, one to delete a team and<br>
	 * another one to delete a player.</p>
	 * @param league(League)
	 */
	public DeleteTab(League league) {
		this.league = league;
		this.deleteGridPane = new GridPane();
		this.setClosable(false);
		this.setContent(this.deleteGridPane);
		this.setText("DELETE");
		
		
		this.deletePlayerBtn = new Button("DELETE PLAYER");
		this.deletePlayerBtn.setMinWidth(250);
		this.deletePlayerBtn.setOnAction(e -> {
			ComboBox<Object> playersBox = new ComboBox<Object>();
			playersBox.setMinWidth(200);
			playersBox.getItems().addAll(this.league.getAllThePlayers());
			Player playerToDelete = (Player)deleteBox("Delete a player", "Select a player", playersBox);
			if(playerToDelete != null) {
				this.league.deletePlayer(playerToDelete);
				Main.alertBox("Deleting a player", "The player has successfully been deleted.", "CONTINUE");
			}
		});
		
		this.deleteManagerBtn = new Button("DELETE MANAGER");
		this.deleteManagerBtn.setMinWidth(250);
		this.deleteManagerBtn.setOnAction(e -> {
			ComboBox<Object> managersBox = new ComboBox<Object>();
			managersBox.setMinWidth(200);
			managersBox.getItems().addAll(this.league.getAllTheManagers());
			Manager managerToDelete = (Manager)deleteBox("Delete a manager", "Select a manager", managersBox);
			if(managerToDelete != null) {
				this.league.deleteManager(managerToDelete);
				Main.alertBox("Deleting a manager", "The manager has successfully been deleted.", "CONTINUE");
			}
			
	    });
		
		this.deleteTeamBtn = new Button("DELETE TEAM");
		this.deleteTeamBtn.setMinWidth(250);
		this.deleteTeamBtn.setOnAction(e -> {
			ComboBox<Object> teamsBox = new ComboBox<Object>();
			teamsBox.setMinWidth(200);
			teamsBox.getItems().addAll(this.league.getAllTheTeams());
			Team teamToDelete = (Team)deleteBox("Delete a team", "Select a team", teamsBox);
			if(teamToDelete != null) {
				this.league.deleteTeam(teamToDelete);
				Main.alertBox("Deleting a team", "The team has successfully been deleted.", "CONTINUE");
			}
			
	    });
		
		this.buttonsBox = new VBox();
		this.buttonsBox.setSpacing(50);
		
		this.buttonsBox.getChildren().addAll(this.deletePlayerBtn, this.deleteManagerBtn, this.deleteTeamBtn);
		
		this.deleteGridPane.add(this.buttonsBox, 0, 0);
		this.deleteGridPane.setAlignment(Pos.CENTER);
	}
	
	
	
	/**
	 * <p>It takes a predefined comboBox of players, managers or teams, then it returns the selected value in order to be passed to the controller.<br></p>
	 * @param title (String)
	 * @param labelText (String)
	 * @param box (ComboBox of type Object)
	 * @return Object
	 */
	private Object deleteBox(String title, String labelText, ComboBox<Object> box) {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(450);
		window.setResizable(false);
		
		Label label = new Label();
		label.setText(labelText);
	
		Button closeButton = new Button("DELETE");
		
		closeButton.setOnAction(e ->{
				window.close();			
	
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, box, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		return box.getValue();
	}
	
	
}
