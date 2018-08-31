## Sixth week

### What I got done
Sadly, not much. This week other schedules piled on heavily, and though the peer review got completed, not much progress was made with the dungeon generator itself. 

Intention was to refactor imported HashMap out of the code, but this turned out to be a bit trickier than expected. First, I considered building a limited version of hashmap, with a linked list included, but then I realised I could refactor the code to only use a list instead of a hasmap. But I wasn't exactly right about that either; next realisation was that a hashmap would be considerably more efficient. Going through all this thought process took most of the time I had available this week, and thusly there's very little in the way of code changes.

To tidy up imports, I did wrap saving to a file functionality to a separate, optional class. Without it, the app produces the results as output alone.

### How it shows
Some refactoring and lots of new, unfinished classes that lack documentation, functionality, and reason.

### This week I learned
I need to allot a lot (eheh) more time for figuring out how to refactor out the hashmap use. 

### Troubles I've seen
As I only had the chance to work with the project towards the very end of the week, I hadn't even thought what I was actually going to do once I got the chance. This meant practically all the time was spent thinking, panicking about not coding, and not coding. Hopefully I can catch up next week and get close to ready by the final turn in.

### Going forward
These are the main things to finish by the final build:
- Refactor out hashmap
- Analyse the algorithms
- Write a test crawler to scour the ready map

If there's a chance, I'd also like to add:
- Command line parameters
- Test coverage close to 100%

## Time spent this week
**Total: 8h**

| Day	| Time Spent	| Tasks	|
| ------|:-------------:|------:|
| Thu	| 2h		| Peer review |
| Fri	| 6h		| Attempts to refactor out the hashmap, refactoring, making file save optional, weekly report |

