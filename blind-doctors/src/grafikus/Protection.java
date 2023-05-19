package grafikus;

/**
 * A felelőssége, hogy ha rákenték egy doktorra,
 * akkor beállítja rajta, hogy ő védett egy adott számú körig.
 */
public class Protection extends Agens{

    /**
     * Meghívja az ős konstruktorát és beállítja a megadott hatóidőt.
     */
    public Protection(int duration){
        super(duration);
    }

    /**
     * Meghívja az ős konstruktorát és beállít egy random hatóidőt.
     */
    public Protection(){
        super();
    }

    /**
     * Beállítja az adott doktoron a védettséget a duration-nek
     * megfelelő számú körig.
     * @param on Ezen a doktoron lesz az ágensnek hatása
     */
    @Override
    public void HandleEffect(Doctor on) {
        on.SetProtection(duration);
    }
    public String toString(){
        return "Protection";
    }
}
