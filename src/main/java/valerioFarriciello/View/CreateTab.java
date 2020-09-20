package valerioFarriciello.View;


import java.time.format.DateTimeFormatter;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import valerioFarriciello.Controller.League;

/**
 * <p>Class responsible to get the values for the creation of players, managers and teams<br></p>
 * @author Valerio Farriciello
 *
 */
public class CreateTab extends Tab{

	private League league;
	private GridPane createGridPane;
	private Button createPlayerBtn;
	private Button createManagerBtn;
	private Button createTeamBtn;
	private VBox buttonsBox;
	
	/**
	 * <p>Generates a gridpane that contains 3 buttons, one to create a manager, one to create a team and<br>
	 * another one to create a player.</p>
	 * @param league(League)
	 */
	public CreateTab(League league) {
		this.league = league;
		this.createGridPane = new GridPane();
		this.setClosable(false);
		this.setContent(this.createGridPane);
		this.setText("CREATE");
		
		
		this.createPlayerBtn = new Button("CREATE PLAYER");
		this.createPlayerBtn.setMinWidth(250);
		this.createPlayerBtn.setOnAction(e -> {
				createAPlayerBox();
		    });
		
		this.createManagerBtn = new Button("CREATE MANAGER");
		this.createManagerBtn.setMinWidth(250);
		this.createManagerBtn.setOnAction(e -> {
			createAManagerBox();
	    });
		
		this.createTeamBtn = new Button("CREATE TEAM");
		this.createTeamBtn.setMinWidth(250);
		this.createTeamBtn.setOnAction(e -> {
			createATeamBox();
	    });
		
		this.buttonsBox = new VBox();
		this.buttonsBox.setSpacing(50);
		
		this.buttonsBox.getChildren().addAll(this.createPlayerBtn, this.createManagerBtn, this.createTeamBtn);
		
		this.createGridPane.add(this.buttonsBox, 0, 0);
		this.createGridPane.setAlignment(Pos.CENTER);
	}
	
	
	
	/**
	 * <p>Small window that will be taking all the parameters for the creation of a player and<br>
	 * pass them to the controller object.</p>
	 */
	private void createAPlayerBox() {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("New Player");
		window.setMinWidth(450);
		window.setResizable(false);
		
		Object[] playerDetails = new Object[7];
		
		Label firstNameLabel = new Label();
		firstNameLabel.setText("First name (max 40 characters long): ");
		TextField firstNameTextField = new TextField();
		firstNameTextField.setMaxWidth(200);
		
		Label middleNameLabel = new Label();
		middleNameLabel.setText("Middle name (OPTIONAL): ");
		TextField middleNameTextField = new TextField();
		middleNameTextField.setMaxWidth(200);
		
		Label lastNameLabel = new Label();
		lastNameLabel.setText("Last name (max 40 characters long): ");
		TextField lastNameTextField = new TextField();
		lastNameTextField.setMaxWidth(200);
		
		Label phoneLabel = new Label();
		phoneLabel.setText("Phone number (between 4 and 25 characters long): ");
		TextField phoneTextField = new TextField();
		phoneTextField.setMaxWidth(200);
		
		Label emailLabel = new Label();
		emailLabel.setText("E-mail (between 10 and 25 characters long): ");
		TextField emailTextField = new TextField();
		emailTextField.setMaxWidth(200);

		Label goalsLabel = new Label();
		goalsLabel.setText("Number of goals (100 goals max): ");
		TextField goalsTextField = new TextField();
		goalsTextField.setMaxWidth(200);

		Label isAGoalieLabel = new Label();
		isAGoalieLabel.setText("Is she/he a goalie? ");
		ComboBox<String> isAGoalieBox = new ComboBox<String>();
		isAGoalieBox.setMinWidth(200);
		isAGoalieBox.getItems().addAll("YES", "NO");

		Button closeButton = new Button("ADD");
		closeButton.setOnAction(e -> {
			playerDetails[0] = firstNameTextField.getText();
			playerDetails[1] = middleNameTextField.getText();
			playerDetails[2] = lastNameTextField.getText();
			playerDetails[3] = phoneTextField.getText();
			playerDetails[4] = emailTextField.getText();
			playerDetails[5] = goalsTextField.getText();
			playerDetails[6] = isAGoalieBox.getValue();
			
			
			if(this.league.createANewPlayer(playerDetails, null)) {
				Main.alertBox("Player Creation", "Congratulations! The player has been successfully created!", "CONTINUE");
				window.close();
			}
			else {
				Main.alertBox("Player Creation", "Oops! Something went wrong during the creation of the player...please make sure the "
						+ "details are in the correct format!", "TRY AGAIN");
			}
			
			
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(firstNameLabel, firstNameTextField, middleNameLabel, middleNameTextField, lastNameLabel, lastNameTextField,
				phoneLabel, phoneTextField, emailLabel, emailTextField, goalsLabel, goalsTextField, isAGoalieLabel, isAGoalieBox, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	/**
	 * <p>Small window that will be taking all the parameters for the creation of a manager and<br>
	 * pass them to the controller object.</p>
	 */
	private void createAManagerBox() {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("New Manager");
		window.setMinWidth(450);
		window.setResizable(false);
		
		Object[] managerDetails = new Object[7];
		
		Label firstNameLabel = new Label();
		firstNameLabel.setText("First name (max 40 characters long): ");
		TextField firstNameTextField = new TextField();
		firstNameTextField.setMaxWidth(200);
		
		Label middleNameLabel = new Label();
		middleNameLabel.setText("Middle name (OPTIONAL): ");
		TextField middleNameTextField = new TextField();
		middleNameTextField.setMaxWidth(200);
		
		Label lastNameLabel = new Label();
		lastNameLabel.setText("Last name (max 40 characters long): ");
		TextField lastNameTextField = new TextField();
		lastNameTextField.setMaxWidth(200);
		
		Label phoneLabel = new Label();
		phoneLabel.setText("Phone number (between 4 and 25 characters long): ");
		TextField phoneTextField = new TextField();
		phoneTextField.setMaxWidth(200);
		
		Label emailLabel = new Label();
		emailLabel.setText("E-mail (between 10 and 25 characters long): ");
		TextField emailTextField = new TextField();
		emailTextField.setMaxWidth(200);

		Label dobLabel = new Label();
		dobLabel.setText("Please select the date of birth: ");
		DatePicker datePicker = new DatePicker();
		datePicker.setMinWidth(200);

		Label starRatingLabel = new Label();
		starRatingLabel.setText("Rating 1 to 10: ");
		TextField ratingTextField = new TextField();
		ratingTextField.setMaxWidth(200);

		Button closeButton = new Button("ADD");
		closeButton.setOnAction(e -> {
			managerDetails[0] = firstNameTextField.getText();
			managerDetails[1] = middleNameTextField.getText();
			managerDetails[2] = lastNameTextField.getText();
			managerDetails[3] = phoneTextField.getText();
			managerDetails[4] = emailTextField.getText();
			String date = null;
			if(datePicker.getValue() != null) {
				date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
			managerDetails[5] = date;
			managerDetails[6] = ratingTextField.getText();
			
			
			if(this.league.createANewManager(managerDetails, null)) {
				Main.alertBox("Manager Creation", "Congratulations! The manager has been successfully created!", "CONTINUE");
				window.close();
			}
			else {
				Main.alertBox("Manager Creation", "Oops! Something went wrong during the creation of the manager...please make sure the "
						+ "details are in the correct format!", "TRY AGAIN");
			}
			
			
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(firstNameLabel, firstNameTextField, middleNameLabel, middleNameTextField, lastNameLabel, lastNameTextField,
				phoneLabel, phoneTextField, emailLabel, emailTextField, dobLabel, datePicker, starRatingLabel, ratingTextField, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	/**
	 * <p>Small window that will be taking all the parameters for the creation of a team and<br>
	 * pass them to the controller object.</p>
	 */
	private void createATeamBox() {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("New Team");
		window.setMinWidth(450);
		window.setResizable(false);
		
		Object[] teamDetails = new Object[2];
		
		Label teamNameLabel = new Label();
		teamNameLabel.setText("Team name (max 40 characters long): ");
		TextField teamNameTextField = new TextField();
		teamNameTextField.setMaxWidth(200);
		
		Label jerseyColourLabel = new Label();
		jerseyColourLabel.setText("Jersey colour (max 40 characters long): ");
		TextField jerseyColourTextField = new TextField();
		jerseyColourTextField.setMaxWidth(200);

		Button closeButton = new Button("ADD");
		closeButton.setOnAction(e -> {
			teamDetails[0] = teamNameTextField.getText();
			teamDetails[1] = jerseyColourTextField.getText();
			
			if(this.league.createANewTeam(teamDetails)) {
				Main.alertBox("Team Creation", "Congratulations! The team has been successfully created!", "CONTINUE");
				window.close();
			}
			else {
				Main.alertBox("Team Creation", "Oops! Something went wrong during the creation of the team...please make sure the "
						+ "details are in the correct format!", "TRY AGAIN");
			}
			
			
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(teamNameLabel, teamNameTextField, jerseyColourLabel, jerseyColourTextField, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	
}