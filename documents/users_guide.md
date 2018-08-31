## User's Guide for Dungeon Generator

### Current implementation (week 5)
The application creates and saves an ASCII map with variable amount of rooms, and a connecting corridor . The rooms are connected to the corridor with a door (+).

### How to use
The project can be run the easiest with e.g. NetBeans, via the Main.java. This is also recommended as the Main.java holds the parameters for choosing the size of the map (width/height), minimum size of the rooms to be added, and a maximum number that can be added to the size of the rooms. The number of **attempts** to place a room is also relevant, as that directly affects how densily filled with rooms the map will be. As the last feature, two new variables were added: maxDoorsPerRoom and MultipleDoorOdds. As the names imply, these affect how many doors can each room have. Please play around the variables to see how they affect the creation!

The latest map is also automatically saved into tiralabra/dungeongenerator/generated_map.txt, making reviewing it a little bit easier.

To run the application direct from the command line, with default settings, use:
```
mvn compile exec:java -Dexec.mainClass=dungeongenerator.domain.Main
```

### Feedback wanted!
Please give your ideas on how to improve! Good luck on your own project! :)
