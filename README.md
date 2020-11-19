# CMGE Readme

**Project Name:** Cellular Model of Genetic Expression and Majority (CMGE)  
**Author:** Mars Paton  
**A very special thanks to *Mark Goadrich***  
**Date:** 11/18/2020  
**Version:** 1.0  

## Description
This project is my final project for my Data Structures. It is based off a tutorial by **Professor Mark Goadrich** (who is an awesome teacher). His original tutorial was for a model of disease spreading with three types of people (never infected, infected, and recovered). I took his tutorial and made it into an expression of mutation and how a type may gain majority even when factors are balanced.  

In my project, there are seven types of cells: red, orange, yellow, green, blue, purple, and mutated. A simulation starts out with red, yellow, and blue cells. The simulation will slowly grow, cells will mate, die, and mutate.  

## Features
### Controls
You are able to reset, stop, start, and move forward a single frame. To allow you changes to the settings to take effect, you have to type them in and then click reset. This generates a new Simulation object with the new settings.
### Initial Population
Allows you to modify the starting amount of cells. Due to the set size of the world, I recommend starting values between ten and one hundred. As slow as it may be, with an initial population of ten you can reach 500 with no problems as there is currently no mechanic of age.
### Refractory Period
This determines how fast cells can mate. A value of "1" would mean it would take roughly one second before a cell can mate and half a second before they can start killing each other. I say roughly because I used rough estimates and manual counting to determine how big the constant would need to be to get one second. Five is a balanced amount and prevents the population from getting too far over the max population.
### Mutation Chance
This determines both the chance for a cell to randomly mutate and change cell type, and the chance of an allele to mutate and possibly make a new cell be born mutated or save it from mutation. The way it works is it takes the number you give, turn it into a percentage (divide by 100) which it uses for allele mutation, and it takes that and divides it by 10,000 for the chance of cell spontaneously mutating into another type of cell.
### Maximum Population
Sets the maximum amount of cells in a simulation. Currently, due to how it is programmed, it will go over the maximum if the cells mate too fast, but they will not mate any more once that step is over. I plan to add a feature where cells will slowly die once it exceeds the maximum. I recommend not setting this any higher than seven hundred as it will begin to get massive slow downs.
### Cell count and histogram
These keeps track of the total amount of cells and the percentage of each type of cell. As far as I know, it will not run over into other GUI elements as I have not tested it (and it very well may be impossible to naturally achieve one cell type even for just a single frame).

## Planned Features
- Add the option for age so that cells can die of old age
- Fix the issue of the population exceeding the maximum and/or make them start to die once it does
- Allow the user to easily click and create/destroy cells (sandbox elements)