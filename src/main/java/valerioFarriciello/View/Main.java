package valerioFarriciello.View;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import valerioFarriciello.Controller.League;
import valerioFarriciello.Model.Player;

/**
 *<p>The Main class is responsible to group all the GUI components and getting input from the user.<br>
 *This class is not responsible for data manipulation or storage. In a MVC architecture this class would<br>
 *be part of the VIEW.</p>
 *
 *@author Valerio Farriciello
 * 
 */
public class Main extends Application{
	private League league;
	@Override
	public void start(Stage stage) {
		try {
			TabPane tabPane = new TabPane();
			tabPane.setTabMinWidth(130);
			Scene scene = new Scene(tabPane, 1080, 400);
			stage.setResizable(false);
			stage.setTitle("My League");
			stage.setScene(scene);
			
			this.league = new League();
			
			tabPane.getTabs().addAll(new CreateTab(this.league), new AddTab(this.league), 
					new ModifyTab(this.league), new RemoveTab(this.league), 
					new DisplayTab(this.league), new DeleteTab(this.league), new OtherTab(this.league));
	        stage.show();     
	        
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	/**
	 * <p>A simple alert box that is used multiple times to communicate with the user.</p>
	 * @param title (String)
	 * @param message (String)
	 * @param closeButtonMessage (String)
	 */
	public static void alertBox(String title, String message, String closeButtonMessage) {
		Stage window = new Stage();
		//Block events to other windows
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(450);
		window.setResizable(false);
		Label label = new Label();
		label.setText(message);
		Button closeButton = new Button(closeButtonMessage);
		closeButton.setOnAction(e -> window.close());
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		//Display window and wait for it to be closed before returning
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	/**
	 * <p>Creates an array list ad adds in it Player objects. It's<br>
	 * used to show memory usage</p>
	 */
	public static void showMemoryUsage() {
		ArrayList<Player> listOfPlayers = new ArrayList<>();
		for(int i = 0; i < 1000000; i++) {
			listOfPlayers.add(new Player());
		}
	}
}