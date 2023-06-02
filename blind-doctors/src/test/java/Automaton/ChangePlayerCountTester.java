package Automaton;

import com.athaydes.automaton.Swinger;
import grafikus.Main;
import org.junit.Test;

import java.awt.*;

import static com.athaydes.automaton.selector.StringSelectors.matchingAll;

public class ChangePlayerCountTester {
	@Test
	public void RandomChangeFieldsCountTest(){
		System.out.println("RandomChangeFieldsCountTest");
		Main.main(null);
		Swinger swinger = Swinger.forSwingWindow();

		swinger	.clickOn("text:random");

		Component fieldscount = swinger.getAt(matchingAll( "type:JTextField", "text:2"));
		swinger	.doubleClickOn(fieldscount)
				.type("3")
				.clickOn("text:START GAME")
				.pause(300);
	}
}
