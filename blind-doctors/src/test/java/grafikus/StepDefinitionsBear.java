package grafikus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class StepDefinitionsBear {

    @Given("There are two empty fields connected")
    public void there_are_two_empty_fields_connected() {
        Data.empty = new Empty("e1");
        Data.empty2 = new Empty("e2");
        Data.empty.AddNeighbor(Data.empty2);
        Data.empty2.AddNeighbor(Data.empty);
    }

    @Given("one of them has a doctor the other a bear")
    public void one_of_them_has_a_doctor_the_other_a_bear() {
        Data.bear = new Bear(new Doctor("d1"), "b1");
        Data.doctor = new Doctor("d2");
        Data.bear.SetField(Data.empty);
        Data.doctor.SetField(Data.empty2);
        Data.empty.AddDoctor(Data.bear);
        Data.empty2.AddDoctor(Data.doctor);
    }

    @When("the bear steps to the other field")
    public void the_bear_steps_to_the_other_field() {
        Data.bear.Step(Data.empty2);
        Data.doctor=Data.empty2.GetDoctors().get(0);
    }

    @Then("the doctor becomes a bear")
    public void the_doctor_becomes_a_bear() {
        assertTrue(Data.doctor.isBear());
    }

    @Given("the doctor has an axe")
    public void theDoctorHasAnAxe() {
        Data.doctor.AddEquipment(new Axe());
    }

    @Then("the bear dies")
    public void theBearDies() {
        assertTrue(Data.doctor.isAlive());
    }
}
