Clases:
Main - creates window and manages it

StartingWindow - creates starting window and manages the start of simulation

GamePanel - manages all actions inside the window

Entity - parent class to all characters in the simulation

Player - test class of a player to check if the simulation works properly

Tile - class thats biulding block of an array of tiles

TileManager - manages the tiles images and is responsible for loading the map correctly

ParkingLights - like Tile is a building block for an array of light tiles

Stopwatch - manages timer window

Music - manages music

CarsParkedCouter - manages counter of parked cars

BackgroundCarsParked - creates window for CarsParkedCounter to display its values

Abstract:
Drawable - manages drawing think onto the main pane of the simulation

Interfaces:
Collisions - checks collisions

Draw_tile - draws light tile

ParkingCheck - checks if parking spot is taken

StartListener - waits and checks for start in the starting window
