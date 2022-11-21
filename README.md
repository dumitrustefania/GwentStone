# GWENT STONE GAME

322CA - Bianca Ștefania Dumitru \
Object Oriented Programming Course

November 2022
----------------------------------------------------------------------------------------------------

* Gwent Stone
  -  the program is an implementation of a game created by combining the Gwent
  and HearttStone gameplay
  -  2 players are competing against each other by using their cards'
  various abilities

__Classes__

`fileio`

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- CardInput -> contains fields that define a card, like mana, health, name, attack damage, etc.
- Coordinates -> contains x & y coordinates (for specifying a certain card on the table)
- ActionsInput -> contains fields that define a required action, like the command name, player index, etc
- DecksInput -> contains fields that define a player's decks
- StartGameInput -> contains fields that define the characteristics of a match, like a player's hero or deck
- GameInput -> contains a StartGameInput field and an array of ActionsInput
- Input -> main input class, used to extract the input data from every JSON file
        -> contains a couple of DecksInput deck sets and a GameInput field
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
`main`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Main -> the main program where the test files are opened the output tests are created and then
every test is checked by the checker
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`game`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Game -> used as the starting point of the game
       -> initialises the 2 players by assigning them their decks
       -> creates the matches and starts them one by one 
                
- Match -> used as the starting point of a match
        -> starts the match by assigning the decks for this match and shuffling them
        -> plays the game turn by turn, following the actions given as input
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
`players`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Player -> contains all the necessary data about a player
         -> performs certain player specific methods, like adding a card in hand or reinitialising
            the player at the start of a match
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`table`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Table -> the actual table where the cards are put
        -> contains helper methods for accessing and modifying the cards on the table
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`actions`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Action -> class used to create 5 different types of actions, based on the command name
         -> its children classes override the performAction() method
         
- DebugAction -> performs one of the debug actions, based on the command name and extra input

- EnvironmentAction -> creates an object depending on the environment card's name and
                       proceeds to use its ability
             
- HeroActions -> creates an object depending on the hero card's name and
                 proceeds to use its ability

- MinionAction -> performs one of the minion actions, based on the command name and extra input
               -> the Minion card can can:
                      --> attack an enemy card
                      --> attack the opponent's hero
                      --> create an object depending on the minion card's name and use its ability
                      
- PlayerAction -> perform the only player action: placing a card on the table                      
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`abilities`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Ability -> an interface to be used for defining card abilities
      
      - EnvironmentAbility -> abstract class for defining an environment card ability
                           -> classes that extend it and implement the specific ability:
                                  --> Firestorm
                                  --> HeartHound
                                  --> Winterfell
      - HeroAbility -> abstract class for defining a hero ability
                           -> classes that extend it and implement the specific ability:
                                  --> EmpressThorina
                                  --> GeneralKocioraw
                                  --> KingMudface
                                  --> LordRoyce
                                  
      - MinionAbility -> abstract class for defining a minion card ability
                           -> classes that extend it and implement the specific ability:
                                  --> Cursed
                                  --> Disciple
                                  --> Miraj
                                  --> Ripper                            
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`util`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Constants -> storing constants used in the implementation

- JsonOut -> used for easily appending the necessary JSON values to the ArrayNode output
          -> all its fields have the same name as the one required in the JSON output files
          -> some integer fields have their default value -1 because they should not be displayed
          in case they are not required in the output. To do this, a special Json tag is included
          near each field

- MyIntFilter -> used for the Json tag mentioned above
              -> filters out every integer value with the default value -1      
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
----------------------------------------------------
How does it work?

* The Main class is the entry point of the program. It creates an Input instance using the 
input JSON file. It also creates a Game instance, that calls the playGame() method with the
ArrayNode output parameter. This starts the actual game.

* Certain initialisations are performed in the playGame() method of the Game object. It then creates
a Match instance for each match that has to be played, calling its startGame() method.

* Some more match-specific initialisations are also performed in the startGame() method of the 
Match object. The match then takes action, with the playRounds() method call.

* For each player's turn, commands from the input are performed until reaching the 'endPlayerTurn'
command. The other player continues with his commands after that.

* Each command is performed with the help of the Action class. It is the first to process each
action and redirect it to the corresponding class based on its command name, by creating an
instance of the respective class (DebugAction / HeroAction / MinionAction etc.) and calling the
performAction() method.

* Each subclass of actions checks the command name and performs it accordingly. Some comands, though,
are different based on the card type that has to execute it (the environment, hero and minion abilities).
For performing these special abilities, the corresponding ability class is instantiated, based on the 
card name, and the useAbility() method is called to perform the ability.

* For each command that requires an output, a JsonOut object is instantiated and the information
about the executed command is added to it. It then gets appended to the overall game output.