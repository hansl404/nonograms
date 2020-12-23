package com.comp301.a08nonograms.model;

public class CluesImpl implements Clues {
  private int height;
  private int width;
  private int[][] rows;
  private int[][] cols;

  public CluesImpl(int[][] rowClues, int[][] colClues) {
    this.height = rowClues.length;
    this.width = colClues.length;
    this.rows = rowClues;
    this.cols = colClues;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int[] getRowClues(int index) {
    return rows[index];
  }

  @Override
  public int[] getColClues(int index) {
    return cols[index];
  }

  @Override
  public int getRowCluesLength() {
    return rows[0].length;
  }

  @Override
  public int getColCluesLength() {
    return cols[0].length;
  }
}
