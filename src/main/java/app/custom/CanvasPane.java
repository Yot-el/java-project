package app.custom;

import javafx.scene.layout.Pane;

public class CanvasPane extends Pane {
  private int brushSize;
  private int currentLayer = 0;

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

  public void setCurrentLayer(int id) {
    this.currentLayer = id;
  }

  public void draw() {
    
  }
}
