# Manuális kód átvizsgálása és Statikus analízis eszköz futtatása

## Statikus analízis
Az általunk választott megoldás a statikus analízishez a SonarCloud, amelyet könnyedén össze lehet kötni a GitHub platformmal.<br> A SonarCloud futtatásához létrehoztunk egy új action-t a GitHub-on, amelyhez elkészítettük a szükséges _.yml_ fájlt. Ez az új action lehetővé teszi, hogy automatikusan futtassuk a SonarCloud statikus analízisét a projektünkben. A _.yml_ fájlban megadtuk azokat a beállításokat és konfigurációkat, amelyek szükségesek a SonarCloud helyes működéséhez. A legfontosabb beállítások az alábbiak voltak:
- SonarCloud API-jához való hozzáférést biztosító Tokenek megadása
<br>
![](tokenek.PNG)


- Git események megadása, amelyekre automatikusan lefut az analízis
<br>
![](gitEsemenyek.PNG)


- Futtatás parancs leírása
<br>
![](parancs.PNG)


A _.yml_ konfigurációs fájl létrehozásán kívül a projektünket leíró _pom.xml_ fájlt is módosítottuk. A tulajdonságokhoz hozzáadtunk két új mezőt, amelyekkel megadjuk a SonarCloud URL-jét, valamint a szervezet nevét. Ezek az alábbi képen láthatóak.
<br>
![](tulajdonsagok.PNG) 


