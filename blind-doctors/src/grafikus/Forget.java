package grafikus;

/**
 * A felelőssége, hogy ha rákenték egy doktorra, akkor az felejtse el a megtanult genetikai
 * kódjait.
 */
public class Forget extends Agens{

    /**
     *  Meghívja az ős konstruktorát és beállítja a megadott hatóidőt.
     */
    public Forget(int duration){
        super(duration);
    }

    /**
     * Meghívja az ős konstruktorát és beállít egy random hatóidőt.
     */
    public Forget(){
        super();
    }
    /**
     * Végrehajtja, hogy a paraméterül kapott doktor elfelejtse a
     * megtanult genetikai kódjait.
     * @param on Ezen a doktoron lesz az ágensnek hatása
     */
    @Override
    public void HandleEffect(Doctor on) {
        on.ForgetLearntCodes();
    }
    public String toString(){
        return "Forget";
    }
}
