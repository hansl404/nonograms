package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import com.comp301.a08nonograms.model.Clues;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class View implements FXComponent {

  private Controller controller;
  private Clues clues;

  public View(Controller controller) {
    this.controller = controller;
    this.clues = controller.getClues();
  }

  @Override
  public Parent render() {

    // pick the current puzzle
    int currentindex = controller.getPuzzleIndex();
    // Clues current = clues.get(currentindex);
    Clues current = this.clues;

    // VBox stores overall view
    VBox overall = new VBox();

    VBox header = new VBox();
    Label title = new Label("Nonograms!");
    title.setFont(Font.font("Courier New", 20));

    int displayedindex = currentindex + 1;
    String di = "" + displayedindex;
    Label displayindex = new Label("Puzzle " + di + " of 5");

    Button clear = new Button("Clear");
    clear.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            controller.clearBoard();
          }
        });
    header.getChildren().addAll(title, displayindex, clear);
    header.setAlignment(Pos.CENTER);

    // main part of VBox is the gridpane for the board
    GridPane grid = new GridPane();

    // Create strings to represent clues in the Label
    String rcc = "";
    String ccc = "";

    // show the clues for each row
    for (int h1 = current.getColCluesLength();
        h1 < current.getHeight() + current.getColCluesLength();
        h1++) {
      for (int h2 = 0; h2 < current.getRowCluesLength(); h2++) {
        String isf = " ";
        if (current.getRowClues(h1 - current.getColCluesLength())[h2] == 0) {
          isf = "-";
        } else {
          isf = "" + current.getRowClues(h1 - current.getColCluesLength())[h2];
        }
        rcc += " " + isf;
      }
      Label rowclue = new Label(rcc);
      rowclue.setStyle("-fx-border-color: black;");
      grid.add(rowclue, 0, h1);
      rowclue.setFont(Font.font("Courier New", 22));
      rcc = "";
    }

    // show the clues for each column
    for (int w1 = 1; w1 < current.getWidth() + 1; w1++) {
      for (int w2 = 0; w2 < current.getColCluesLength(); w2++) {
        String isn = " ";
        if (current.getColClues(w1 - 1)[w2] == 0) {
          isn = "-";
        } else {
          isn = "" + current.getColClues(w1 - 1)[w2];
        }
        ccc += isn;
        Label colclue = new Label(ccc);
        colclue.setFont(Font.font("Courier New", 22));
        colclue.setStyle("-fx-border-color: black;");
        grid.add(colclue, w1, w2);
        ccc = "";
      }
    }

    // create a board of buttons
    for (int i = 1; i < current.getWidth() + 1; i++) {
      for (int j = current.getColCluesLength();
          j < current.getHeight() + current.getColCluesLength();
          j++) {
        Button button = new Button();
        button.setPrefSize(50, 50);

        grid.add(button, i, j);

        int col = i - 1;
        int row = j - current.getColCluesLength();
        if (controller.isShaded(row, col)) {
          button.setStyle("-fx-background-color: black;");
        } else if (controller.isEliminated(row, col)) {
          button.setStyle("-fx-background-color: white;");
          button.setText("\u274C");
        }

        // add event handlers
        button.setOnMouseClicked(
            (MouseEvent event) -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                // if already filled and clicked again, it returns to white color
                if (controller.isShaded(row, col)) {
                  button.setStyle("-fx-background-color: white;");
                  controller.toggleShaded(row, col);
                } else {
                  button.setText("");
                  button.setStyle("-fx-background-color: black;");
                  controller.toggleShaded(row, col);
                }
              } else if (event.getButton() == MouseButton.SECONDARY) {
                if (controller.isEliminated(row, col)) {
                  button.setText("");
                  controller.toggleEliminated(row, col);
                  // System.out.println("is eliminated");
                } else {
                  button.setText("\u274C");
                  button.setStyle("-fx-background-color : white;");
                  controller.toggleEliminated(row, col);
                  // System.out.println("else");
                }
                //                controller.toggleEliminated(row,col);
                //                button.setStyle("-fx-background-color: red;");
              }
            });
      }
    }

    grid.setVgap(4);
    grid.setHgap(4);
    grid.setAlignment(Pos.CENTER);
    grid.setPadding(new Insets(20, 20, 20, 20)); // padding?

    // the last part of the overall VBox
    HBox hbox = new HBox();

    Button random = new Button("Get random puzzle");
    random.setOnAction(
        e -> {
          controller.randPuzzle();
        });

    Button prev = new Button("\u2190");
    prev.setOnAction(
        e -> {
          controller.prevPuzzle();
        });

    Button next = new Button("\u2192");
    next.setOnAction(
        e -> {
          controller.nextPuzzle();
        });

    hbox.getChildren().addAll(prev, random, next);
    hbox.setAlignment(Pos.CENTER);

    // Create new VBox that will show up if model isSolved is true
    VBox winscreen = new VBox();
    Label winmsg = new Label("Correct!");
    winmsg.setFont(Font.font("Courier New", 20));
    winscreen.getChildren().addAll(winmsg);
    winscreen.setAlignment(Pos.CENTER);

    // Assemble parts according to win or not win yet
    if (controller.isSolved()) {
      overall.getChildren().addAll(header, winscreen, grid, hbox);
    } else {
      overall.getChildren().addAll(header, grid, hbox);
    }

    return overall;
  }
}
