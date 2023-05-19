package grafikus;

/**
 * A felelőssége, hogy ha rákenték egy virológusra,
 * akkor az lépjen odébb pár mezővel véletlenszerűen.
 */
public class Dance extends Agens{


    /**
     * explicit konstruktor
     * beállítja, hogy milyen sokszor tegye majd arréb a doctort
     */
    public Dance(int duration){
        super(duration);
    }

    /**
     * Meghívja az ős konstruktorát és beállít egy random hatóidőt.
     */
    public Dance(){
        super();
    }
    /**
     * Végrehajtja a doktor véletlenszerű odébb léptetését.
     * @param on Ezen a doktoron lesz az ágensnek hatása
     */
    @Override
    public void HandleEffect(Doctor on) {
        for(int i = 0; i < duration; i++)
            on.StepRandom();
    }

    public String toString(){
        return "Dance";
    }
}
