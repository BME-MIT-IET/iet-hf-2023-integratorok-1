package grafikus;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class StepDefinitionsSteal {
    @Given("there are two doctors on an empty field")
    public void there_are_two_doctors_on_an_empty_field() {
        Data.doctor = new Doctor("d1");
        Data.doctor2 = new Doctor("d2");
        Data.empty = new Empty("e1");
        Data.empty.AddDoctor(Data.doctor);
        Data.empty.AddDoctor(Data.doctor2);
    }

    @Given("both of them have {int} material")
    public void both_of_them_have_material(int material) {
        Data.doctor.AddMaterial(new Material(material));
        Data.doctor2.AddMaterial(new Material(material));
    }

    @Given("the second doctor is stunned")
    public void the_second_doctor_is_stunned() {
        Data.doctor2.SetStun(3);
    }

    @When("the first doctor steals material from the second")
    public void the_first_doctor_steals_material_from_the_second() {
        Data.doctor.StealMaterial(Data.doctor2);
    }

    @Given("the second doctor has a bag")
    public void theSecondDoctorHasABag() {
        Data.bag = new Bag();
        Data.doctor2.AddEquipment(Data.bag);
    }

    @When("the first doctor steals the bag")
    public void theFirstDoctorStealsTheBag() {
        Data.doctor.StealEquipment(Data.doctor2, Data.bag);
    }

    @Then("the doctor now has a bag")
    public void theDoctorNowHasABag() {
        assertEquals(Data.doctor.GetEquipment().get(0).toString(), "bag");
    }
}
