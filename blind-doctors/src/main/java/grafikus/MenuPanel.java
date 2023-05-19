package grafikus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A játék elindításakorjelenik meg
 */
public class MenuPanel extends JPanel{
    private JTextField numOfPlayers;
    private JTextField numOfFields;

    private JRadioButton randomButton;
    private JRadioButton predefinedButton;
    private ButtonGroup group;

    private JButton startGame;
    
    public MenuPanel(){
        initComponents();
    }

    private void initComponents() {
        this.setLayout(null);

        Font radioFont = new Font(Font.DIALOG, Font.BOLD, 20);

        randomButton = new JRadioButton("random");
        randomButton.setBounds(200, 200, 150,30);
        randomButton.setFont(radioFont);
        randomButton.setOpaque(false);
        this.add(randomButton);

        predefinedButton = new JRadioButton("predefined");
        predefinedButton.setBounds(390, 200, 200, 30);
        predefinedButton.setFont(radioFont);
        predefinedButton.setOpaque(false);
        predefinedButton.setSelected(true);
        this.add(predefinedButton);

        //Group radio buttons
        group = new ButtonGroup();
        group.add(randomButton);
        group.add(predefinedButton);


        numOfFields = new JTextField("20");
        numOfFields.setBounds(450, 260, 30, 30);
        this.add(numOfFields);


        numOfPlayers = new JTextField("2");
        numOfPlayers.setBounds(360, 330, 30, 30);
        this.add(numOfPlayers);


        startGame = new JButton("START GAME");
        startGame.setBounds(250, 430, 300, 75);
        startGame.setBackground(Color.WHITE);
        startGame.addActionListener(new startGameListener());
        this.add(startGame);


    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage cover = null;
        try {
            cover = ImageIO.read(new File("img\\blindDocCover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(cover, 0, 0, null);


        //Title
        Font font = new Font(Font.DIALOG, Font.BOLD, 50);
        g.setColor(Color.RED);
        g.setFont(font);
        g.drawString("BLIND DOCTORS", 200, 75);

        //Choose map generation
        font = new Font("Arial", Font.BOLD, 20);
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString("Choose map generation:", 170, 170);

        g.drawString("Number of Players:", 170, 350);


        font = new Font(Font.DIALOG, Font.BOLD, 16);
        g.setFont(font);
        g.drawString("Number of fields in case of random:", 170, 280);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }



    class startGameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {


            Main.StartGame(Integer.parseInt(numOfPlayers.getText()),-1);
/*
            if(predefinedButton.isSelected()){
                if(numOfPlayers.getText().matches("[0-9]+")){
                    Main.StartGame(Integer.parseInt(numOfPlayers.getText()),-1);
                }
            }else{
                if(numOfPlayers.getText().matches("[0-9]+") && numOfFields.getText().matches("[0-9]+")){
                    Main.StartGame(Integer.parseInt(numOfPlayers.getText()),
                            Integer.parseInt(numOfFields.getText()));
                }
            }
            */

        }

    }


}
