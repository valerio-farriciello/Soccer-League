package valerioFarriciello.View;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import valerioFarriciello.Controller.League;

/**
 * <p>Simple class that is responsible to show the memory usage (by temporary overloading the memory) and close the connection<br>
 * with the database</p>
 * @author Valerio Farriciello
 *
 */
public class OtherTab extends Tab {
	private League league;
	private GridPane otherGridPane;
	private Button showMemoryUsageBtn;
	private Button exitBtn;
	private VBox buttonsBox;
	
	/**
	 * <p>Generates a gridpane that contains 2 buttons, one to show memory usage and one to exit<br></p>
	 * @param league(League)
	 */
	public OtherTab(League league) {
		this.league = league;
		this.otherGridPane = new GridPane();
		this.setClosable(false);
		this.setContent(this.otherGridPane);
		this.setText("OTHER OPTIONS");
		
		this.showMemoryUsageBtn = new Button("SHOW MEMORY USAGE");
		this.showMemoryUsageBtn.setMinWidth(250);
		this.showMemoryUsageBtn.setOnAction(e ->{
			Main.showMemoryUsage();
		});
		
		this.exitBtn = new Button("EXIT");
		this.exitBtn.setMinWidth(250);
		this.exitBtn.setOnAction(e ->{
			this.league.close();
			Platform.exit();
		});
		
		this.buttonsBox = new VBox();
		this.buttonsBox.setSpacing(30);
		this.buttonsBox.setPadding(new Insets(10, 0, 10, 0));
		
		this.buttonsBox.getChildren().addAll(this.showMemoryUsageBtn, this.exitBtn);
		
		this.otherGridPane.add(this.buttonsBox, 0, 0);
		this.otherGridPane.setAlignment(Pos.CENTER);
		
	}

}
