package grafikus;

/**
 * Ez egy felszerelés. Azért felel, hogy ha van ilyene a játékosnak, akkor 82.3% eséllyel
 * hatástalan lesz a rákent ágens. Ha több Cape-je (Védőköpenye) van, akkor elég egynek
 * blokkolni az ágenst.
 */
public class Cape extends Equipment{

    /**
     * implicit konstruktor
     * Létrehozza a Cape-et
     */
    public Cape(){}

    /**
     * Megmondja az adott Cape-ről, hogy az tud-e blokkolni az adott ágenskenés ellen.
     * Minden egyes alkalommal amikor meg van hívva,
     * akkor 82.3 % eséllyel igazat 17,7 % eséllyel meg hamisat ad vissza.
     * @return igaz érték
     */
    @Override
    public boolean CanBlock(){

        if(+Math.random() > 0.177)
            return true;
        return false;


    }
    public String toString(){
        return "cape";
    }
}
