# Tricky-Five-in-a-Row

This project implements a variant of the classic Five-in-a-Row (Gomoku) game in Java, playable by two players on a customizable square board. The board size can be selected by the player at the start of the game, with options of 6×6, 10×10, or 14×14 grids. Players take turns to place their marks (X or O) on the board, which must be placed only on empty fields. The game ends either when the board is full (resulting in a draw) or when one player aligns five adjacent marks in a row, column, or diagonal—winning the game. A unique twist makes the game more challenging: if a player creates three adjacent marks, one of their own marks is randomly removed from the board; if they create four adjacent marks, two of their own marks are randomly removed. The program continuously displays which player's turn it is, and upon game end, a message box declares the winner or a draw, and the game automatically restarts.

Requirements:
Java JDK 8 or later
IDE or terminal to compile and run the program

How to Use:
Launch the application
Run the game class with your IDE or from terminal:
  javac *.java
  java GameLauncher
Select Board Size
When prompted or via GUI, choose between:
6 × 6
10 × 10
14 × 14

Gameplay
Players alternate turns, placing X or O.
You can only click on empty cells.
The game shows whose turn it is at all times.
If a player creates 3 or 4 adjacent marks:
3 adjacent: one of their marks is randomly removed.
4 adjacent: two of their marks are removed.
First to get 5 in a row, column, or diagonal wins.
End of Game

A message box announces the result (Win or Draw).
The board resets automatically, and a new game begins.
