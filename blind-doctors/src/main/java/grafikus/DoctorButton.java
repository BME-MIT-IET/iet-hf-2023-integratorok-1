package grafikus;

import javax.swing.*;

public class DoctorButton extends JButton {
    private Doctor doctor;

    public void SetDoctor(Doctor d){
        doctor = d;
    }

    public Doctor GetDoctor(){
        return doctor;
    }



}
