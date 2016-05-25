# Pokemon Battle Simulator
A Pokemon Battle engine coded in Java. Has local and multiplayer options (multiplayer not perfect), only runs from command line.

<br>
![alt tag](http://i.imgur.com/C9VgeW7.png)<br>
Image of the simulator from the Ubuntu 16.04 terminal
<br>
<br>
## Features

### Local:
Simulator can be run locally against the computer. Opponent chooses moves randomly, with a provided team loaded from a .txt file.

### Multiplayer:
Multiplayer only works from LAN, as peer-to-peer; one player acts as the client, another as the server. Currently, fails at transferring team across sockets.

### Team Loading from Text: 
Loads Pokemon team from .txt files. Uses the traditional formatting Pokemon Online and Pokemon Showdown uses. Allows for custom Pokemon team options, can create a team in Showdown, save to .txt, and place in appropriate directory.

## Future Implementation
* Get multiplayer to work
* Add online functionality to LAN multiplayer
* Refactor code (not always very clean)
* Merge with Pokemon Emerald Bootleg to form working engine

-----------------------------------------------------------------------------------------

## Appendix

### A. Pokemon Showdown - The definitive Pokemon simulator. Uses the Pokemon and moves JSON from this project to load teams. https://github.com/Zarel/Pokemon-Showdown

### B. JSON.simple - JSON toolkit used for parsing in the project. Hosted at: https://code.google.com/archive/p/json-simple/downloads


