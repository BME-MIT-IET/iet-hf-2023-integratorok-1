package grafikus;

/**
 * Ez egy mező. Ha erre a mezőre egy doktor, akkor ennek az osztálynak a felelőssége, hogy
 * megtanítsa neki, az itt található genetikai kódot, ha ezt még nem tudta.
 */
public class Laboratory extends Field{

    protected Code code;          //Laboratóriumban fellelhető genetikai kód.
    /**
     * Explicit konstruktor
     * Létrehoz egy labort random kóddal.
     */
    public Laboratory(String id){
        super(id);
        code = new Code();
    }

    /**
     * Explicit konstruktor
     * Létrehoz egy labort a megadott kóddal rajta..
     * @param code
     */
    public Laboratory(Code code, String id){
        super(id);
        this.code = code;
    }

    /**
     * A paraméterül kapott doktor meglévő kódjaihoz
     * hozzáadja az itt található kódot.
     * @param doc - A mezőre lépő Doctor
     */
    @Override
    public void AddDoctor(Doctor doc) {
        doctors.add(doc);
        doc.LearnCode(code);
    }

    /**
     * A mező típusától függően kiírja, a rajta található anyagmennyiséget
     * /felszerelést /megtanulható genetikai kód típusát.
     */
    public void FieldInfo(){
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
        System.out.println("\tCode: " + code.toString());
        System.out.println("\tNumber of Neighbors: " + neighbors.size());
    }

    public String toString(){
        return "lab";
    }


}
