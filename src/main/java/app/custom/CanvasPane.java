package app.custom;

import javafx.scene.layout.Pane;

public class CanvasPane extends Pane {
  public CanvasPane() {
    super();

    this.setWidth(500);
    this.setHeight(500);

    this.addLayer();
  }

  public void addLayer() {
    Layer newLayer = new Layer(this.getWidth(), this.getHeight());

    this.getChildren().add(newLayer);
  }
}
