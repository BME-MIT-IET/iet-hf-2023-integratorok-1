package Automaton;

import com.athaydes.automaton.*;
import grafikus.Main;
import org.junit.*;

public class ChooseRandomMapTester {
	@Test
	public void ChooseRandomMapTest(){
		System.out.println("ChooseRandomMapGenTest");
		Main.main(null);
		Swinger swinger = Swinger.forSwingWindow();

		swinger	.clickOn("text:random")
				.clickOn("text:START GAME")
				.pause(300);
	}
}
