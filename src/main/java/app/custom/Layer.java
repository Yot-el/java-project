package app.custom;

import javafx.scene.canvas.Canvas;

public class Layer extends Canvas {
  private int id;

  public Layer(double w, double h) {
    super(w, h);
  }

  public int getLayerId() {
    return this.id;
  }

  public void setLayerId(int id) {
    this.id = id;
  }
}
