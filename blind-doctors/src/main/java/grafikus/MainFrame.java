package grafikus;

import javax.swing.*;

public class MainFrame extends JFrame {

    MainFrame(){
        this.setTitle("Blind Doctors");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().add(new MenuPanel());
        this.pack();
    }



}
