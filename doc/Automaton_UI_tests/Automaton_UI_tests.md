# Automaton tesztek készítése

- Az Automatonnal lehetséges java swing alapú felhasználói felületet tesztelni.
- Véleményem szerint kényelmes használni, bár tapasztalatom szerint sajnos van benne hiba.

## Beüzemelés maven-nel
```xml
<dependency>
	<groupId>com.athaydes.automaton</groupId>
	<artifactId>Automaton</artifactId>
	<version>1.3.2</version>
	<scope>test</scope>
</dependency>
```
## UI teszt nem futhat githubon
- A pom.xml-ben a build ben pluginként lehet exclude-olni a 'mvn test' alól a tesztet.
```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-surefire-plugin</artifactId>
	<configuration>
		<excludes>
			<exclude>Automaton/**</exclude>
		</excludes>
	</configuration>
</plugin>
```

## Működésre bírás
- Jelenleg egy hibát nem sikerült kijavítanom, a project dokumentációja alapján ennek nem is kéne léteznie: Egymás után együtt lefutatott tesztekből csak az első fut le, a többi nem.
- Alapból a project nem képes elérni a programunkban a megfelelő osztályokat, ezért ezeket elérhetővé kell tenni.
```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-surefire-plugin</artifactId>
	<configuration>
		<argLine>
			--add-opens java.base/java.lang=ALL-UNNAMED
			--add-opens java.base/java.lang.reflect=ALL-UNNAMED
			--add-opens java.base/java.util=ALL-UNNAMED
			--add-opens java.base/java.util.concurrent=ALL-UNNAMED
			--add-opens java.desktop/java.awt=ALL-UNNAMED
			--add-opens java.desktop/java.awt.event=ALL-UNNAMED
			--add-opens java.desktop/javax.swing=ALL-UNNAMED
			--add-opens java.desktop/javax.swing.text=ALL-UNNAMED
		</argLine>
	</configuration>
</plugin>
```
## Tesztek készítése
- Egy Swinger osztályon keresztül érhetőek el a komponensek.
- Ezt a swinger osztályt a programunk indítása után lehet inicializálni.
- Lehet a komponensekre név, típus, szöveg alapján szűrni, ezekre különböző műveleteket végezni(kattintás, írás...).
- A projectnek hasznos dokumentációja van, különböző frameworkökhöz, különböző nehézségű példákkal, ezek segítségével könnyen lehet teszteket készíteni.
- Bizonyos tesztek futásánál kell egy kis delayt beszúrni, különben még nem létező gombra akar kattintani a program. Például menüpanel -> gamepanel váltásnál.