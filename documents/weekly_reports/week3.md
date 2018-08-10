##  Third week

### What I got done
The map now gets filled with a maze generator that creates a 'perfect' maze, as in a maze that has no cycles. A lot of the code was refactored, and a brief user guide was also created to help with peer review. 

### How it shows
![The 'perfect' maze!](https://github.com/Granigan/dungeongenerator/blob/master/documents/images/mapcap3_week3.png)

### This week I learned
After fighting a while with a but that turned out to be a simple typo, I may be able to appreciate simple tests a bit more in the future. Also, I wasted a ton of time not wanting to do a 'tedious' step-by-step analysis of the problem - had I started with that I would've discovered the problem right away, instead of toiling away, building different implementations for the parts that were not broken in the first place.

So, when there's a bug with a cause that's not immediately obvious, it's best to dive deep right away to get to the bottom of the issue - not mess about hoping to stumble into it.


### Troubles I've seen
All of my source material had squares for corridors, and borders for squares as walls. Instead, I'm building both corridors and walls as squares, which caused some extra work. This may yet be problematic further down the line.

After refactoring some of the methods, the tests also blew up, so fixing them and building more caused a fair amount of work.

### Going forward
Next week's feature will be connecting the different segments (i.e. rooms and corridors) together, which'll effectively be a slightly modified spanning tree. Removing dead ends can be done after this, leaving the final map. I also hope to get started on the data structures (at least Random() and Stack() are needed.)

## Time spent this week
**Total: 22h**

| Day	| Time Spent	| Tasks	|
| ------|:-------------:|------:|
| Wed	| 2h		| Reading about maze generating algorithms |
| Thu	| 12h		| Support for segments, maze generator created, refactoring large methods  |
| Fri	| 8h		| Fixes to maze generator, javadocs, checkstyle fixes, tests, weekly report, user's guide|

