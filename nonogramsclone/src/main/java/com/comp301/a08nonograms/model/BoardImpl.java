package com.comp301.a08nonograms.model;

public class BoardImpl implements Board {

  private int[][] cells;
  private int width;
  private int height;
  // 0 for blank, 1 for shaded, 2 for X

  public BoardImpl(int num_rows, int num_cols) {
    // num_rows should be height
    // num_cols should be is width
    this.width = num_cols;
    this.height = num_rows;
    this.cells = new int[height][width];
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (cells[row][col] == 1) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (cells[row][col] == 2) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (cells[row][col] == 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (cells[row][col] == 1) {
      cells[row][col] = 0;
    } else {
      cells[row][col] = 1;
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (cells[row][col] == 2) {
      cells[row][col] = 0;
    } else {
      cells[row][col] = 2;
    }
  }

  @Override
  public void clear() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        cells[i][j] = 0;
      }
    }
  }
}
