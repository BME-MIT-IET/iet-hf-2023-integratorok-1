package Automaton;

import com.athaydes.automaton.Swinger;
import grafikus.Main;
import org.junit.Test;

public class StartGameTester {
	@Test
	public void StartGameTest(){
		System.out.println("StartGameTest");
		Main.main(null);
		Swinger swinger = Swinger.forSwingWindow();

		swinger	.clickOn("text:START GAME")
				.pause(300);
	}
}
