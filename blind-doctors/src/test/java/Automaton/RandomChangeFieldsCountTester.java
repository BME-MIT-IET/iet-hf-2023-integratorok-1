package Automaton;

import com.athaydes.automaton.Swinger;
import grafikus.Main;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static com.athaydes.automaton.selector.StringSelectors.matchingAll;

public class RandomChangeFieldsCountTester {
	@Test
	public void RandomChangeFieldsCountTest(){
		System.out.println("RandomChangeFieldsCountTest");
		Main.main(null);
		Swinger swinger = Swinger.forSwingWindow();

		swinger	.clickOn("text:random");

		Component fieldscount = swinger.getAt(matchingAll( "type:JTextField", "text:20"));
		swinger	.doubleClickOn(fieldscount)
				.type("5")
				.clickOn("text:START GAME")
				.pause(300);
	}
}
