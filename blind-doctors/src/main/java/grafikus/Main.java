package grafikus;

public class Main {

    private static MainFrame game;

    /**
     *
     * Betölti a pályát és a mezőkre helyezi a játékosokat
     *
     * @param players A játékosok száma
     * @param fields A mezők száma
     */
    public static void StartGame(int players, int fields){
        if(fields > 0){

        }else{
            Game.LoadMap("TestMap.txt");
            Game.AddPlayers(players);
        }
        game.getContentPane().removeAll();
        if(Game.GetCurrentPlayer().isBear()){
            game.getContentPane().add(new BearPanel());
        }else{
            game.getContentPane().add(new GamePanel(Game.GetCurrentPlayer(), true));
        }
        game.validate();

    }

    /**
     *
     * A paraméterül kapott mezőre lépteti a játékost, és kitörli az előzőről, ha volt előző
     *
     * @param f Erre a mezőre lépteti
     */
    public static void Stepped(Field f){
        Game.GetCurrentPlayer().Step(f);
        game.getContentPane().removeAll();
        if(Game.isEndGame()) {
            game.getContentPane().add(new EndPanel(0));
        }else if(Game.isAllBears()){
            game.getContentPane().add(new EndPanel(1));
        } else if(Game.GetCurrentPlayer().isBear()){
            game.getContentPane().add(new BearPanel());
        }else{
            game.getContentPane().add(new GamePanel(Game.GetCurrentPlayer(), false));
        }
        game.validate();
    }

    /**
     * Ez a függvény felelős a játékosok léptetéséért, megkeresi a következő nem stunolt és nem medve játékost
     */
    public static void EndTurn() {
        Game.GetCurrentPlayer().PlayerTurn();
        Game.NextPlayer();
        game.getContentPane().removeAll();
        if (Game.isAllBears()) {
            game.getContentPane().add(new EndPanel(1));
        }else if(Game.GetCurrentPlayer().isBear()) {
            Game.GetCurrentPlayer().StepRandom();
            game.getContentPane().add(new BearPanel());
        }else if (!Game.GetCurrentPlayer().isAlive()) {
            game.getContentPane().add(new DeadPanel());
        }else if(Game.GetCurrentPlayer().GetStun() > 0){
            game.getContentPane().add(new StunnedPanel());
        }else{
            game.getContentPane().add(new GamePanel(Game.GetCurrentPlayer(), true));
        }

        game.validate();
    }

    /**
     * Elindítja a játékot
     *
     */
    public static void main(String[] args) {
        game = new MainFrame();
        game.setVisible(true);
    }

    /**
     * Frissíti a Panelt az új paraméterek szerint
     */
    public static void UpdateView(boolean b){
        game.getContentPane().removeAll();
        game.getContentPane().add(new GamePanel(Game.GetCurrentPlayer(), b));
        game.validate();
    }
}
