package app.controllers;

import java.io.IOException;

import app.App;
import app.custom.CanvasPane;
import app.custom.brushes.Eraser;
import app.custom.brushes.Pen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController {
  public CanvasPane canvasPane;

  public MenuItem newFilebtn;
  public MenuItem saveBtn;
  public MenuItem penBtn;
  public MenuItem eraserBtn;

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

  @FXML
  private void showSaveDialog() {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(App.class.getResource("custom/save-warning.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 150);
        Stage stage = new Stage();
        stage.setTitle("Save");
        stage.setScene(scene);

        SaveController controller = fxmlLoader.getController();
        controller.initData(this.canvasPane);

        stage.show();
    } catch (IOException e) {
      System.out.printf("Err: %s", e.getMessage());
    }
  }

  @FXML
  private void saveCanvas() {
    this.canvasPane.save();
  }

  @FXML
  private void setBrush(ActionEvent e) {
    Object source = e.getSource();

    if (source == penBtn) {
      canvasPane.setCurrentInstrument(new Pen());
    }
    else if (source == eraserBtn) {
      canvasPane.setCurrentInstrument(new Eraser());
    }
  }
}
