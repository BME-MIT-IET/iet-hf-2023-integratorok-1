package grafikus;

import java.util.*;

/**
 * Absztrakt ősosztály a különböző mezőknek.
 * Ismeri a szomszédait a különböző irányokban és a rajta lévő doktorokat.
 */
public abstract class Field {

    protected String ID;

    protected ArrayList<Field> neighbors; // A mezővel szomszédos mezők listája.

    protected ArrayList<Doctor> doctors; // A mezőn lévő doktorok listája.



    /**
     * Explicit konstruktor
     */
    public Field(String id){
        this.ID = id;
        doctors = new ArrayList<>();
        neighbors = new ArrayList<>();
    }

    /**
     * Absztrakt függvény, ami a leszármazottakban van
     * megvalósítva.
     * @param doc - A mezőre lépő Doctor
     */
    public abstract void AddDoctor(Doctor doc);

    /**
     *  Nem csinál semmit. Csak a Storage-ben van megvalósítva.
     */
    public void BearAttack(){}

    /**
     * Visszaadja a mezőn lévő doktoroklistáját.
     * @return - Doctorok listája
     */
    public ArrayList<Doctor> GetDoctors(){
        return doctors;
    }

    public ArrayList<Field> GetNeighbors() {return neighbors; }

    /**
     * A paraméterül kapott doktortörlődik a listán szereplő doktorok közül.
     * @param doc - Mezőről ellépő Doctor
     */
    public void RemoveDoctor(Doctor doc){
        doctors.remove(doc);
    }

    /**
     * Visszaad egy random szomszédot, a vitustánc effektus
     * segédfüggvénye.
     * @return - Random szomszéd
     */
    public Field GetRandomNeighbor(){
        Random r = new Random();
        int index = r.nextInt(neighbors.size());
        return neighbors.get(index);
    }

    /**
     * Iniciílizáló segédfüggvény
     * @param f - a szomszédnak adandó mező
     */
    public void AddNeighbor(Field f) {
        neighbors.add(f);
    }

    /**
     * A mező típusától függően kiírja, a rajta található anyagmennyiséget
     * /felszerelést /megtanulható genetikai kód típusát.
     */
    public abstract void FieldInfo();

    public String GetID() {
        return ID;
    }
}
