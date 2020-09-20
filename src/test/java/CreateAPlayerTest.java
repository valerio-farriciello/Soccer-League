import static org.junit.Assert.*;

import org.junit.Test;

import valerioFarriciello.Controller.League;

public class CreateAPlayerTest {

	@Test
	public void test() {
		League league = new League();
		Object[] playerDetails = {"testfname", "testmname", "testlname", "111111111", "test.email@test.ie", "10", "YES"};
		boolean result = league.createANewPlayer(playerDetails, null);
		assertEquals(true, result);
	}

}
