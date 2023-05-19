package grafikus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StunnedPanel extends JPanel {

    private JButton endTurn = new JButton("End Turn");

    StunnedPanel(){
        initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        endTurn.setBounds(700, 280, 90, 40);
        endTurn.addActionListener(new endListener());
        this.add(endTurn);
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(30,150,30));
        g.fillRect(0,0, 800, 600);

        Font font = new Font("Arial", Font.PLAIN, 50);
        g.setFont(font);

        g.setColor(Color.BLACK);
        int id = (Game.GetCurrentPlayerIndex() % 4) + 1;
        BufferedImage playerImage = null;
        try {
            playerImage = ImageIO.read(new File("img\\doctor" + id + "stunned.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawString("Player" + (Game.GetCurrentPlayerIndex()+1) + "'s stunned", 230, 310);
        g.drawImage(playerImage, 190, 250, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    class endListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Main.EndTurn();
        }
    }

}
