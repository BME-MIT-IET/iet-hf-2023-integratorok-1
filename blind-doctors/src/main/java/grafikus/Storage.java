package grafikus;

import java.util.Random;

/**
 * Ez egy mező. Ha erre a mezőre lép egy doktor, akkor ennek az osztálynak a felelőssége, hogy
 * anyagot adjon a doktornak.
 */
public class Storage extends Field{

    private Material material;          // A raktárban fellelhető anyag mennyisége.

    /**
     * Létrehoz egy raktárt random anyagmennyiséggel.
     */
    public Storage(String id, int amount){
        super(id);
        Random rn = new Random();
        material = new Material(amount);
    }

    /**
     * Létrehoz egy raktárt a megadott anyagmennyiséggel.
     * @param amount
     */
    public Storage(int amount, String id){
        super(id);
        material = new Material(amount);
    }
    /**
     * A paraméterül kapott doktor megkapja a raktárban
     * fellelhető anyag mennyiséget.
     * @param doc - A mezőre lépő Doctor
     */
    @Override
    public void AddDoctor(Doctor doc) {
        doctors.add(doc);
        doc.AddMaterial(material);
    }

    public void BearAttack(){
        material.SetAmount(0);
    }

    /**
     * A mező információit kiírja a konzolra
     */
    @Override
    public void FieldInfo() {
        System.out.println("Field Info:");
        System.out.println("\tField ID: " + GetID());
        if(doctors.size() == 0){
            System.out.println("\tDoctor ID: null");
        }
        else{
            System.out.println("\tDoctor ID:");
            for(Doctor doc: doctors){
                System.out.println("\t\t" + doc.GetID());
            }
        }
        System.out.println("\tField Type: " + this.toString());
        System.out.println("\tAmount of material: " + material.GetAmount());
        System.out.println("\tNumber of Neighbors: " + neighbors.size());
    }

    public String toString(){
        return "storage";
    }
}
