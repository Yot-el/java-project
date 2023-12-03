package app.controllers;

import app.custom.CanvasPane;
import javafx.fxml.FXML;

public class HomeController {
  public CanvasPane canvasPane;

  public void initialize() {}

  @FXML
  private void startDraw() {
    System.err.println("start draw");
  };

  @FXML
  private void draw() {};

  @FXML
  private void endDraw() {};
}
