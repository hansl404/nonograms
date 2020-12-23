package com.comp301.a08nonograms.controller;

import com.comp301.a08nonograms.model.Clues;
import com.comp301.a08nonograms.model.Model;
import java.util.Random;

public class ControllerImpl implements Controller {

  private Model m;

  public ControllerImpl(Model model) {
    this.m = model;
  }

  @Override
  public Clues getClues() {
    return this.m;
  }

  @Override
  public boolean isSolved() {
    return m.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return m.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return m.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    m.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    m.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    m.setPuzzleIndex(m.getPuzzleIndex() + 1);
  }

  @Override
  public void prevPuzzle() {
    m.setPuzzleIndex(m.getPuzzleIndex() - 1);
  }

  @Override
  public void randPuzzle() {
    Random rand = new Random();
    int i = rand.nextInt(m.getPuzzleCount() - 1);
    m.setPuzzleIndex(i);
  }

  @Override
  public void clearBoard() {
    m.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return m.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return m.getPuzzleCount();
  }
}
