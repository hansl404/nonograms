package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import com.comp301.a08nonograms.model.Model;
import com.comp301.a08nonograms.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModelObserverImpl implements ModelObserver {
  private Controller controller;
  private View view;
  private Stage stage;
  // private Model model;

  public ModelObserverImpl(Controller controller) {
    this.controller = controller;
    this.view = new View(controller);
    // this.stage =
  }

  @Override
  public void update(Model model) {
    //    model.clear();
    //    Scene s = new Scene(view.render());
    //    this.stage.setScene(s);
  }
}
