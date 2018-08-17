## Fourth week

### What I got done
The rooms are now connected to the corridor system with doors (+), and the deadend parts of corridors are sealed off. As a result, the map is pretty much what was originally intended to be created.

On the testing side, there was a major breakthrough as I finally figured out what [dependency injection testing](https://javaranch.com/journal/200709/dependency-injection-unit-testing.html) actually is. Following this, I was able to create tests for methods using random numbers, and for methods that mainly call other classes.

The work began on homemade structures and algoriths, starting with replacing the Random() with HomemadeRandom().

Finally, concepts and ideas for [Implementation document](https://github.com/Granigan/dungeongenerator/blob/master/documents/implementation.md) and [Testing plan](https://github.com/Granigan/dungeongenerator/blob/master/documents/testing_plan.md) were put together.

### How it shows
![Maze with a homemade randon generator](https://github.com/Granigan/dungeongenerator/blob/master/documents/images/mapcap1_week4.png)

*It's starting to look a lot like a randomly generated level!*

### This week I learned
Lots! The work on testing and reading about dependency injection was a real revelation. There were some struggles with the random generator (including some that could've been solved much faster by creating tests for it first!), but adding the door placement code was easier than expected - as some of the original features didn't come to play (at least yet.)

### Troubles I've seen
& is not the same as %. If I had realised this by myself, I would've saved an hour of two of debugging the HomemadeRandom(). Also, I struggled a lot working by myself. Once I started talking about the process and work I was doing with friends and fellow students, my motivation rose and progress was made much faster. Looking forward to OHTU-project where it'll be a lot more about working as group.

Also, the internet has a wonderful amount of nicely presented information available. Finding the right guide for myself isn't that easy though, and getting the right keywords for the search is critical.

### Going forward
Next week focus will be on building own data structures. I plan to go through each use of the existing ones (mostly hashmaps and arraylists) and see what would be the simplest structure that could fill the same role for that purpose. Alternatively, building a fully featured versions would mean they have a lot of redundant (to me) faetures, but would make the code easier to expand. **Any thoughts on this?**

Also, it'll be interesting to do the peer review! :)

## Time spent this week
**Total: 15h**

| Day	| Time Spent	| Tasks	|
| ------|:-------------:|------:|
| Thu	| 10h		| Adding door placement and corridor sealing, tests for new classes, first version of random generator, some refactoring. |
| Fri	| 5h		| Dependency injected testing and other tests, concepts for implementation and test documents, weekly report. |

