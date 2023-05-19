package grafikus;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *A GamePanel megrajzolása
 */
public class GamePanel extends JPanel{
    /**
     * A mező alakjának grafikai megjelenítése
     */
    private Polygon poly2 = new Polygon();
    /**
     * A mező típusának grafikai megjelenítés
     */
    private BufferedImage currentField;
    private ArrayList<BufferedImage> equipment = new ArrayList<>();
    private ArrayList<DoctorButton> doctors = new ArrayList<>();
    private ArrayList<BufferedImage> codes = new ArrayList<>();
    /**
     * Az irányítást végző gombok
     */
    private JButton endTurn = new JButton("End Turn");
    private JButton drop = new JButton("Drop Equipment");
    private JButton attack = new JButton("Attack");
    private JButton stealMaterial = new JButton("Steal Material");
    private JButton stealEq = new JButton("Steal Equipment");
    private JButton kill = new JButton("Kill");
    private JButton selfcast = new JButton("Self Cast");

    /**
     * referencia a kiválasztott Doctorra
     */
    private DoctorButton chosenDoctorButton = null;

    private Doctor player;
    private BufferedImage playerImage;

    private boolean needStepButtons;

    /**
     *Construktor
     * @param d- körön lévő játékos
     * @param needStepButtons- lépett-e már a játékos a körében
     */
    public GamePanel(Doctor d, boolean needStepButtons) {
        this.needStepButtons = needStepButtons;
        player = d;
        initComponents();
    }

    /**
     * GamePanel összes elemének inicializálása
     */
    private void initComponents() {
        this.setLayout(null);

        //currentfield image and playerImage
        try {
            currentField = ImageIO.read(new File("img\\" + player.GetField().toString() + ".png"));
            int id = (Integer.parseInt(player.GetID()) % 4) + 1;

            if(player.GetStun() < 1)
                playerImage = ImageIO.read(new File("img\\doctor" + id + ".png"));
            else
                playerImage = ImageIO.read(new File("img\\doctor" + id + "stunned.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Kirajzolj az összes saját kódját
         */
        for (Code code : player.GetCodes()) {
            try {
                codes.add(ImageIO.read(new File("img\\" + code.toString() + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Kirajzolja az összes többi doctort a fieldre
         */
        int size = player.GetField().GetDoctors().size();
        int j = 0;
        for(int i = 0; i < size; i++){
            Doctor doc = player.GetField().GetDoctors().get(i);
            if(!doc.equals(player)){
                doctors.add(new DoctorButton());
                int docid = Integer.parseInt(doc.GetID()) % 4 + 1;
                if(doc.isBear() && !doc.isAlive()) {
                    doctors.get(j).setIcon(new ImageIcon("img\\deadbear.png"));
                }else if(doc.isBear()) {
                    doctors.get(j).setIcon(new ImageIcon("img\\bear.png"));
                }else if(!doc.isAlive()) {
                    doctors.get(j).setIcon(new ImageIcon("img\\doctor" + docid + "dead.png"));

                    //Ha stunolva van
                }else if(doc.GetStun() > 0){
                    doctors.get(j).setIcon(new ImageIcon("img\\doctor" + docid + "stunned.png"));

                }/*ha nincs stunolva*/ else{
                    doctors.get(j).setIcon(new ImageIcon("img\\doctor" + docid + ".png"));
                }
                int x = (int) (385 + 120 * Math.cos(j * 2 * Math.PI / size));
                int y = (int) (260 + 120 * Math.sin(j * 2 * Math.PI / size));
                doctors.get(j).setBounds(x, y, 31, 80);

                if(doc.isBear())
                    doctors.get(j).setBounds(x, y, 50, 50);


                doctors.get(j).SetDoctor(doc);
                doctors.get(j).addActionListener(new doctorListener());
                this.add(doctors.get(j));
                j++;
            }
        }


        JLabel material = new JLabel("Material: " + player.GetMaterial().GetAmount() +
                "/" + player.GetCapacity());
        material.setBounds(10, 400, 300, 30);
        material.setFont(new Font("Arial", Font.BOLD, 20));
        material.setForeground(Color.BLACK);
        this.add(material);

        JLabel protection = new JLabel("Protection: " + player.GetProtection());
        protection.setBounds(10, 350, 300, 30);
        protection.setFont(new Font("Arial", Font.BOLD, 20));
        protection.setForeground(Color.BLACK);
        this.add(protection);



        int directions = 4 + player.GetField().GetNeighbors().size();
        int actualDirections = player.GetField().GetNeighbors().size();


        for (int i = 0; i < directions; i++)
            poly2.addPoint((int) (400 + 200 * Math.cos(i * 2 * Math.PI / directions)),
                    (int) (300 + 200 * Math.sin(i * 2 * Math.PI / directions)));


        if(needStepButtons){
            ArrayList<JButton> buttons = new ArrayList<>();
            for (int i = 0; i < (2*directions); i++){
                if(i % 2 == 1 && i < 2*actualDirections){
                    buttons.add(new RoundButton(String.valueOf((i-1)/2 + 1)));
                    buttons.get((i-1)/2).setBounds(
                            (int) (380 + 200 * Math.cos(i * 2 * Math.PI / (2*directions))),
                            (int) (280 + 200 * Math.sin(i * 2 * Math.PI / (2*directions))), 50, 50);
                }
            }

            for(int i = 0; i < buttons.size(); i++){
                buttons.get(i).addActionListener(new stepListener(player.GetField().GetNeighbors().get(i)));
                if(player.GetPreviousField() != null && player.GetPreviousField().equals(player.GetField().GetNeighbors().get(i)))
                    buttons.get(i).setBackground(Color.darkGray);
                this.add(buttons.get(i));
            }
        }






        if(!player.GetEquipment().isEmpty()){
            drop.setBounds(600, 550, 130, 40);
            drop.addActionListener(new dropListener());
            this.add(drop);
        }

        if(!player.GetCodes().isEmpty()){
            selfcast.setBounds(5, 550, 120, 40);
            selfcast.addActionListener(new selfCastListener());
            this.add(selfcast);
        }


        attack.setBounds(130, 550, 120, 40);
        attack.addActionListener(new attackListener());
        this.add(attack);
        attack.setVisible(false);


        stealEq.setBounds(255, 550, 130, 40);
        stealEq.addActionListener(new stealEqListener());
        this.add(stealEq);
        stealEq.setVisible(false);

        stealMaterial.setBounds(390, 550, 130, 40);
        stealMaterial.addActionListener(new stealMatListener());
        this.add(stealMaterial);
        stealMaterial.setVisible(false);

        kill.setBounds(525, 550, 70, 40);
        kill.addActionListener(new killListener());
        this.add(kill);
        kill.setVisible(false);

        if(!needStepButtons){
            endTurn.setBounds(700, 280, 90, 40);
            endTurn.addActionListener(new endListener());
            this.add(endTurn);
        }
    }

    /**
     * overridolja a panel paintComponentjét
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        equipment.clear();
        super.paintComponent(g);
        g.setColor(new Color(82, 76, 76));
        g.fillRect(0,0, 800, 600);
        g.setColor(new Color(30,150,30));
        g.fillPolygon(poly2);

        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.drawPolygon(poly2);

        g.drawImage(currentField, 337, 237, null);

        for (Equipment eq : player.GetEquipment()) {
            try {
                equipment.add(ImageIO.read(new File("img\\" + eq.toString() + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < equipment.size(); i++) {
            g.drawImage(equipment.get(i), 10 + i*31, 450, null);
        }

        for (int i = 0; i < codes.size(); i++) {
            g.drawImage(codes.get(i), 10 + i*31, 500, null);
        }


        
        Font font = new Font("Arial", Font.PLAIN, 50);
        g.setFont(font);

        g.drawString("Player" + (Game.GetCurrentPlayerIndex()+1) + "'s turn", 250, 75);
        g.drawImage(playerImage, 210, 15, null);
    }

    /**
     * overridolja agetPreferedSize fv-t
     * @return az ablak méretét
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    /**
     * ActionListener a Step-nek
     */
    class stepListener implements ActionListener{

        Field field;

        public stepListener(Field f){
            field = f;
        }

        /**
         * Meghívja a Main.Stepped fv-t
         * @param e gombnyomás
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Main.Stepped(field);
        }
    }

    /**
     * Drop gomb ActionListenerje
     */
    class dropListener implements ActionListener{
        /**
         *Add lehetőséget arra, hogy kiválasszuk az eldobandó Equipementet
         * @param e- drop gombnyomás
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> eqs = new ArrayList<>();
            for (Equipment eq : player.GetEquipment()) {
                eqs.add(eq.toString());
            }
            Object[] possibilities = eqs.toArray();
            JButton b = (JButton) e.getSource();
            String s = (String)JOptionPane.showInputDialog(
                    b.getParent().getParent(),
                    "Which equipment of yours do you want to drop?",
                    "Drop Equipment",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    possibilities[0]);


            //If a string was returned, say so.
            if ((s != null) && (s.length() > 0)) {
                GamePanel l = (GamePanel) b.getParent();
                for (Equipment eq : player.GetEquipment()) {
                    if(eq.toString().equals(s)){
                        player.LoseEquipment(eq);
                        break;
                    }
                }
                if(player.GetEquipment().size() < 1){
                    l.remove(b);
                }
                l.repaint();
                return;
            }
        }
    }

    /**
     * Doktor gomb ActionListenerje
     */
    class doctorListener implements ActionListener{
        /**
         * Láthatóvá teszi a kiválasztott doctorral kapcsolatos gombokat
         * @param e gombnyomás
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            DoctorButton b = (DoctorButton) e.getSource();
            GamePanel l = (GamePanel) b.getParent();
            if(chosenDoctorButton != null && chosenDoctorButton.equals(b)){
                attack.setVisible(false);
                stealEq.setVisible(false);
                stealMaterial.setVisible(false);
                kill.setVisible(false);
                l.repaint();
                chosenDoctorButton = null;
            }else{
                chosenDoctorButton = b;
                if(!b.GetDoctor().isAlive())
                    return;

                stealEq.setVisible(false);
                stealMaterial.setVisible(false);
                if(player.GetCodes().size() > 0)
                    attack.setVisible(true);
                //csak akkor jelenjen meg ha stunned b.GetDoctor().GetStun > 0
                if(b.GetDoctor().GetStun() > 0){
                    stealEq.setVisible(true);
                    stealMaterial.setVisible(true);
                }
                //csak akkor ha tud olni = van fejszeje
                if(player.HaveIGotAxe()){
                    System.out.println("Tudok olni");
                    kill.setVisible(true);
                }
                l.repaint();
            }



        }
    }

    /**
     * Ez aktiválódik amikor a játékos saját magára akar ágenst hinteni
     */
    class selfCastListener implements ActionListener{
        /**
         * feldobja a lehetőségeket, hogy melyik agenst akarjuk magunkra rakni
         * @param e gombnyomás
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> codes = new ArrayList<>();
            for (Code code : player.GetCodes()) {
                if(code.toString().equals("protection") || code.toString().equals("dance"))
                    codes.add(code.toString() + ": " + code.GetCost());
            }
            JButton b = (JButton) e.getSource();
            if(codes.isEmpty()){
                JOptionPane.showMessageDialog(b.getParent().getParent(),
                        "You don't have anything to put on yourself!");
                return;
            }

            Object[] possibilities = codes.toArray();

            String s = (String)JOptionPane.showInputDialog(
                    b.getParent().getParent(),
                    "What do you want to put on yourself?",
                    "Self Cast",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    possibilities[0]);


            //If a string was returned, say so.
            if ((s != null) && (s.length() > 0)) {

                String[] ss  = s.split(":");

                s = ss[0];

                Code c = null;
                for (Code code : player.GetCodes()) {
                    if (code.toString().equals(s))
                        c = code;
                }

                if(c.GetCost() > player.GetMaterial().GetAmount()){
                    JOptionPane.showMessageDialog(b.getParent().getParent(),
                            "You dont, have enough material");
                    return;
                }
                player.AgensOnSelf(c);
                Main.UpdateView(needStepButtons);
            }
        }
    }

    /**
     * Ez aktiválódik amikor a játékos végzett a körével
     */
    class endListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Main.EndTurn();
        }
    }

    /**
     * Ez aktiválódik amikor az egyik játékos egy ágens akar rakni egy másik játékosra
     */
    class attackListener implements ActionListener{
        /**
         * A kiválasztott játékost a rendelkezésre álló ágensek közül megtámadhatja eggyel
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> codes = new ArrayList<>();
            for (Code code : player.GetCodes()) {
                codes.add(code.toString() + ": " + code.GetCost());
            }
            JButton b = (JButton) e.getSource();

            Object[] possibilities = codes.toArray();

            String s = (String)JOptionPane.showInputDialog(
                    b.getParent().getParent(),
                    "What do you want to attack with?",
                    "Attack",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    possibilities[0]);


            //If a string was returned, say so.
            if ((s != null) && (s.length() > 0)) {
                String[] ss  = s.split(":");
                s = ss[0];
                Code c = null;
                for (Code code : player.GetCodes()) {
                    if (code.toString().equals(s)) {
                        c = code;
                    }
                }

                if(c.GetCost() > player.GetMaterial().GetAmount()){
                    JOptionPane.showMessageDialog(b.getParent().getParent(),
                            "You dont, have enough material!");
                    return;
                }

                player.Attack(chosenDoctorButton.GetDoctor(), c);
                Main.UpdateView(needStepButtons);
                return;

            }
        }
    }

    /**
     * Ez aktiválódik amikor az egyik játékos egy eszközt akar elvenni egy másik játékostól
     */
    class stealEqListener implements ActionListener{

        /**
         * A kiválasztott játékos összes eszköze közül kiválaszthat egyet
         * @param e gombnyomás
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> eqs = new ArrayList<>();
            for (Equipment eq : chosenDoctorButton.GetDoctor().GetEquipment()) {
                eqs.add(eq.toString());
            }
            JButton b = (JButton) e.getSource();
            if(eqs.isEmpty()){
                JOptionPane.showMessageDialog(b.getParent().getParent(),
                        "This player doesn't have any items!");
                return;
            }

            Object[] possibilities = eqs.toArray();
            String s = (String)JOptionPane.showInputDialog(
                    b.getParent().getParent(),
                    "Which equipment do you want to steal?",
                    "Steal Equipment",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    possibilities,
                    possibilities[0]);


            //If a string was returned, say so.
            if ((s != null) && (s.length() > 0)) {
                GamePanel l = (GamePanel) b.getParent();
                Equipment equipment = null;
                for (Equipment eq : chosenDoctorButton.GetDoctor().GetEquipment()) {
                    if(eq.toString().equals(s)){
                        equipment = eq;
                        break;
                    }
                }
                player.StealEquipment(chosenDoctorButton.GetDoctor(),equipment);
                Main.UpdateView(needStepButtons);
                return;
            }
        }
    }

    /**
     * Ez aktiválódik amikor az egyik játékos meg akar ölni egy másikat
     */
    class killListener implements ActionListener{
        /**
         * Playerhasználja azg Axe-ot
         * @param e killgombnyomás
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            player.UseAxe(chosenDoctorButton.GetDoctor());
            Main.UpdateView(needStepButtons);
        }
    }

    /**
     * Ez aktiválódik amikor az egyik játékos anyagot akar elvenni egy másik játékostól
     */
    class stealMatListener implements ActionListener{
        /**
         * A kiválasztott játékostól elveszi az összes anyagát
         * @param e gombnyoomás
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if(chosenDoctorButton.GetDoctor().GetMaterial().GetAmount() < 1){
                JOptionPane.showMessageDialog(b.getParent().getParent(),
                        "This player doesn't have any material");
                return;
            }

            player.StealMaterial(chosenDoctorButton.GetDoctor());
            Main.UpdateView(needStepButtons);
        }
    }
}

