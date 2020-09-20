package valerioFarriciello.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import valerioFarriciello.Controller.League;
import valerioFarriciello.Model.*;


/**
 * <p>Class responsible to display the values received from the controller<br></p>
 * @author Valerio Farriciello
 *
 */
public class DisplayTab extends Tab{

	private League league;
	private GridPane displayGridPane;
	private Button searchByNameBtn;
	private Button displayAllThePlayersBtn;
	private Button displayAllTheManagersBtn;
	private Button displayAllTheManagersInOrderBtn;
	private Button displayPlayersInATeamBtn;
	private Button displayAllTheTeamsBtn;
	private VBox buttonsBox;
	
	
	/**
	 * <p>Generates a gridpane that contains 6 buttons that will display players, managers and teams in different ways.</p>
	 * @param league(League)
	 */
	public DisplayTab(League league) {
		this.league = league;
		this.displayGridPane = new GridPane();
		this.setClosable(false);
		this.setContent(this.displayGridPane);
		this.setText("DISPLAY & SEARCH");
		
		this.searchByNameBtn = new Button("SEARCH BY NAME");
		this.searchByNameBtn.setMinWidth(420);
		this.searchByNameBtn.setOnAction(e ->{
			searchByNameBox();
		});
		
		this.displayAllThePlayersBtn = new Button("DISPLAY ALL THE PLAYERS");
		this.displayAllThePlayersBtn.setMinWidth(420);
		this.displayAllThePlayersBtn.setOnAction(e ->{
			String result = this.league.getAllThePlayersToDisplay();
			displayBox("Display all the players", result);
		});
		
		
		this.displayAllTheManagersBtn = new Button("DISPLAY ALL THE MANAGERS");
		this.displayAllTheManagersBtn.setMinWidth(420);
		this.displayAllTheManagersBtn.setOnAction(e ->{
			String result = this.league.getAllTheManagersToDisplay();
			displayBox("Display all the managers", result);
		});
		
		
		this.displayAllTheManagersInOrderBtn = new Button("DISPLAY ALL THE MANAGERS (ORDERED BY STAR RATING)");
		this.displayAllTheManagersInOrderBtn.setMinWidth(420);
		this.displayAllTheManagersInOrderBtn.setOnAction(e ->{
			String result = this.league.getAllTheManagersToDisplayOrderedByStars();
			displayBox("Display all the managers ordered by star rating", result);
		});
		
		
		this.displayPlayersInATeamBtn = new Button("DISPLAY ALL THE PLAYERS IN A TEAM (ORDERED BY NAME)");
		this.displayPlayersInATeamBtn.setMinWidth(420);
		this.displayPlayersInATeamBtn.setOnAction(e ->{
			displayPlayersInATeam();
		});
		
		this.displayAllTheTeamsBtn = new Button("DISPLAY ALL THE TEAMS");
		this.displayAllTheTeamsBtn.setMinWidth(420);
		this.displayAllTheTeamsBtn.setOnAction(e ->{
			String result = this.league.getTeamsToDisplay();
			displayBox("Display all the teams", result);
		});
		
		
		
		this.buttonsBox = new VBox();
		this.buttonsBox.setSpacing(30);
		this.buttonsBox.setPadding(new Insets(10, 0, 10, 0));
		
		this.buttonsBox.getChildren().addAll(this.searchByNameBtn, this.displayAllThePlayersBtn, this.displayAllTheManagersBtn, this.displayAllTheManagersInOrderBtn,
				this.displayPlayersInATeamBtn, this.displayAllTheTeamsBtn);
		
		
		this.displayGridPane.add(this.buttonsBox, 0, 0);
		this.displayGridPane.setAlignment(Pos.CENTER);
		
	}
	
	
	
	
	
	/**
	 * <p>It gets a text value from the user and returns the result from the controller object.<br></p>
	 */
	private void searchByNameBox() {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Search by name");
		window.setMinWidth(450);
		window.setResizable(false);
		
		
		Label nameLabel = new Label();
		nameLabel.setText("Name: ");
		TextField nameTextField = new TextField();
		nameTextField.setMaxWidth(200);
		
		TextArea textArea = new TextArea();
		Button searchButton = new Button("SEARCH");
		
		searchButton.setOnAction(e -> {
			textArea.setText("Searching for players and managers with the first name, middle name or last name\n that contains: '" + nameTextField.getText() + "'\n\n"+
					this.league.getPlayersAndManagersByName(nameTextField.getText()));
			
		});
		Button closeButton = new Button("GO BACK");
		closeButton.setOnAction(e -> {window.close();
		});
		
		HBox buttonsBox = new HBox(10);
		buttonsBox.getChildren().addAll(searchButton, closeButton);
		buttonsBox.setAlignment(Pos.CENTER);
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(nameLabel, nameTextField, textArea, buttonsBox);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	
	
	/**
	 * <p>This method is used internally multiple times in order to display the different values from the controller in different circumstances<br></p>
	 * @param title (String)
	 * @param resultToDisplay (String)
	 */
	private void displayBox(String title, String resultToDisplay) {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(880);
		window.setResizable(false);
		
		TextArea textArea = new TextArea();
		textArea.setText(resultToDisplay);
		
		Button closeButton = new Button("GO BACK");
		closeButton.setOnAction(e -> {
			window.close();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(textArea, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	}

	
	/**
	 * <p>Prompts the user a small window where he/she needs to select a team, then all the players present in that team will be<br>
	 * displayed alphabetically</p>
	 * 
	 */
	private void displayPlayersInATeam() {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Display players in a team");
		window.setMinWidth(450);
		window.setResizable(false);
		
		Label selectTeamLabel = new Label();
		selectTeamLabel.setText("Select a team");
		ComboBox<Team> teamsBox = new ComboBox<Team>();
		teamsBox.setMinWidth(200);
		teamsBox.getItems().addAll(this.league.getAllTheTeams());
		
		TextArea textArea = new TextArea();
		
		Button displayButton = new Button("DISPLAY");
		displayButton.setOnAction(e -> {
			textArea.setText(this.league.getAllThePlayersToDisplayInATeam((Team)teamsBox.getValue()));
		});
		Button closeButton = new Button("GO BACK");
		closeButton.setOnAction(e -> {
			window.close();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(selectTeamLabel, teamsBox, textArea, displayButton, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	
	
	
	
}
