# Requirements and Specifications for the Project

## Purpose of the Application
The application creates an ASCII map based on the user input. Goal is to create procedurally generated maps for e.g. dungeon crawler so that the game offers a unique experience each time it is played. This would not be possible with handcrafted maps, but becomes a core gameplay element with a well-tuned map generator.

## User Input
The user can use default values, or define the size of the map (height*width), and the wanted amount of rooms (low/normal/high), and the application does the rest. More options may be available for debugging, testing, and performance measuring use.

## Application Logic
The application uses a multi-staged approach to create rooms and corridors, described by Bob Nystrom at [Rooms and Mazes: A Procedural Dungeon Generator](http://journal.stuffwithstuff.com/2014/12/21/rooms-and-mazes/). The process has four stages:
1. Create rooms at random, but non-overlapping locations
2. Fill the empty space with corridors created by a randomised [Flood fill](https://en.wikipedia.org/wiki/Flood_fill) algorithm
3. Starting from a random room, add doors that connect every room and corridor segment to each other, allowing travel from any square to any other square
4. Remove deadends

[The source code](https://github.com/munificent/hauberk/blob/db360d9efa714efb6d937c31953ef849c7394a39/lib/src/content/dungeon.dart) for the method is  available at GitHub. The original is created with Dart, this project uses Java instead.

## Required Algorithms and Data Structures
**This is the initial estimate only, and will be updated as the project progresses.**

At least these are required:
- Spanning Tree
- Random number generator
- Stack
- modified Flood Fill
- Kruskal's algorithm

## Time and Space Requirements
**This is the initial estimate only, and will be updated as the project progresses.**

### First stage: Rooms
Time requirement is defined by the wanted amount of rooms. The higher the value, the more attempts are made to place a new room into the map. If the room overlaps an existing room, it won't be placed, but instead a new attempt is made. Once the defined amount of attempts are made, the process finishes. The attempts require constant time, and have no notable space requirements.

Time: O(1)
Space: O(1)

### Second stage: Corridors
The flood fill algorithm is quite time consuming. It also requires an equal amount of space as the map itself. Adding randomisation to this may further slow down the algorithm.

n = amount of squares
Time: O(n²)
Space: O(n)

### Third stage: Connectors
All the squares are checked to find squares that are adjacent to different segments. Starting with a random connector, what is essentially a spanning tree, is built to connect everything together. However, some cycles improve the look and playability, which is done by leaving a chance for there to be more than one connection. This should be doable with e.g. Kruskal's algorithm.

m = amount of segments (= number of rooms and corridor segments)
n = amount of squares
Time: O(n log m)
Space: O(n + m)

### Fourth stage: Pruning
All squares that are next to three wall squares, i.e. are dead ends, are replaced with walls. As this process to check every square is repeated until no such dead ends are left, the process can be extremely slow. However, it is likely that the process can be optimised greatly by adding a check for neighbouring squares after having changed the one to a wall.

n = amount of squares
Time: O(n²)
Space: O(1)

### In total:
m = amount of segments (= number of rooms and corridor segments)

n = amount of squares

Time: O(n²)

Space: O(n+m)

## Other Generic and Related Knowledge Sources
[Map Generation algorithms and tools at Procedural Content Generation Wiki](http://pcg.wikidot.com/pcg-algorithm:map-generation)

[Random Dungeon Generator for caverns and bunkers](http://donjon.bin.sh/code/dungeon/)

[Cellular Automata Method](http://roguebasin.roguelikedevelopment.org/index.php?title=Cellular_Automata_Method_for_Generating_Random_Cave-Like_Levels)
