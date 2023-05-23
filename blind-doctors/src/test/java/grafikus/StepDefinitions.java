package grafikus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    @Given("there is a doctor")
    public void there_is_a_doctor() {
        Data.doctor = new Doctor("1");
    }

    @Given("the doctor has {int} max material")
    public void the_doctor_has_max_material(int capacity) {
        assertEquals(capacity, Data.doctor.GetCapacity());
    }

    @Given("there is a shelter")
    public void there_is_a_shelter() {
        Data.shelter = new Shelter(2, "1");
    }

    @When("the doctor steps on the shelter")
    public void the_doctor_steps_on_the_shelter() {
        Data.shelter.AddDoctor(Data.doctor);
    }

    @Then("the doctor has max {int} material")
    public void then_doctor_has_max_material(int capacity) {
        assertEquals(capacity, Data.doctor.GetCapacity());
    }
}
