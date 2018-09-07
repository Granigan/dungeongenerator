## User's Guide for Dungeon Generator

The application creates and saves an ASCII map with variable amount of rooms, and a connecting corridor. The rooms are connected to the corridor with a door (+).

### How to use
The application is best run with NetBeans, via the Main.java. This is also recommended as the Main.java holds the parameters for choosing the size of the map (width/height), minimum size of the rooms to be added, and a maximum number that can be added to the size of the rooms. The number of **attempts** to place a room is also relevant, as that directly affects how densily filled with rooms the map will be. As the last feature, two new variables were added: maxDoorsPerRoom and MultipleDoorOdds. As the names imply, these affect how many doors can each room have. Please play around the variables to see how they affect the creation!

To run the application direct from the command line, with default settings, use:
```
mvn compile exec:java -Dexec.mainClass=dungeongenerator.domain.Main
```

### JAR file
You can also get and execute the [JAR file](url), though this way you can only create maps with the default parameters.

### End result
The latest map is automatically saved into /tiralabra/dungeongenerator/generated_map.txt, making reviewing it a little bit easier. Note that this file is overwritten every time a new map is created.

