package grafikus;

/**
 * Ez az osztály testesíti meg az anyag kifejezést.
 */
public class Material {

    /**
     * Az anyag mennyisége.
     */
    private int amount;

    /**
     * Kostruktor
     * @param amount
     */
    public Material(int amount){
        this.amount = amount;
    }

    /**
     * Explicit getter
     * Visszaadja az anyagmennyiséget.
     * @return
     */
    public int GetAmount() {
        return amount;
    }

    /**
     * Explicit setter
     * Beállítja az anyagmennyiséget.
     * @param a
     */
    public void SetAmount(int a){
        amount = a;
    }
}
