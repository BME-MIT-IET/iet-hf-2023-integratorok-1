package grafikus;

/**
 * Ez egy mező. Ez az osztály az üres mezőért felel. Ha ide lép egy játékos, akkor semmi
 * különleges nem történik.
 */
public class Empty extends Field{

    /**
     * Létrehoz egy empty fieldet.
     */
    public Empty(String id){
        super(id);
    }

    /**
     * A mező doktorainak listájához fűzi a paraméterül
     * kapott doktort.
     * @param doc - A mezőre lépő Doctor
     */
    public void AddDoctor(Doctor doc){
        doctors.add(doc);
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
        return "empty";
    }
}
