package grafikus;

/**
 * Ez egy felszerelés. Azért felel, hogy ha egy virológus birtokába kerül. akkor az a virológus
 * több anyag tárolására lesz képes. Minél több Bag-je van egy virológusnak, annál több anyag
 * fér el nála.
 */
public class Bag extends Equipment{

    /**
     * implicit konstruktor
     */
    public Bag(){}

    /**
     * Igazzal tér vissza, mivel a zsák felszerelés képes több anyag
     * tárolására.
     *
     * @return igaz érték
     */
    @Override
    public boolean CanCarryMore()
    {
        return true;
    }

    /**
     * Kiirja az osztaly nevet
     */
    public String toString(){
        return "bag";
    }
}
