package grafikus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EndPanel extends JPanel {

    private int n;

    EndPanel(int n){
        this.n = n;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(30,150,30));
        g.fillRect(0,0, 800, 600);

        Font font = new Font("Arial", Font.PLAIN, 50);
        g.setFont(font);

        g.setColor(Color.BLACK);
        if(n == 0){
            g.drawString("Player" + (Game.GetCurrentPlayerIndex()+1) + " won the game", 100, 310);
        }else{
            g.drawString("Everyone died or became a bear", 30, 310);
            g.drawString("         THE GAME IS OVER", 10, 400);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

}
