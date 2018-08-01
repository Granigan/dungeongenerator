## Second week

### What I got done
The coding work began by creating methods for initialising the map based on parameters. After this, the more fascinating work on the methods to add rooms was done. Now the generator takes (hardcoded) parameters for the size of the map (in squares), the minimum and (randomised) maximum diameter of the room, and the amount of room placement attempts, and creates an ASCII map based on these. The rooms do not collide, but can exist inside other rooms. This is to be fixed as it is also required for pathfinding operations later on.

### How it shows
![It makes rooms that don't collide](https://github.com/Granigan/dungeongenerator/blob/master/documents/images/mapcap_week2.png)
It took a reasonable amount of time to just play around with the settings and see the different outcomes!


### This week I learned
As said, it was great fun to play with the algorithm as soon as it was running even at some level. Seeing the progress is a great way to stay interested in the project. I also got a reminder about how computers use coordinates and stopped trying to force the human way of Y going upwards as it grows.

Adding a simple printing to file was also nice little thing to add the program that'll hopefully come part of the normal skillset with few more repetations.


### Troubles I've seen
Codewise, a bug with drawing the walls for the room kept me wondering for a while, as it turns out coordinates can be a bit tricky. After some whiteboard debugging I realised I didn't start the count from zero and thus the walls were a bit leaky.

On a more comprehensive level, I struggle to get a grasp on testing logic, or paradigm. It always feels that either I build a test to test something tiny and obvious - irrelevant, or something vast, like the end result - which is essentially what happens every time the program is ran anyway. I'd really appreciate a recommendations on how to improve my understanding over testing, it feels that as a topic it's been slightly overlooked during the courses so far. Though of course, tests alone are not really the emphasis on this project, so this is just an 'optional side quest', if you will.


### Going forward
Much refactoring is needed as everything is currently in Main.java. I'll start by creating a class for the map itself, and move most, if not all methods there. After this, I can also add tests for the methods.

After that, I'll be adding segmentation for the rooms, which also should prevent them spawning inside other rooms. After that, it's time to create an algorithm for filling the empty space with a maze of corridors. 

## Time spent this week
**Total: 7h**

| Day	| Time Spent	| Tasks	|
| ------|:-------------:|------:|
| Wed	| 7h		| Creation of a blank map, support for adding rooms to it, learning about and planning testing, weekly report |


