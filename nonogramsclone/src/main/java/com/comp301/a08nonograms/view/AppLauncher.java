package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.PuzzleLibrary;
import com.comp301.a08nonograms.controller.Controller;
import com.comp301.a08nonograms.controller.ControllerImpl;
import com.comp301.a08nonograms.model.Clues;
import com.comp301.a08nonograms.model.Model;
import com.comp301.a08nonograms.model.ModelImpl;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Launch your GUI here

    List<Clues> c = new PuzzleLibrary().create();
    Model m = new ModelImpl(c);
    Controller controller = new ControllerImpl(m);
    View view = new View(controller);

    Scene s = new Scene(view.render());
    stage.setScene(s);

    // Refresh view when model changes
    m.addObserver(
        (Model model) -> {
          stage.setScene(new Scene(view.render()));
        });

    stage.show();
    stage.setTitle("Nonograms");
  }
}
