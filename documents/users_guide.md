## User's Guide for Dungeon Generator

### Current implementation (week 3)
The application creates and saves an ASCII map with variable amount of rooms, and a 'perfect' (as in non-cyclic) maze to fill out the rest of the map. The corridors of the maze are not yet connected to the rooms.

### How to use
The project can be run the easiest with e.g. NetBeans, via the Main.java. This is also recommended as the Main.java holds the parameters for choosing the size of the map (width/height), minimum size of the rooms to be added, and a maximum number that can be added to the size of the rooms. Finally, the number of **attempts** to place a room is also relevant, as that directly affects how densily filled with rooms the map will be. Please play around the variables to see how they affect the creation!

The latest map is also automatically saved into tiralabra/dungeongenerator/generated_map.txt, making reviewing it a little bit easier.


### Feedback wanted!
Please give your ideas on how to improve! I especially struggle with testing, so any thoughts on that department will be greatly appreciated. :)
