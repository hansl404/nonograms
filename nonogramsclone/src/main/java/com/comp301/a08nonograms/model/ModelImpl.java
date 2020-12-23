package com.comp301.a08nonograms.model;

import com.comp301.a08nonograms.controller.ControllerImpl;
import com.comp301.a08nonograms.view.ModelObserverImpl;
import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private List<Clues> games;
  private List<ModelObserver> observers;
  private int puzzleindex;
  private Board board;

  public ModelImpl(List<Clues> clues) {
    this.games = clues;
    this.puzzleindex = 0;
    this.board =
        new BoardImpl(clues.get(puzzleindex).getHeight(), clues.get(puzzleindex).getWidth());
    this.observers = new ArrayList<ModelObserver>();

    // create instance of modelobservers
    observers.add(new ModelObserverImpl(new ControllerImpl(this)));
  }

  // update method for observers in the list of observers
  private void notifyObservers() {
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public int getPuzzleCount() {
    return games.size();
  }

  @Override
  public int getPuzzleIndex() {
    return puzzleindex;
  }

  @Override
  public void setPuzzleIndex(int index) {
    if (index < 0) {
      index = 2 * this.getPuzzleCount() - 1;
    }
    this.puzzleindex = index % this.getPuzzleCount();
    this.board =
        new BoardImpl(games.get(puzzleindex).getHeight(), games.get(puzzleindex).getWidth());
    notifyObservers();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    this.observers.remove(observer);
  }

  @Override
  public boolean isSolved() {
    boolean victory = true;

    // count the number of boxes that should be shaded (row)
    int[] rshaded_ans = new int[games.get(puzzleindex).getHeight()];
    for (int i = 0; i < this.games.get(puzzleindex).getHeight(); i++) {
      for (int j = 0; j < this.games.get(puzzleindex).getRowCluesLength(); j++) {
        rshaded_ans[i] += this.games.get(puzzleindex).getRowClues(i)[j];
      }
    }

    // count number of boxes that should be shaded (col)
    int[] cshaded_ans = new int[games.get(puzzleindex).getWidth()];
    for (int i = 0; i < this.games.get(puzzleindex).getWidth(); i++) {
      for (int j = 0; j < this.games.get(puzzleindex).getColCluesLength(); j++) {
        cshaded_ans[i] += this.games.get(puzzleindex).getColClues(i)[j];
      }
    }

    // count the number of boxes for each row that are currently shaded
    int[] rshaded = new int[rshaded_ans.length];
    for (int i = 0; i < this.games.get(puzzleindex).getHeight(); i++) {
      for (int j = 0; j < this.games.get(puzzleindex).getWidth(); j++) {
        if (board.isShaded(i, j)) {
          rshaded[i] += 1;
        }
      }
    }

    // count number of boxes for each col that are currently shaded
    int[] cshaded = new int[cshaded_ans.length];
    for (int i = 0; i < this.games.get(puzzleindex).getWidth(); i++) {
      for (int j = 0; j < this.games.get(puzzleindex).getHeight(); j++) {
        if (board.isShaded(j, i)) {
          cshaded[i] += 1;
        }
      }
    }

    // check number of shaded for each row
    for (int i = 0; i < rshaded_ans.length; i++) {
      if (rshaded[i] != rshaded_ans[i]) {
        victory = false;
      }
    }

    // check number of shaded for each col
    for (int i = 0; i < cshaded_ans.length; i++) {
      if (cshaded[i] != cshaded_ans[i]) {
        victory = false;
      }
    }

    return victory;
  }

  @Override
  public boolean isShaded(int row, int col) {
    return board.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return board.isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    return board.isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    board.toggleCellShaded(row, col);
    notifyObservers();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    board.toggleCellEliminated(row, col);
    notifyObservers();
  }

  @Override
  public void clear() {
    board.clear();
    notifyObservers();
  }

  @Override
  public int getWidth() {
    return this.games.get(puzzleindex).getWidth();
  }

  @Override
  public int getHeight() {
    return this.games.get(puzzleindex).getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return this.games.get(puzzleindex).getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return this.games.get(puzzleindex).getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return this.games.get(puzzleindex).getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return this.games.get(puzzleindex).getColCluesLength();
  }
}
