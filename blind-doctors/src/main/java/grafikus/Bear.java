package grafikus;

import java.util.ArrayList;

/**
 * Ez az osztály felel a módosításban szereplő medve megvalósításáért.
 * A Doctorból származik le.
 */
public class Bear extends Doctor{
    private BearAgens bearagens;            /** Kifogyhatatlan medvevírus.*/

    /**
     * Létrehoz egy Bear a paraméterül kapott Doctorból.
     * @param d megfertőzött Doctor
     */
    public Bear(Doctor d, String id){
        super(id);
        this.field = d.GetField();
        bearagens = new BearAgens();
    }

    /**
     * Felülírja a Doktorban szereplőt, hogy ne lehessen rá ágenst kenni. Ez azt jelenti, hogy üres a törzse.
     * @param d - Támadott Doktor
     * @param a - Hasznalt agens
     */
    public void AttackedBy(Doctor d, Agens a){
        return;
    }

    /**
     * Felülírja a Doktorban szereplőt, hogy a Bear Laboratory-val ne legyen baj.
     * Ez azt jelenti, hogy üres a törzse. Igazzal tér vissza.
     * @return
     */
    public boolean CanProtectFromLab(){
        return true;
    }

    /**
     *  A Doktor DoctorInfojának felüldefiniálása. Kiírja a mezőjének azonosító száma, típusa, doktor által ismert kódok (itt nincs ilyen),
     *  felszerelései (itt nincs ilyen), kapacitása (itt nincs ilyen), kábításból/védelemből hátralévő idő (itt nincs ilyen),
     *  rá ható ágensek (itt nincs ilyen), azonos mezőn lévő másik doktorok, illetve hogy medve-e a doktor.
     */

    public void DoctorInfo(String id){
        System.out.println("Bear info:");
        System.out.println("\tBear ID: " + id);
        System.out.println("\tField ID: " + field.GetID());
        System.out.println("\tField Type: " + field.toString());
        if(otherDoctors.size() == 0){
            System.out.println("\t" + "Doctors on the same field: null");
        }
        else{
            System.out.println("\t" + "Doctors on the same field:");
            for(Doctor doc: otherDoctors){
                System.out.println("\t\t" + doc.GetID());
            }
        }
    }

    /**
     * Felülírja a Doktorban szereplőt, hogy medve ne tudja megnyerni a játékot. Ez azt jelenti, hogy üres a törzse.
     * @return
     */
    public boolean DoIKnowAllGenCodes(){
        return false;
    }

    public boolean PlayerTurn(){
        /**
         * Rendes játéknál itt hívódik a StebpRandom
         */
        return true;
    }

    /**
     * Lekezeli a medve léptetését. Szól a mezőknek, hogy medve lépett rájuk.
     * Továbbá a mezőn lévő összes játékost megpróbálja megfertőzni a kifogyhatatlan medvevírusával.
     * Meghívja a RemoveDoctor-t a jelenlegi mezőjén, az az uj (kapott) mezőjén az AddDoctor-t,
     * a BearAttack-ot és a GetDoctors-t.
     * Majd végigmegy az összes kapott Doktoron és meghívja rajtuk az AttackedBy-t,
     * amiben önmagát és a saját medveágensét adja meg.
     * @param f - ide lép
     */
    public void Step(Field f){
        if(isAlive()){
            field.RemoveDoctor(this);
            field = f;
            f.AddDoctor(this);
            otherDoctors = f.GetDoctors();
            f.BearAttack();

            ArrayList<Doctor> doctorList = new ArrayList<>();
            doctorList = f.GetDoctors();

            for(Doctor d : doctorList){
                d.AttackedBy(this, bearagens);
            }
        }
    }

    /**
     * Kiirja az osztaly nevet
     */
    public String toString(){
        return "Bear";
    }

    /**
     * Visszaadja, hogy medve
     */
    @Override
    public boolean isBear(){
        return true;
    }


}
