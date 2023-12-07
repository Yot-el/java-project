package app.controllers;

import app.custom.CanvasPane;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SaveController {
  public Button yesBtn;
  public Button noBtn;

  private CanvasPane canvas;


  @FXML
  private void saveCanvas(MouseEvent e) {
    try {
      this.canvas.save();
    }
    catch (Exception exception) {
      return;
    }

    this.resetCanvas(e);
  }

  @FXML
  private void resetCanvas(MouseEvent e) {
    try {
      this.canvas.reset();
      Node  source = (Node)  e.getSource(); 
      Stage stage  = (Stage) source.getScene().getWindow();
      stage.close();
    }
    catch (Exception err) {
      System.out.println(err.getMessage());
    }
  }

  void initialize() {}
  void initData(CanvasPane canvas) {
    this.canvas = canvas;
  }
}
