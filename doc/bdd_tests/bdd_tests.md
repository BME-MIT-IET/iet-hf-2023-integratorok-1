# BDD tesztek készítése

## Felhasznált függőségek

A Cucumber beüzemeléséhez a következő függőségeket használtam:

```xml
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>4.2.0</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit</artifactId>
    <version>4.2.0</version>
</dependency>
```

- A Cucumber beállítása a Maven projektben relatíve egyszerű volt. A függőségek hozzáadása a pom.xml fájlhoz lehetővé tette a Cucumber használatát a projektben.
- A Cucumber tesztek létrehozása során a feature fájlok definiálása jelentős szerepet játszik. Az egyes feature fájlok nagy részében több scenario-t is definiáltunk.
- A Cucumber futtatásához a tesztekhez tartozó futtatóosztályt (runner class) kellett létrehoznunk, amelyek összekapcsolják a Gherkin leírásokat a Java tesztkódokkal.
- A Gherkin leírásokhoz tartozó kód megírása roppant magától értetődő volt és egész jól is sikerült szerveznünk a kódot. (Több StepDefinition és egy Data osztály)
- A Cucumber beüzemelése a Maven projektben lehetővé tette a könnyű integrációt a jövőbeli fejlesztési folyamatba. A tesztek automatikusan lefutnak a CI/CD rendszerben, ami hozzájárul a megbízható és minőségi szoftver fejlesztéséhez.