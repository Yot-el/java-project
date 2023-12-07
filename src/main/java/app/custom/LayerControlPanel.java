package app.custom;

import java.io.IOException;

import app.App;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class LayerControlPanel extends FlowPane {
  public Label layerName;
  private CanvasPane canvas;
  private int id;

  public LayerControlPanel(CanvasPane canvas, int id) {
    this.canvas = canvas;
    this.id = id;
    this.setPrefWrapLength(170);

    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(App.class.getResource("custom/layer-panel.fxml"));

    try {
      fxmlLoader.setController(this); 
      fxmlLoader.setRoot(this);
      Object loaded = fxmlLoader.load();
      Object root=fxmlLoader.getRoot();

      String name = "Layer "+id;
      this.layerName.setText(name);
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  public void deleteLayer() {
    try {
      this.canvas.deleteLayer(id);
      VBox hbox = (VBox) this.getParent();
      hbox.getChildren().remove(this);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void setCurrentLayer() {
    this.canvas.setCurrentLayer(this.id);
  }

  public void setLayerOpacity(ObservableValue<Number> ovn, Number before, Number after) {
    double opacity = Math.round(after.doubleValue() * 100.0) / 100.0;

    this.canvas.setLayerOpacity(this.id, opacity);
  }
}
