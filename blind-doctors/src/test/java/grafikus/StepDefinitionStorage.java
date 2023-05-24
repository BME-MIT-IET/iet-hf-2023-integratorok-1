package grafikus;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class StepDefinitionStorage {

    @Given("the doctor begins with {int} material")
    public void the_doctor_begins_with_material(int materialCapacity) {
        Data.doctor.SetMaterial(materialCapacity);
    }

    @Given("there is a storage")
    public void there_is_a_storage() {
        Data.storage = new Storage(50, "1");
    }

    @When("the doctor steps on the storage")
    public void the_doctor_steps_on_the_storage() {
        Data.storage.AddDoctor(Data.doctor);
    }

    @Then("the doctor has {int} material")
    public void then_doctor_has_material(int materialCapacity) {
        assertEquals(materialCapacity, Data.doctor.GetMaterial().GetAmount());
    }

    @Given("the doctor has a bag")
    public void the_doctor_has_a_bag() {
        Data.doctor.AddEquipment(new Bag());
    }
}
