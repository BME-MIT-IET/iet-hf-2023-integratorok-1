package grafikus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class StepDefinitionsLaboratory {

    @Given("the doctor has no code")
    public void the_doctor_has_no_code() {
        assertEquals(Data.doctor.GetCodes().size(), 0);
    }

    @Given("there is a laboratory with protection code")
    public void there_is_a_laboratory_with_protection_code() {
        Data.laboratory = new Laboratory( new Code("protection", new Protection(5), new Material(20)), "7");
    }

    @When("the doctor steps on the laboratory with protection code")
    public void the_doctor_steps_on_the_laboratory_with_protection_code() {
        Data.laboratory.AddDoctor(Data.doctor);
    }

    @Then("the doctor has protection code")
    public void the_doctor_has_protection_code() {
        assertEquals(Data.doctor.GetCodes().get(0).toString(), "protection");
    }
}
