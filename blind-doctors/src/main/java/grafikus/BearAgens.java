package grafikus;

/**
 * A felelőssége, hogy ha rákenték egy doktorra, akkor a doktorból medve legyen.
 */
public class BearAgens extends Agens{

    /**
     * Explicit Konstruktor paraméterrel.
     * Létrehoz egy BearAgens ágenst adott hatóidővel.
     * @param duration
     */
    public BearAgens(int duration){
        super(duration);
    }
    /**
     * Explicit Konstruktor paraméter nélkül.
     * Létrehoz egy BearAgens ágenst.
     */
    public BearAgens(){
        super();
    }

    /**
     * Megfertőzi medvevírussal a kapott doktort. (Létrehoz egy medvét.)
     * Meghívja a Game RemoveDoctor fv-ét. Létrehoz egy medvét.
     * Majd ezt a medvét hozzáadja a Game-hez az AddDoctor fv-el.
     * @param on Ezen a doktoron lesz az ágensnek hatása
     */
    @Override
    public void HandleEffect(Doctor on) {
        Bear b = new Bear(on, on.GetID());
        on.GetField().GetDoctors().set(on.GetField().GetDoctors().indexOf(on), b);
        Game.TurnIntoBear(on, b);
    }

    /**
     * Kiirja az osztaly nevet
     */
    public String toString(){
        return "BearAgens";
    }
}
