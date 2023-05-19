package grafikus;


/**
 * Absztrakt ősosztály a különböző felszereléseknek. Az összes függvénye hamis értéket ad
 * vissza alapértelmezetten, annak érdekében, hogy ha a leszármazottban nincsen felüldefiniálva,
 * akkor működjenek a lekérdező a algoritmusok az ilyen típusú heterogén kollekción.
 */
public abstract class Equipment {
    /**
     * @return Hamis értéket ad vissza.
     */
    public boolean CanBlock()
    {
        return false;
    }
    /**
     * @return Hamis értéket ad vissza.
     */
    public boolean CanCarryMore()
    {
        return false;
    }
    /**
     * @return Hamis értéket ad vissza.
     */
    public boolean CanThrowBack()
    {
        return false;
    }
    /**
     * @return Hamis értéket ad vissza.
     */
    public boolean CanKill()
    {
        return false;
    }

    /**
     * Nem csinál semmit. Csak az Axe-ben és a Glove-ban van megvalósítva.
     * @return false
     */
    public boolean DecreaseUsesLeft(){
        return false;
    }
}
