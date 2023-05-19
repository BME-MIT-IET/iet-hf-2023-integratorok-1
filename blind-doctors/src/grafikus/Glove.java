package grafikus;

/**
 * Ez egy felszerelés. Azért felel, hogy ha van ilyene a játékosnak, akkor visszadobódik arra az
 * ágens, aki kenni akarta
 */
public class Glove extends Equipment{

    private int usesLeft;                // Ennyi használat lehetséges még. (Esetünkben mindig 3-ról indul.)

    /**
     * Létrehozza a kesztyűt, beállítja, hogy a usesLeftet, hogy 3 legyen.
     */
    public Glove(){
        usesLeft = 3;
    }

    /**
     * Igazzal tér vissza.
     * @return: Igazzal tér vissza.
     */
    @Override
    public boolean CanThrowBack(){
        return usesLeft > 0;
    }

    /**
     * Csökkenti a usesLeft értékét eggyel és igazzal tér vissza,
     * ha elérte a 0-át, különben false-al.
     * @return
     */
    public boolean DecreaseUsesLeft(){
        if(usesLeft > 0)
            usesLeft -= 1;
        if(usesLeft == 0)
            return true;
        return false;
    }

    public String toString(){
        return "glove";
    }
}
