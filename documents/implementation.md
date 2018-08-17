## Implementation Details (WIP)

As planned, the application is able to create 2d ASCII maps in given sizes. The structure follows the [specifications](https://github.com/Granigan/dungeongenerator/blob/master/documents/specifications.md) pretty closely, though some small differences exist. For example, room walls cannot overlap (currently, @week 4, subject to change), which means that rooms will not have direct access to each other, but only to corridors.

### Algorithms
All the algorithms were created organically during the process. Pseudocode and O-analyses to be added.

### Data Structures
HomemadeRandom() is a [linear congruential generator](https://en.wikipedia.org/wiki/Linear_congruential_generator),  based on the ["Microsoft code"](https://github.com/Granigan/dungeongenerator/blob/master/documents/specifications.md).

Limited, or specialised versions of ArrayList and HashMap are yet to be created, possibly several versions to fit specific needs in code.

### V2.0
The most immediate improvement would be the allow room walls to overlap, and thus make doors between rooms possible. After this, connecting everything becomes a bit more complicated as well, but should produce more interesting maps. A simpler addition is to allow multiple doors per room, as this should create some cycles into the maze.

Saving the map on disk is not particularily relevant for this course, but improving this by allowing the user to name the map, or at least always give the map an unique name would be a nice touch.

The code is not optimised in the least, and there's probably a lot of work that could be done to improve the efficiency.

### Information Sources
[Random generator: Linear congruential generator with "Microsoft code"](https://github.com/Granigan/dungeongenerator/blob/master/documents/specifications.md)
