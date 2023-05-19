package grafikus;

/**
 * A felelőssége, hogy ha rákenték egy doktorra, akkor beállítja rajta,
 * hogy egy adott számú körig bénult (nem tud lépni).
 */
public class Stun extends Agens{


    /**
     * Meghívja az ős konstruktorát és beállítja a megadott hatóidőt.
     * @param duration - hatóidő
     */
    public Stun(int duration){
        super(duration);
    }

    /**
     * Meghívja az ős konstruktorát és beállít egy random hatóidőt.
     */
    public Stun(){
        super();
    }
    /**
     * Beállítja, hogy adott számú körig bénult a kent doktor.
     * @param on Ezen a doktoron lesz az ágensnek hatása
     */
    @Override
    public void HandleEffect(Doctor on) {
        on.SetStun(duration);
    }
    public String toString(){
        return "Stun";
    }
}
