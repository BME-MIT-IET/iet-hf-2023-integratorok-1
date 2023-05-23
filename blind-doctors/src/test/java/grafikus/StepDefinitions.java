package grafikus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    Doctor doctor;
    Shelter shelter;

    @Given("there is a doctor")
    public void there_is_a_doctor() {
        doctor = new Doctor("1");
        System.out.println(doctor.hashCode());
    }

    @Given("the doctor has {int} max material")
    public void the_doctor_has_max_material(int capacity) {
        assertEquals(capacity, doctor.GetCapacity());
    }

    @Given("there is a shelter")
    public void there_is_a_shelter() {
        shelter = new Shelter(2, "1");
    }

    @When("the doctor steps on the shelter")
    public void the_doctor_steps_on_the_shelter() {
        shelter.AddDoctor(doctor);
    }

    @Then("the doctor has max {int} material")
    public void then_doctor_has_max_material(int capacity) {
        assertEquals(capacity, doctor.GetCapacity());
    }


}
