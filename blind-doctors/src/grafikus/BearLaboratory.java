package grafikus;

/**
 * Ez egy mező. Ha erre a mezőre lép egy doktor, akkor ennek az osztálynak a felelőssége, hogy
 * megpróbálja medvevírussal megfertőzni az ide lépő doktort. Ha a doktornak sikerül kivédeni,
 * akkor viszont az is a felelőssége, hogy ennek a doktornak megtanítsa az itt található genetikai kódot, ha ezt még nem tudta
 */
public class BearLaboratory extends Laboratory {
    private BearAgens bearAgens;        // A medvevírus.

    /**
     * Létrehoz egy BearLaboratory-t random genetikai kóddal.
     */
    public BearLaboratory(String id){
        super(id);
        bearAgens = new BearAgens();
    }

    /**
     * Létrehoz egy BearLaboratory-t adott c genetikai kóddal.
     */
    public BearLaboratory(Code c, String id) {
        super(c, id);
        bearAgens = new BearAgens();
    }

    /**
     * Megpróbálja megfertőzni medvevírussal a doktort.
     * Ha viszont a doktor megvédte magát, akkor viszont megtanítja a kódját.
     * Meghívja a doktor CanProtectFromLab fv-ét. Ha false-al tér vissza, akkor meghívja a vírusa HandleEffect-jét,
     * aminek átadja a doktort. Ha igazzal tér vissza, akkor viszont a doktor LearnCode-ját hívja meg a kódjával.
     * @param doc - A mezőre lépő Doctor
     */
    public void AddDoctor(Doctor doc){
        doctors.add(doc);
        if(!doc.CanProtectFromLab()){
            bearAgens.HandleEffect(doc);
        }
        else{
            doc.LearnCode(code);
        }

    }

    /**
     * Kiírja a mező információit
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
        return "bearlab";
    }
}
