package grafikus;

import java.util.Random;

/**
 * Ezeket gyűjti a doktor. Eltárolja magában, hogy milyen ágens és mennyi anyagból hozható
 * létre belőle.
 */
public class Code {

    private Agens agens;                // A code-ból megvalósítható ágens

    private String name;                // Genetikai kód neve, ezzel különböztethető meg a doktorban tárolt halmazban

    private Material cost;              // Az ebből a kódból készíthető agens koltsege.

    /**
     * explicit konstruktor
     * @param name kód neve
     * @param agens megvalósítható ágens
     * @param cost Az ágens előállítás költsége
     */
    public Code(String name, Agens agens, Material cost)
    {
        this.name = name;
        this.agens = agens;
        this.cost = cost;
    }
    /**
     * Random Code konstruktor
     * Random kódot hoz létre.
     */
    public Code(){
        Random rn = new Random();
        int num = rn.nextInt(4) + 1;
        int duration = rn.nextInt(5);
        int cost = rn.nextInt(50);
        switch (num){
            case 1:
                this.name = "dance";
                Dance d = new Dance(duration);
                this.agens = d;
                this.cost = new Material(cost);
                break;
            case 2:
                this.name = "forget";
                Forget f = new Forget(duration);
                this.agens = f;
                this.cost = new Material(cost);
                break;
            case 3:
                this.name = "stun";
                Stun s = new Stun(duration);
                this.agens = s;
                this.cost = new Material(cost);
                break;
            case 4:
                this.name = "protection";
                Protection p = new Protection(duration);
                this.agens = p;
                this.cost = new Material(cost);
                break;
            default:
        }
    }

    /**
     * Visszaadja a kódból készíthető ágenst. Közben
     * csökkenti a paraméterül kapott doktor anyagkészletét.
     * @param attacker a támadó aki készíti az ágenst
     * @return tartalmazott ágens
     */
    public Agens CreateAgens(Doctor attacker) throws LowMaterialException{
        try {
            attacker.ReduceMaterial(cost);
        }
        catch (LowMaterialException e) {
            throw e;
        }
        return agens;
    }

    public String toString(){
        return name;
    }

    /**
     * Visszaadja a kódnak a költségét
     */
    public int GetCost(){ return cost.GetAmount(); }
}
