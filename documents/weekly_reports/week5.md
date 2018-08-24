## Fifth week

### What I got done
This week's feature is a chance for there being multiple doors per room, controlled by two new variables (max doors per room, and chance for extra doors). This was the final feature to be added, though it'll need some further checks and fixes to prevent extreme settings from crashing the map creation.

Tech side, the focus was on replacing ArrayList structure out of the code, and this was achieved. Alongside the homemade data structure, code was refactored a fair bit to avoid using ArrayLists where possible. Following this, some obsolete code (like directions enums) was also scrapped.

Test coverage keeps rising, this time focus being on some of the missed branches in methods with more checks than results.

Finally, peer review was great, though potentially time consuming task. I believe I had some good feedback, and was superbly satisfied on the peer review I got myself - it was full of good feedback, and more of it will be implemented next week.

### How it shows
![Multiple doors!](https://github.com/Granigan/dungeongenerator/blob/master/documents/images/mapcap1_week5.png)

*Some rooms now have multiple doors, creating cycles in the dungeon!*

### This week I learned
Crafting the minimum needs ArrayList replacement was both fun, and interesting, as it become clear that I only needed a very limited set of its functionality, meaning I might be able to use custom structures a lot more in the future as well, in other projects. Still, best to verify the custom structures are actually better performance wise...

The peer review was fantastic, e.g. with its reminder to look into the bit operations with the random generator. 

### Troubles I've seen
Filling the gaps in the tests coverage can get very tedious - it seems to be well worth the effort to write tests along with new code to avoid having to start the from the scratch later on. Due to time limits - and because some of the code is still changing - I wasn't able to start work on the pseudocode examples and O analyses.

### Going forward
Next week, HashMap will get whacked, and a crawler created. Then some O analysis and and hopefully a command line parameter parser. It'll be busy, but that should about do it!

## Time spent this week
**Total: 12h**

| Day	| Time Spent	| Tasks	|
| ------|:-------------:|------:|
| Thu	| 5h		| Peer review, replacing ArrayList, support for multiple doors |
| Fri	| 7h		| Replacing ArrayList, refactoring, tests, weekly report |

