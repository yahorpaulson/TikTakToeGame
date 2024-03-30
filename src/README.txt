++++++++++++++++++++++++++++++HOW TO USE THE GAME+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

The game is presented by 4 classes:
    -Server.java
    -ClientFirst.java
    -ClientSecond.java
    -Game.java


++++++++++++++++++++++++++++++CONNECTION AND STARTING THE GAME++++++++++++++++++++++++++++++++++++++++++++++++++++

To start the game firstly all classes must be compiled to class files.
It can be achieved either through the cmd line using command:
    javac <name_of_class>.java
    java <name_of_class> (for running the code)
or using an IDE such as Intellij IDEA, which will create it automatically after running the code

The first class to be run is Server.class, which runs the server. After launching in command line will
appear the message: "Waiting for players...". It means that server is waiting while two clients will
connenct to this server.

The next two classes to be launched are accordingly ClientFirst.class and ClientSecond.class .
When client will be connected server sends a message. The first connected player moves with X:
    Player X connected.
    Player O connected.

After all the two players are connected for both of them appears a board with empty slots and for the first
player, which moves with X appears the message to make a move:
    You are Player O            You are Player X
    Board:                      Board:
    - - -                       - - -
    - - -                       - - -
    - - -                       - - -
                                Your turn:
+++++++++++++++++++++++++++++++++++++++++INSTRUCTIONS TO THE GAME++++++++++++++++++++++++++++++++++++++++++++++++++++++

The board slots are assigned with the numbers from 0 to 8 from the left upper corner. To make a move
player has to set a number of the slot accordingly and press Enter. For instance choosing number 6 player marks the
lower left corner slot:
    Your turn:
    6
    Move successful
    Board:
    - - -
    - - -
    X - -
Each player makes move one by one until winning the game. Player cannot make move in that slot, which is already
set by another player.

After winning the game the board resets to empty slots board and starts another game.