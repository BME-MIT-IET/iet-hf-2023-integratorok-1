package grafikus;

import java.io.*;
import java.util.*;

/**
 * Game osztály. A játék indításáért és
 * befejezésért felel. Létrehozza a mezőket és
 * doktorkat, amelyeket eltárol.
 */
public class Game {
    public Game(){}

    /**
     * A menüben levo opciokat irja
     */
    public static void InitializeMenu(){
        System.out.println(1 + "\tDoctor steps on Empty");
        System.out.println(2 + "\tDoctor steps on Laboratory");
        System.out.println(3 + "\tDoctor steps on Shelter");
        System.out.println(4 + "\tDoctor steps on Storage");
        System.out.println(5 + "\tDoctor steps on Storage, but he is full already");
        System.out.println(6 + "\tDoctor steps on Bear Laboratory and becomes a bear");
        System.out.println(7 + "\tDoctor steps on Bear Laboratory, but protects himself");
        System.out.println(8 + "\tDoctor puts Stun Agens on Doctor");
        System.out.println(9 + "\tDoctor puts Protection Agens on himself");
        System.out.println(10 + "\tDoctor puts Forget Agens on Doctor");
        System.out.println(11 + "\tDoctor puts Dance Agens on Doctor");
        System.out.println(12 + "\tBear tries to attack another Bear");
        System.out.println(13 + "\tDoctor uses Axe on Doctor");
        System.out.println(14 + "\tDoctor doesn’t have enough Material for Agens");
        System.out.println(15 + "\tDoctor throws back an Agens with a Glove");
        System.out.println(16 + "\tDoctor doesn't throw back an Agens but he has Protection");
        System.out.println(17 + "\tDoctor protects himself with his Cape");
        System.out.println(18 + "\tDoctor steals material");
        System.out.println(19 + "\tDoctor steals Bag");
        System.out.println(20 + "\tDoctor steals Axe");
        System.out.println(21 + "\tDoctor steals Cape");
        System.out.println(22 + "\tBear Steps on Random neighbor Field");
        System.out.println(23 + "\tBear infects Doctor with bear virus successfully");
        System.out.println(24 + "\tBear tries to infect Doctors with bear virus, not successfully");
        System.out.println(25 + "\tDoctor steps on a laboratory and gets a code of an Agens and wins");
        System.out.println(26 + "\tDoctor puts dance on himself");
        System.out.println(27 + "\tDoctor steps on shelter, but gets no equipment, because he has 3 already");
        System.out.println(28 + "\tDoctor drops equipment.");
        System.out.println(29 + "\tFirst complex test");
        System.out.println(30 + "\tSecond complex test");
        System.out.println(31 + "\tThird complex test");
        System.out.println(32 + "\tIF YOU WANT A CUSTOM TEST, make a test32.txt with the commands and then choose this");
    }
    //file nevek az egyes teszt esetekhez rendelve
    private static final Map<Integer, String> testCases = new HashMap<>();
    //A palyan talalhato mezok
    private static Map<String, Field> fields = new HashMap<>();
    //A palyan talalhato dokik
    private static Map<String, Doctor> doctors = new HashMap<>();

    private static ArrayList<Doctor> doctorsList = new ArrayList<>();

    private static ArrayList<Field> fieldsList = new ArrayList<>();

    private static final Random random = new Random();

    //true ha vege a jateknak
    private static boolean endGame = false;

    private static int currentPlayer;

    public static void NextPlayer(){
        if(currentPlayer < doctorsList.size()-1)
            currentPlayer++;
        else
            currentPlayer = 0;
    }

    /**
     * Medvévé változtatja
     * @param who melyik doctort
     * @param b medve
     */
    public static void TurnIntoBear(Doctor who, Bear b){
        doctorsList.set(Integer.parseInt(who.GetID()), b);
    }

    /**
     * Visszaadja a jelenlegi doctort
     * @return
     */
    public static Doctor GetCurrentPlayer(){
        return doctorsList.get(currentPlayer);
    }

    /**
     * visszaadja a jelenlegi docctor indexét
     * @return
     */
    public static int GetCurrentPlayerIndex(){
        return currentPlayer;
    }

    public static ArrayList<Field> GetFields(){
        return fieldsList;
    }

    public static ArrayList<Doctor> GetDoctors(){
        return doctorsList;
    }

    /**
     * Hozzaad egy doktort a jatekhoz
     * @param doc - doktor
     */
    public static void AddDoctor(Doctor doc){
        doctors.put(doc.GetID(), doc);
    }

    /**
     * Kivesz (megöl) egy doktort a jatekbol
     * @param doc - doktor
     */
    public static void RemoveDoctor(Doctor doc){
        doctors.remove(doc.GetID());

    }


    // Minden bemeneti test file neve test + a test soraszama a doksiban pl.: test1, test17
    static{
        for(int i = 0; i<32; i++){
            testCases.put(i+1, "test" + (i+1) + ".txt");
        }

        currentPlayer = 0;
    }

    /**
     * A megadott filet beolvassa soronket es feldolgozza a sorokat a command fv-el
     */
    static void readTestFile(int index) {
        try (BufferedReader reader = new BufferedReader(new FileReader(testCases.get(index)))) {
            String line = reader.readLine();
            while (line != null && !endGame) {
                Command(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * nagy switch case a kulonbozo parancsoknak
     * @param command - paramterek
     */
    public static void Command(String command){
        String[] params = command.split(" ");
        if(params.length > 0){
            switch(params[0]){
                case "create_field":
                    CreateField(params);
                    break;
                case "place_doctor":
                    PlaceDoctor(params);
                    break;
                case "doctor_step":
                    DoctorStep(params);
                    break;
                case "pair":
                    Pair(params);
                    break;
                case "doctor_learn":
                    LearnCode(params);
                    break;
                case "add_mat":
                    GiveMaterial(params);
                    break;
                case "give_eq":
                    AddEquipment(params);
                    break;
                case "place_agens":
                    PlaceAgens(params);
                    break;
                case "attack_with":
                    AttackOtherDoctor(params);
                    break;
                case "selfcast_with":
                    SelfCast(params);
                    break;
                case "drop":
                    DropEquipment(params);
                    break;
                case "steal_eq_with":
                    StealEquipment(params);
                    break;
                case "steal_mat_with":
                    StealMaterial(params);
                    break;
                case "kill_with":
                    KillDoctor(params);
                    break;
                case "doc_info":
                    DoctorInfo(params);
                    break;
                case "field_info":
                    FieldInfo(params);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + params[0]);
            }
        }
    }

    /**
     * Letrehoz egy mezot a parameterek alapjan
     * @param params - paramterek
     */
    public static void CreateField(String[] params){
        String id = params[1];
        Code c = new Code();
        switch (params[2]){
            case "1":
                fields.put(params[1], new Empty(id));
                break;

            case "2":
                switch (params[3]) {
                    case "1":
                        c = new Code("forget", new Forget(5), new Material(20));
                        break;
                    case "2":
                        c = new Code("protection", new Protection(5), new Material(20));
                        break;
                    case "3":
                        c = new Code("stun", new Stun(5), new Material(20));
                        break;
                    case "4":
                        c = new Code("dance", new Dance(5), new Material(20));
                        break;
                    default:
                        c = new Code();
                        break;
                }
                fields.put(params[1], new Laboratory(c,id));
                break;

            case "3":
                fields.put(params[1], new Shelter(Integer.parseInt(params[3]),id));
                break;
            case "4":
                fields.put(params[1], new Storage(id, 50));
                break;

            case "5":
                switch (params[3]) {
                    case "1":
                        c = new Code("forget", new Forget(5), new Material(20));
                        break;
                    case "2":
                        c = new Code("protection", new Protection(5), new Material(20));
                        break;
                    case "3":
                        c = new Code("stun", new Stun(5), new Material(20));
                        break;
                    case "4":
                        c = new Code("dance", new Dance(5), new Material(20));
                        break;
                    default:
                        c = new Code();
                        break;
                }
                fields.put(params[1], new BearLaboratory(c, id));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + params[2]);
        }


    }


    /**
     * Osszeparosit ket mezot
     * @param params - paramterek
     */
    public static void Pair(String[] params){
        fields.get(params[1]).AddNeighbor(fields.get(params[2]));
        fields.get(params[2]).AddNeighbor(fields.get(params[1]));
    }

    /**
     * Lerak egy dokit egy mezore
     * @param params - paramterek
     */
    public static void PlaceDoctor(String[] params){
        doctors.put(params[1], new Doctor(params[1]));
        doctors.get(params[1]).SetField(fields.get(params[2]));
        doctors.get(params[1]).Step(fields.get(params[2]));

    }

    /**
     * Lepteti a dokit
     * @param params - paramterek
     */
    public static void DoctorStep(String[] params){

        //playerturnt meg kell hivni, hogy lephet e egyaltalan
        if(!doctors.get(params[1]).PlayerTurn()){
            return;
        }
        //nem biztos szerintem azert, mert a testeknel csak
        if(params.length > 2){
            doctors.get(params[1]).Step(fields.get(params[2]));
        }else{
            doctors.get(params[1]).StepRandom();
        }
    }

    /**
     * Megtanit egy kodot a dokinak
     * @param params - paramterek
     */
    public static void LearnCode(String[] params){
        Code c;
        switch (params[2]) {
            case "1":
                c = new Code("forget", new Forget(5), new Material(20));
                break;
            case "2":
                c = new Code("protection", new Protection(5), new Material(20));
                break;
            case "3":
                c = new Code("stun", new Stun(5), new Material(20));
                break;
            case "4":
                c = new Code("dance", new Dance(5), new Material(20));
                break;
            default:
            c = new Code();
            break;
        }
        doctors.get(params[1]).LearnCode(c);
    }

    /**
     * Ad anyagot a dokinak
     * @param params - paramterek
     */
    public static void GiveMaterial(String[] params){
        doctors.get(params[1]).AddMaterial(new Material(Integer.parseInt(params[2])));
    }


    public static void LoadMap(String input) {
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String line = reader.readLine();
            while (line != null && !endGame) {
                Command(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ez rakja a mezőkre a játékosokat
     * @param players playerek száma
     */
    public static void AddPlayers(int players){
        Collection<Field> fieldValues = fields.values();
        ArrayList<Field> notUsed = new ArrayList<>(fieldValues);
        ArrayList<Field> allfields = new ArrayList<>(fieldValues);
        for(int i = 0; i < players; i++){
            if(notUsed.size() > 0){
                int r = random.nextInt(notUsed.size());
                Doctor d = new Doctor(Integer.toString(i));
                doctors.put(d.GetID(), d);
                doctorsList.add(d);
                d.Step(notUsed.get(r));
                notUsed.remove(r);


            }else{
                int r = random.nextInt(fields.size());
                Doctor d = new Doctor(Integer.toString(i));
                doctors.put(d.GetID(), d);
                doctorsList.add(d);
                d.Step(allfields.get(r));


            }

        }
    }

    /**
     * Equipementet ad a fieldhez
     * @param params beadott txt
     */
    public static void AddEquipment(String[] params){
        Equipment e = null;
        switch(params[2]){
            case "1":
                e = new Axe();
                break;
            case "2":
                e = new Bag();
                break;
            case "3":
                e = new Cape();
                break;
            case "4":
                e = new Glove();
                break;
            default:
                e = new Bag();
        }
        doctors.get(params[1]).AddEquipment(e);
    }

    /**
     * Agenst rak egy dokira
     * @param params - paramterek
     */
    public static void PlaceAgens(String[] params){
        Agens a = null;
        switch(params[2]){
            case "1":
                a = new Forget(1);
                break;
            case "2":
                a = new Protection(5);
                break;
            case "3":
                a = new Stun(5);
                break;
            case "4":
                a = new Dance(3);
                break;
            case "5":
                a = new BearAgens();
            default:
                a = new Protection(0);
        }
        a.HandleEffect(doctors.get(params[1]));
    }

    /**
     * Masik doki megtamadasa
     * @param params
     */
    public static void AttackOtherDoctor(String[] params){
        Code c;
        switch (params[3]) {
            case "1":
                c = new Code("forget", new Forget(5), new Material(20));
                break;
            case "2":
                c = new Code("protection", new Protection(5), new Material(20));
                break;
            case "3":
                c = new Code("stun", new Stun(5), new Material(20));
                break;
            case "4":
                c = new Code("dance", new Dance(5), new Material(20));
                break;
            default:
                c = new Code();
                break;
        }

        doctors.get(params[1]).Attack(doctors.get(params[2]), c);
    }

    /**
     * Eldob egy felszerelest
     * @param params - paramterek
     */
    public static void DropEquipment(String[] params){
        String lose = null;
        switch(params[2]){
            case "1":
                lose = "Axe";
                break;
            case "2":
                lose = "Bag";
                break;
            case "3":
                lose = "Cape";
                break;
            case "4":
                lose = "Glove";
                break;
            default:
                lose = "Bag";
        }

        Equipment eq = null;
        for(Equipment e : doctors.get(params[1]).GetEquipment()){
            if(e.toString().equals(lose)){
                eq = e;
                break;
            }
        }
        if(eq != null){
            doctors.get(params[1]).LoseEquipment(eq);
        }
    }

    /**
     * Ellop egy felszerelest
     * @param params
     */
    public static void StealEquipment(String[] params){
        Equipment e = null;
        switch(params[3]){
            case "1":
                e = new Axe();
                break;
            case "2":
                e = new Bag();
                break;
            case "3":
                e = new Cape();
                break;
            case "4":
                e = new Glove();
                break;
            default:
                e = new Bag();
        }
        doctors.get(params[1]).StealEquipment(doctors.get(params[2]), e);
    }

    /**
     * Ellopja a maximalis mennyisegu anyagot
     * @param params
     */
    public static void StealMaterial(String[] params){
        doctors.get(params[1]).StealMaterial(doctors.get(params[2]));
    }

    /**
     * Megol egy dokit
     * @param params
     */
    public static void KillDoctor(String[] params){
        doctors.get(params[1]).UseAxe(doctors.get(params[2]));
    }

    /**
     * Doktor infoit irja ki
     * @param params
     */
    public static void DoctorInfo(String[] params){
        if(doctors.containsKey(params[1])){
            doctors.get(params[1]).DoctorInfo(params[1]);
        }else{
            System.out.println(params[1] + " Doctor or Bear died or didn't exist.");
        }
    }

    /**
     * Mezo info
     * @param params
     */
    public static void FieldInfo(String[] params){
        fields.get(params[1]).FieldInfo();
    }

    /**
     * magar rak agenst
     * @param params
     */
    public static void SelfCast(String[] params){
        Code c;
        switch (params[3]) {
            case "1":
                c = new Code("forget", new Forget(5), new Material(20));
                break;
            case "2":
                c = new Code("protection", new Protection(5), new Material(20));
                break;
            case "3":
                c = new Code("stun", new Stun(5), new Material(20));
                break;
            case "4":
                c = new Code("dance", new Dance(5), new Material(20));
                break;
            default:
                c = new Code();
                break;
        }
        doctors.get(params[1]).AgensOnSelf(c);
    }

    /**
     * Elindítja a játékot.
     */
    public static void StartGame(){

    }

    /**
     * Végetvet a játéknak.
     */
    public static void EndGame(){
        endGame = true;
    }

    public static boolean isEndGame(){
        return endGame;
    }

    /**
     * Mondenki medve
     * @return egy boolean
     */
    public static boolean isAllBears(){
        boolean allbears = true;
        for (Doctor doctor : doctorsList) {
            if(!doctor.isBear())
                allbears = false;
        }
        return allbears;
    }
}
