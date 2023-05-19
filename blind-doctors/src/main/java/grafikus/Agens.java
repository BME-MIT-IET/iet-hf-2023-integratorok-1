package grafikus;

import java.util.Random;

/**
 * Absztrakt ősosztály a különböző ágenseknek.
 */
public abstract class Agens {

    protected int duration;                 // Az ágens hatásának ideje körökben mérve.

    /**
     * Paraméteres explicit konstruktor
     * @param duration
     */
    public Agens(int duration){
        this.duration = duration;
    }

    /**
     * Paraméter nélküli explicit konstruktor
     */
    public Agens(){
        Random rn = new Random();
        duration = rn.nextInt()%5 + 1;
    }
    /**
     * Absztrakt függvény, ami leszármazottakban van
     * megvalósítva. A különböző ágensek különböző hatással vannak arra akire rákenik.
     *
     * @param on Ezen a doktoron lesz az ágensnek hatása
     */
    public abstract void HandleEffect(Doctor on);
}
