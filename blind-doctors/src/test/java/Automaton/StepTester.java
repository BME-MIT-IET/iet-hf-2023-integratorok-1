package Automaton;

import com.athaydes.automaton.Swinger;
import grafikus.Main;
import org.junit.Before;
import org.junit.Test;

public class StepTester {
	Swinger swinger;
	@Before
	public void setUp(){
		Main.main(null);
		swinger = Swinger.forSwingWindow();
		swinger	.clickOn("text:START GAME");
		swinger.pause(100);
	}

	@Test
	public void StepTest(){
		System.out.println("StartGameTest");
		swinger	.clickOn("text:1")
				.pause(100)
				.clickOn("text:End Turn")
				.pause(300);
	}
}
