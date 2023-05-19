package grafikus;

import java.util.Random;

/**
 * Ez egy mező. Ha erre a mezőre lép egy doktor, akkor ennek az osztálynak a felelőssége, hogy
 * hozzáadja az itt található felszerelést a doktor felszereléseihez.
 */
public class Shelter extends Field{
    /**
     Az óvóhelyen található védőfelszerelés.
     */
    private int eq;

    /**
     * explicit konstruktor
     * @param e - felszerelés
     */


    public Shelter(int e, String id){
        super(id);
        eq = e;
    }

    /**
     * A paraméterül kapott doktor megkapja az óvóhelyen
     * található felszerelést.
     * @param doc - A mezőre lépő Doctor
     */
    @Override
    public void AddDoctor(Doctor doc) {
        doctors.add(doc);
        Equipment e = null;
        switch(eq){
            case 1:
                e = new Axe();
                break;
            case 2:
                e = new Bag();
                break;
            case 3:
                e = new Cape();
                break;
            case 4:
                e = new Glove();
                break;
            default:
                e = new Bag();
        }
        doc.AddEquipment(e);
    }

    /**
     * A mező típusától függően kiírja, a rajta található anyagmennyiséget
     * /felszerelést /megtanulható genetikai kód típusát.
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
        System.out.println("\tNumber of Neighbors: " + neighbors.size());
    }

    public String toString(){
        return "shelter";
    }
}
