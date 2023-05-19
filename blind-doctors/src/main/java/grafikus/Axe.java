package grafikus;

/**
 * Ez egy felszerelés. Azért felel, hogy ha egy virológus birtokába kerül,
 * akkor az a virológus képes lesz megölni vele valakit vagy medvét egyszer.
 */
public class Axe extends Equipment{

    /**
      Ennyi használat lehetséges még. (Esetünkben mindig 1.)
     */
    private int usesLeft;

    /**
     * Konstruktor
     */
    public Axe(){
        usesLeft = 1;
    }

    /**
        Igazzal ter vissza, hogyha a usesLeft értéke 1.
     */
    public boolean CanKill(){
        return usesLeft == 1;
    }

    /**
        Csokkenti a usesLeft erteket eggyel es igazzal ter vissza, ha elerte a 0-at, kulonben false-al.
     */
    public boolean DecreaseUsesLeft(){
        if(usesLeft > 0) {
            usesLeft -= 1;
            return true;
        }
        return false;
    }

    /**
     * Kiirja a baltat attol fuggoen, hogy elhasznaltak mar-e
     */
    public String toString(){
        if(usesLeft > 0)
            return "axe";
        return "usedaxe";
    }
}
