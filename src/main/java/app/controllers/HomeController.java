package app.controllers;

import app.custom.CanvasPane;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class HomeController {
  public CanvasPane canvasPane;

  public void initialize() {}

  @FXML
  private void startDraw(MouseEvent event) {
    double x = event.getX();
    double y = event.getY();
    canvasPane.startDraw(x, y);
  };

  @FXML
  private void draw(MouseEvent event) {
    double x = event.getX();
    double y = event.getY();
    canvasPane.draw(x, y);
  };

  @FXML
  private void endDraw(MouseEvent event) {
    double x = event.getX();
    double y = event.getY();
    canvasPane.endDraw(x, y);
  };
}
