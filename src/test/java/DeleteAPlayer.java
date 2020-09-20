import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import valerioFarriciello.Controller.League;
import valerioFarriciello.Model.Player;

public class DeleteAPlayer {

	@Test
	public void test() {
		//NOTE: Run CreateAPlayerTest first before running this test
		League league = new League();
		boolean test = true;
		ArrayList<Player> players = league.getAllThePlayers();
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getEmail().equals("test.email@test.ie")) {
				league.deletePlayer(players.get(i));
			}				
		}
		
		players = league.getAllThePlayers();
		//let's try to look for the player again
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getEmail().equals("test.email@test.ie")) {
				test = false;
			}				
		}
		
		assertEquals(true, test);
	}

}
