# Debugging For Pokemon Battle Simulator For Johns Hopkins Debugging Course
##  See Project Proposal Document
##  Mason Cole, Keturah Jones, Matt Chodaczek


**Pokémon Battle Simulator Debugging Proposal**

Repository: [https://github.com/Luiserebii/Pokemon-Battle-Simulator](https://github.com/Luiserebii/Pokemon-Battle-Simulator)

Members: Mason Cole, Keturah Jones, Matthew Chodaczek

The source code in this simulator is used in other more advanced Pokémon simulators. We plan to conduct mock testing, blackbox, and whitebox testing. We plan on using tools such as JUnit and Mockito for testing as well as other tools such as JaCoCo for testing feedback. If we are unable to mock the client-server communication, we plan to do some other form of testing such as mutation testing. Our members will split work depending on what type of testing. If one type of testing requires less work, then that member will assist with other forms of testing as needed.

While the SUT doesn&#39;t come with direct specifications or an API, the rules it uses are encoded into the mainline Pokémon games which we can use to facilitate blackbox testing.

Basic metrics:

- 1857 lines of Java.
- Ten classes corresponding to teams, moves, Pokémon, battles, etc., including client-server code.
- Many methods per class, from setters and getters to battle logic.

Language: Java

| 4/6 | Divide Classes Between Members For Unit Testing |
| --- | --- |
| 4/13 | Finish Classes That Don&#39;t Require Mock Testing |
| 4/20 | Finish All Testing |
| 4/27 | Finish Presentations |
| 4/29 | Finish Entire Project 😊 |



# Pokemon Battle Simulator
A Pokemon Battle engine coded in Java. Has local and multiplayer options (multiplayer not perfect), only runs from command line.

<br>
<div align="center"><img src="http://i.imgur.com/C9VgeW7.png"/></div><br>
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


