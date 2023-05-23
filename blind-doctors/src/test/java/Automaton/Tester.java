package Automaton;

import com.athaydes.automaton.*;
import grafikus.Main;
import org.junit.*;

public class Tester {
	@Test
	public void test(){
		System.out.println( "Running test" );
		Main.main(null);
		Swinger swinger = Swinger.forSwingWindow();
		swinger.clickOn("text:START GAME");
		swinger.pause(2000);
	}
}
