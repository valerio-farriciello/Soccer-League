package valerioFarriciello.View;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import valerioFarriciello.Controller.League;
import valerioFarriciello.Model.Manager;
import valerioFarriciello.Model.Player;



/**
 * <p>Class responsible to get the values for the modification of players and managers attributes<br></p>
 * @author Valerio Farriciello
 *
 */
public class ModifyTab extends Tab {
	private League league;
	private GridPane modifyGridPane;
	private Button modifyPlayerBtn;
	private Button modifyManagerBtn;
	private VBox buttonsBox;
	
	/**
	 * <p>Generates a gridpane that contains 2 buttons, one to modify a manager and one to modify a player<br></p>
	 * @param league(League)
	 */
	public ModifyTab(League league) {
		this.league = league;
		this.modifyGridPane = new GridPane();
		this.setClosable(false);
		this.setContent(this.modifyGridPane);
		this.setText("MODIFY");
		
		this.modifyPlayerBtn = new Button("MODIFY PLAYER");
		this.modifyPlayerBtn.setMinWidth(200);
		this.modifyPlayerBtn.setOnAction(e ->{
			
			modifyAPlayerBox(selectAPlayerBox());
		});
		
		
		this.modifyManagerBtn = new Button("MODIFY MANAGER");
		this.modifyManagerBtn.setMinWidth(200);
		this.modifyManagerBtn.setOnAction(e ->{
			
			modifyAManagerBox(selectAManagerBox());
		});
		
		this.buttonsBox = new VBox();
		this.buttonsBox.setSpacing(50);
		
		this.buttonsBox.getChildren().addAll(this.modifyPlayerBtn, this.modifyManagerBtn);
		
		this.modifyGridPane.add(this.buttonsBox, 0, 0);
		this.modifyGridPane.setAlignment(Pos.CENTER);
		
		
	}
	
	
	
	/**
	 * <p>Generates a small window that will prompt the user to select a player, then it will return the selected value<br></p>
	 * @return Player
	 */
	private Player selectAPlayerBox() {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Select a player");
		window.setMinWidth(450);
		window.setResizable(false);
		
		Label label = new Label("Select a player");
		
		ComboBox<Player> players = new ComboBox<Player>();
		players.setMinWidth(200);
		players.getItems().addAll(this.league.getAllThePlayers());

		Button closeButton = new Button("SELECT");
		closeButton.setOnAction(e -> {
			if(players.getValue() != null) {
				window.close();
			}
			
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, players, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return (Player)players.getValue();
		
	}
	
	/**
	 * <p>Takes a player object as parameter in order to display the current attributes and allow the user to modify them.<br></p>
	 * @param playerToModify (Player)
	 */
	private void modifyAPlayerBox(Player playerToModify) {
		if(playerToModify == null) return;
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Modify a player");
		window.setMinWidth(450);
		window.setResizable(false);
		
		Object[] playerDetails = new Object[7];
		
		Label firstNameLabel = new Label();
		firstNameLabel.setText("First name (max 40 characters long): ");
		TextField firstNameTextField = new TextField(playerToModify.getName().getFirstName());
		firstNameTextField.setMaxWidth(200);
		
		Label middleNameLabel = new Label();
		middleNameLabel.setText("Middle name (OPTIONAL): ");
		TextField middleNameTextField = new TextField(playerToModify.getName().getMiddleName());
		if(playerToModify.getName().getMiddleName() == null) {
			middleNameTextField.setText("");
		}
		middleNameTextField.setMaxWidth(200);
		
		Label lastNameLabel = new Label();
		lastNameLabel.setText("Last name (max 40 characters long): ");
		TextField lastNameTextField = new TextField(playerToModify.getName().getLastName());
		lastNameTextField.setMaxWidth(200);
		
		Label phoneLabel = new Label();
		phoneLabel.setText("Phone number (between 4 and 25 characters long): ");
		TextField phoneTextField = new TextField(playerToModify.getPhone());
		phoneTextField.setMaxWidth(200);
		
		Label emailLabel = new Label();
		emailLabel.setText("E-mail (between 10 and 25 characters long): ");
		TextField emailTextField = new TextField(playerToModify.getEmail());
		emailTextField.setMaxWidth(200);

		Label goalsLabel = new Label();
		goalsLabel.setText("Number of goals (100 goals max): ");
		TextField goalsTextField = new TextField("" + playerToModify.getGoals());
		goalsTextField.setMaxWidth(200);

		Label isAGoalieLabel = new Label();
		isAGoalieLabel.setText("Is she/he a goalie? ");
		ComboBox<String> isAGoalieBox = new ComboBox<String>();
		isAGoalieBox.setMinWidth(200);
		isAGoalieBox.getItems().addAll("YES", "NO");

		Button closeButton = new Button("MODIFY");
		closeButton.setOnAction(e -> {
			playerDetails[0] = firstNameTextField.getText();
			playerDetails[1] = middleNameTextField.getText();
			playerDetails[2] = lastNameTextField.getText();
			playerDetails[3] = phoneTextField.getText();
			playerDetails[4] = emailTextField.getText();
			playerDetails[5] = goalsTextField.getText();
			playerDetails[6] = isAGoalieBox.getValue();
			
			
			if(this.league.createANewPlayer(playerDetails, playerToModify.getPlayerID())) {
				Main.alertBox("Player Modification", "Congratulations! The player has been successfully MODIFIED!", "CONTINUE");
				window.close();
			}
			else {
				Main.alertBox("Player Modification", "Oops! Something went wrong during the modification of the player...please make sure the "
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
	 * <p>Generates a small window that will prompt the user to select a Manager, then it will return the selected value<br></p>
	 * @return Manager
	 */
	private Manager selectAManagerBox() {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Select a manager");
		window.setMinWidth(450);
		window.setResizable(false);
		
		Label label = new Label("Select a manager");
		
		ComboBox<Manager> managers = new ComboBox<Manager>();
		managers.setMinWidth(200);
		managers.getItems().addAll(this.league.getAllTheManagers());

		Button closeButton = new Button("SELECT");
		closeButton.setOnAction(e -> {
			if(managers.getValue() != null) {
				window.close();
			}
			
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, managers, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return (Manager)managers.getValue();
		
	}
	
	/**
	 * <p>Takes a manager object as parameter in order to display the current attributes and allow the user to modify them.<br></p>
	 * @param managerToModify (Manager)
	 */
	private void modifyAManagerBox(Manager managerToModify) {
		if(managerToModify == null) return;
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("New Manager");
		window.setMinWidth(450);
		window.setResizable(false);
		
		Object[] managerDetails = new Object[7];
		
		Label firstNameLabel = new Label();
		firstNameLabel.setText("First name (max 40 characters long): ");
		TextField firstNameTextField = new TextField(managerToModify.getName().getFirstName());
		firstNameTextField.setMaxWidth(200);
		
		Label middleNameLabel = new Label();
		middleNameLabel.setText("Middle name (OPTIONAL): ");
		TextField middleNameTextField = new TextField(managerToModify.getName().getMiddleName());
		if(managerToModify.getName().getMiddleName() == null) {
			middleNameTextField.setText("");
		}
		
		middleNameTextField.setMaxWidth(200);
		
		Label lastNameLabel = new Label();
		lastNameLabel.setText("Last name (max 40 characters long): ");
		TextField lastNameTextField = new TextField(managerToModify.getName().getLastName());
		lastNameTextField.setMaxWidth(200);
		
		Label phoneLabel = new Label();
		phoneLabel.setText("Phone numbeR (between 4 and 25 characters long): ");
		TextField phoneTextField = new TextField(managerToModify.getPhone());
		phoneTextField.setMaxWidth(200);
		
		Label emailLabel = new Label();
		emailLabel.setText("E-mail (between 10 and 25 characters long): ");
		TextField emailTextField = new TextField(managerToModify.getEmail());
		emailTextField.setMaxWidth(200);

		Label starRatingLabel = new Label();
		starRatingLabel.setText("Rating 1 to 10: ");
		TextField ratingTextField = new TextField();
		ratingTextField.setMaxWidth(200);

		Button closeButton = new Button("MODIFY");
		closeButton.setOnAction(e -> {
			managerDetails[0] = firstNameTextField.getText();
			managerDetails[1] = middleNameTextField.getText();
			managerDetails[2] = lastNameTextField.getText();
			managerDetails[3] = phoneTextField.getText();
			managerDetails[4] = emailTextField.getText();
			managerDetails[5] = managerToModify.getDateOfBirth();
			managerDetails[6] = ratingTextField.getText();
			
			
			if(this.league.createANewManager(managerDetails, managerToModify.getManagerID())) {
				Main.alertBox("Manager Modification", "Congratulations! The manager has been successfully modified!", "CONTINUE");
				window.close();
			}
			else {
				Main.alertBox("Manager Modification", "Oops! Something went wrong during the modification of the manager...please make sure the "
						+ "details are in the correct format!", "TRY AGAIN");
			}
			
			
		});
		VBox layout = new VBox(10);
		layout.getChildren().addAll(firstNameLabel, firstNameTextField, middleNameLabel, middleNameTextField, lastNameLabel, lastNameTextField,
				phoneLabel, phoneTextField, emailLabel, emailTextField, starRatingLabel, ratingTextField, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	
}
