package grafikus;

import java.util.*;

/**
 * Doktor osztály. Ez az osztály felel a játékos által irányított karakterért.
 * Tud lépni, lebénulni, új genetikai kódokat tanulni, új felszereléseket gyűjteni,
 * felszerelést eldobni és magára vagy más játékosra ágenst kenni.
 */
public class Doctor {
    protected String ID;                          // Doctor azonosítója

    private int capacity;                       // Kapacitás
    private ArrayList<Equipment> equipment;     // Felszerelések listája
    protected Field field;                      // Adott mező
    protected ArrayList<Doctor> otherDoctors;   // Doktorok azonos mezőn
    private HashSet<Code> learntCodes;          // Megtanult kódok listája
    private Material material;                  // Anyagmennyiség
    private int protection;                     // Védettségből hátralévő idő
    private int stun;                           // Kábultságból hátralévő idő
    private Field prevField;

    private boolean isDead;


    public Field GetPreviousField(){
        return prevField;
    }

    public boolean isBear(){
        return false;
    }

    public void Die(){
        isDead = true;
    }

    public boolean isAlive(){
        boolean alive = !isDead;
        return alive;
    }



    /**
     * Létrehoz egy varázslót, és benne létrehozza a listáit az equipementeknek és a megtanult kódjainak.
     * Létrehozza a Materialját és 0-ra inicializálja annak az amount-ját és a capacity-t beállítja 100-ra.
     */
    public Doctor(String id){
        this.ID = id;
        learntCodes = new HashSet<>();
        equipment = new ArrayList<>();
        otherDoctors = new ArrayList<>();
        material = new Material(0);
        capacity = 100;
        protection = 0;
        stun = 0;
        isDead = false;
    }

    /**
     * Ha még nincsen három felszerelése, akkor hozzáadja a kapott felszerelést az equipment listához.
     * Meghívja a felszerelés CanCarryMore fv-ét és ha igazzal tér vissza, akkor meghívja az IncreaseCapacity fv-t is.
     * @param e - Felszerelés
     */
    public void AddEquipment(Equipment e){
        if(equipment.size() < 3) {
            equipment.add(e);
            if (e.CanCarryMore()) {
                IncreaseCapacity();
            }
        } else
            System.out.println("A doktor nem képes több felszerelést magánál hordani.");
    }

    /**
     * Megnöveli a doktor anyagmennyiségét, de csak amíg a kapacitása engedi.
     * @param m - Anyag
     */
    public void AddMaterial(Material m){
        if(material.GetAmount() + m.GetAmount() >= capacity){
            material.SetAmount(capacity);
            //System.out.println("A doktor nem tud több anyagot tárolni.");
            return;
        }
        material.SetAmount(material.GetAmount() + m.GetAmount());
    }

    /**
     * Készít egy ágenst a megadott kódból és magára keni.
     * Meghívja a kiválasztott kód CreateAgens-ét.
     * Majd utána az abból kapott ágensen a HandlEffect-et, aminek átadja önmagát.
     * @param c - code
     */
    public void AgensOnSelf(Code c){
        try {
            Agens a = c.CreateAgens(this);
            a.HandleEffect(this);
        }
        catch(LowMaterialException e){
            System.out.println("Nincs elég anyag az ágenshez.");
        }
    }

    /**
     * Készít egy ágenst a megadott kódból és megpróbálja rákenni a megadott doktorra.
     * Meghívja a kiválasztott kód CreateAgens-ét, majd meghívja a paraméterül kapott doktor AttackedBy fv-ét,
     * aminek a createAgens-ből kapott ágenst és önmagát adja át.
     * @param attacked - Megtámadott doktor
     * @param c - code
     */
    public void Attack(Doctor attacked, Code c) {
        try {
            Agens a = c.CreateAgens(this);
            attacked.AttackedBy(this, a);
        }
        catch(LowMaterialException e){
            System.out.println("Nincs elég anyag az ágenshez.");
        }
    }

    /**
     * Ez kezeli a doktor felé érkező támadást.
     * Megkapja, hogy ki intézte (hogy ha vissza kéne dobni rá) és, hogy milyen ágenst próbálnának meg rákenni.
     * Megkerdezi magát, hogy ki tudja-e védeni a CanProtectFromAgens fv segítségével.
     * Ha nem, akkor meghívja az ágens HandleEffect fv-ét.
     * @param attacker - Tamado
     * @param a - Hasznalt agens
     */
    public void AttackedBy(Doctor attacker, Agens a){
        if(!CanProtectFromAgens(attacker, a))
            a.HandleEffect(this);
    }

    /**
     * Igazzal tér vissza, ha doktor meg tudja védeni magát. Ellenkező esetben hamissal.
     * @param attacker - Tamado
     * @param a - Hasznalt agens
     * @return ha igaz akkor megtudja magat vedeni
     */
    public boolean CanProtectFromAgens(Doctor attacker, Agens a){
        if(HaveIGotAxe()){
            UseAxe(attacker);
        }
        else if(HaveIGotGlove()){
            attacker.AttackedBy(this, a);
            return true;
        }
        else if (HaveIGotProtection()){
            return true;
        }
        else if(HaveIGotCape()){
            return true;
        }
        return false;
    }

    /**
     * Megnézi, hogy meg tudja e védeni magát attól, hogy egy laboratóriumban megfertőződjön.
     * @return - bool érték
     */
    public boolean CanProtectFromLab(){
        if(!HaveIGotProtection()) {
            if(!HaveIGotCape()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Csökkenti a doktor anyag tárolási kapacitását.
     */
    public void DecreaseCapacity(){
        capacity -= 50;
        RemoveSurplusMaterial();
    }

    /**
        Kiírja a mezőjének azonosító száma,
        típusa, doktor által ismert kódok , felszerelései , kapacitása ,
        kábításból/védelemből hátralévő idő, rá ható ágensek, azonos mezőn lévő másik doktorok,
        illetve hogy medve-e a doktor.
     */
    public void DoctorInfo(String id){
        System.out.println("Doctor info:");
        System.out.println("\tDoctor ID: " + id);
        System.out.println("\tField ID: " + field.GetID());
        System.out.println("\tField Type: " + field.toString());
        System.out.println("\tCapacity: " + capacity);
        System.out.println("\tMaterial: " + material.GetAmount());
        System.out.println("\tProtections: " + protection);
        System.out.println("\tStun: " + stun);
        if(learntCodes.size() == 0){
            System.out.println("\tCodes: null");
        }
        else {
            System.out.println("\tCodes:");
            for (Code c : learntCodes) {
                System.out.println("\t\t" + c.toString());
            }
        }

        if(equipment.size() == 0){
            System.out.println("\tEquipment: null");
        }
        else {
            System.out.println("\tEquipment:");
            for (Equipment e : equipment) {
                System.out.println("\t\t" + e.toString());
            }
        }
        if(otherDoctors.size() == 1 ) {
            System.out.println("\t" + "Doctors on the same field: null");
        }
        else{
            System.out.println("\t" + "Doctors on the same field:");
            for(Doctor doc: otherDoctors){
                if(!doc.equals(this))
                System.out.println("\t\t" + doc.GetID());
            }
        }
    }

    /**
     * Megmondja, hogy az összes kódot ismeri-e a doktor.
     * @return Ha igaz akkor megnyerte a jatekot
     */
    public boolean DoIKnowAllGenCodes() {
        if(learntCodes.size() >= 4){
            //System.out.println("Megtanultad az összes kódot.");
            return true;
        }
        //else System.out.println("Megtanultál egy kódot, de van még van mit tanulni...");
        return false;
    }

    /**
     * Kitorli az eddig megtanult genetikai kodokat
     */
    public void ForgetLearntCodes(){
        learntCodes = new HashSet<>();
    }

    /**
     * Megmondja, hogy van-e baltája. Végigmegy az összes felszerelésen és meghívja a CanKill()
     * @return Ha igaz akkor van.
     */
    public boolean HaveIGotAxe(){
        for (Equipment e : equipment) {
            if(e.CanKill()){
                return true;
            }
        }
        return false;
    }

    /**
     * Megkérdezi a felszereléseitől a doktor, hogy van-e köpenye, ami meg tudja védeni.
     * Végigmegy az összes felszerelésen és meghívja a CanBlock()-ot.
     * @return Ha igaz akkor van
     */
    public boolean HaveIGotCape(){
        for (Equipment e : equipment) {
            if(e.CanBlock()){
                return true;
            }
        }
        return false;
    }

    /**
     * Megkérdezi a felszereléseitől a doktor, hogy van-e kesztyűje, ami meg tudja védeni.
     * Végigmegy az összes felszerelésen és meghívja a CanThrowBack-et.
     * Ha van ami igazzal tér vissza, akkor meghívjuk rajta a DecreaseUsesLeft-et.
     * Ha true-t ad vissza, akkor remove-oljuk ezt a felszerelést a listából.
     * @return Ha igaz akkor van
     */
    public boolean HaveIGotGlove(){
        for (Equipment e : equipment) {
            if(e.CanThrowBack()) {
                if(e.DecreaseUsesLeft()){
                    equipment.remove(e);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * egmondja, hogy védett-e a doktor.
     * Ha a doctor protection tagváltozója nagyobb mint 0-a akkor igazzal tér vissza. Különben hamissal.
     * @return Ha igaz akkor van
     */
    public boolean HaveIGotProtection(){
        return protection > 0;
    }

    public ArrayList<Equipment> GetEquipment(){
        return equipment;
    }

    /**
     *  Növeli a doktor anyag tárolási kapacitását.
     *
     */
    public void IncreaseCapacity(){
        capacity += 50;
    }

    /**
     * Megtanít egy új kódot a doktorral.
     * @param c - code
     */
    public void LearnCode(Code c){
        learntCodes.add(c);
        if(DoIKnowAllGenCodes()){
            System.out.println(this.ID + " won the Game!");
            Game.EndGame();
        }
    }

    /**
     * Elveszíti az adott felszerelését a doktor. Ha ez táska volt, akkor csökken a kapacitása is.
     * @param e - felszerelés
     */
    public void LoseEquipment(Equipment e){
        equipment.remove(e);
        if(e.CanCarryMore()){
            DecreaseCapacity();
        }
    }

    /**
     * Elindítja a játékos körét. Ha a stun vagy a protection értéke nagyobb mint 0, akkor csökkenti őket 1-el.
     * Utána ha a stun 0, akkor vár arra, hogy a játékos lépjen, majd, hogy végez-e egy akciót vagy befejezi a körét.
     */
    public boolean PlayerTurn(){
        int currentStun = GetStun();
        if(GetStun() > 0){
            SetStun(GetStun()-1);
        }
        if (GetProtection() > 0) {
            SetProtection(GetProtection() - 1);
        }
        if(currentStun == 0) {
            return true;
        }
        return false;
    }

    /**
     * Csökkenti a doktor anyagkészletét egy adott mennyiséggel.
     */
    public void ReduceMaterial(Material m) throws LowMaterialException {
        if(m.GetAmount() > material.GetAmount()){
            throw new LowMaterialException();
        }
        else {
            material.SetAmount(material.GetAmount()-m.GetAmount());
        }
    }

    /**
     * Ha doktornál több anyag van, mint a kapacitása engedné,
     * akkor ez a függvény csökkenti a doktor többlet anyagmennyiségét.
     */
    public void RemoveSurplusMaterial(){
        if (material.GetAmount() > capacity)
            material.SetAmount(capacity);
    }

    /**
     * Beallitja, hogy a doktor melyik mezon all
     * @param f - Ezen áll a Doktor
     */
    public void SetField(Field f){
        field = f;
    }

    /**
     * Beállítja a játékos védettségét. Ennyi körig lesz védett.
     * @param n - hatóidő
     */
    public void SetProtection(int n){
        protection += n;
    }

    /**
     * Beállítja a játékos bénulását. Ennyi körig lesz lebénulva.
     * @param n - hatóidő
     */
    public void SetStun(int n){
        stun = n;
    }

    /**
     * Ez a függvény csak egy bénult doktort kaphat paraméterül.
     * Ennek a bénult doktornak ellopja a paraméterül kapott felszerelését.
     * Meghívja a kapott doktoron a loseEquipmentet.
     * @param from - Melyik Doktortol
     * @param e - Melyik eszkozt
     */
    public void StealEquipment(Doctor from, Equipment e){
        if(from.GetStun() > 0){
            from.LoseEquipment(e);
            AddEquipment(e);
        }
        else return;
    }

    /**
     * Ez a függvény csak egy bénult doktort kaphat paraméterül.
     * Ettől a bénult doktortól ellopja a lehetséges legtöbb anyagot.
     * @param stunned - bénult doktor
     */
    public void StealMaterial(Doctor stunned){
        if(stunned.GetStun() > 0) {
            this.AddMaterial(stunned.GetMaterial());
            stunned.SetMaterial(0);
        }
        else return;
    }

    /**
     *  Lépteti a játékost az adott mezőre.
     *  Meghívja a RemoveDoctor-t a jelenlegi mezojen, az az uj (kapott) mezojen az AddDoctor-t, és a GetDoctors-t.
     *  @param f - új mező
     */
    public void Step(Field f){
        if(field != null){
            field.RemoveDoctor(this);
        }
        prevField = field;
        field = f;
        f.AddDoctor(this);
        otherDoctors = f.GetDoctors();
    }

    /**
     * Random lépteti a játékost.
     * Lekér egy random szomszédos mezőt a GetRandomNeighbor-al,
     * majd ezzel meghívja a Step-et.
     */
    public void StepRandom(){
        Field randomNeigbour = field.GetRandomNeighbor();
        Step(randomNeigbour);
    }

    /**
     * Kicsorbítja a fejszét a DecreaseUsesLeft-el.
     * Majd meghívja a Game RemoveDoktor fv-ét a paraméterül kapott doktorral.
     * @param on - megölni kívánt doktor
     */
    public void UseAxe(Doctor on){
        for(Equipment axe: equipment){
            if(axe.CanKill()){
                axe.DecreaseUsesLeft();
                on.Die();
                break;
            }
        }
    }

    /**
     * Kiirja, hogy Doctor
     */
    public String toString(){
        return "Doctor";
    }

    /**
     * Lekérdezi, hogy melyik fielden áll a Doki
     * @return field amin a Doktor áll
     */
    public Field GetField() {
        return field;
    }

    /**
     * Lekérdezi a protection értékét
     */
    public int GetProtection() {
        return protection;
    }

    /**
     * Lekérdezi a stun értékét
     */
    public int GetStun() {
        return stun;
    }

    /**
     * Lekérdezi a material értékét
     */
    public Material GetMaterial() {
        return material;
    }

    /**
     * Beállítja a material értékét egy adott értékre
     */
    public void SetMaterial(int amount) {
        this.material.SetAmount(amount);
    }

    public String GetID() {
        return ID;
    }

    public ArrayList<Code> GetCodes(){
        return new ArrayList<Code>(learntCodes);
    }

    public int GetCapacity(){
        return capacity;
    }
}
