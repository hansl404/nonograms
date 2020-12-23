# Nonograms

# About
Copy of nonograms assignment. Consists of 5 built-in nonograms to solve. Rules can be found on: https://puzzlygame.com/pages/how_to_play_nonograms/

# How to play
Start with a blank grid of varying dimensions, along with numbers along the top and left sides of the grid. The numbers on the left indicate how many grid spaces will be filled in for the corresponding row while the numbers on the top indicate how many grid spaces will be filled in for the corresponding column. Multiple numbers indicate that there will be a sequence of rows/columns filled in, but those sequences cannot be together. A "-" in the place of a number simply means there are no additional sequences of rows/columns to be filled and can be ignored. The order the numbers are placed in does not matter (a 2 1 could mean that there could be either 1 grid space filled in and then 2 or vice versa). 

**Left click** on the grid space to fill it in. **Right click** on the grid space to X a space out. Left clicking a filled grid of right clicking an X'd out grid will reset the space back to blank. X's have no effect on the board and are simply there to help visually eliminate spaces to fill in.

Upon solving the nonogram with correct number and placement of filled grid spaces will display a "Correct!" message. X's or blanks have no effect on whether or not this message will be shown.

# Hints
- Start with the largest numbers. For example, in a 5x5 grid, if one of the numbers corresponding with the 1st row is a 3, the 1st row, 3rd column of the grid space will be filled no matter what.
- If a row or column has multiple numbers as hints and one of the sequences is filled out, there grid spaces adjacent to the filled in sequence will never be filled in.
