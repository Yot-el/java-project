package app.custom;

import app.custom.brushes.Instrument;
import app.custom.brushes.Pen;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class CanvasPane extends Pane {
  private int currentSize = 1;
  private int currentLayer = 0;
  private Paint currentColor = Color.web("rgb(0, 0, 0)");
  private Instrument currentInstrument;
  private int layerIdsGenerator = 0;

  public CanvasPane() {
    super();

    this.setWidth(500);
    this.setHeight(500);

    int id = this.addLayer();
    this.setCurrentLayer(id);
    this.getLayer(id);
    this.setCurrentInstrument("Pen");
  }

  public void setSize(int size) {
    this.currentSize = size;
  }

  public void setColor(String color) {
    this.currentColor = Color.web(color);
  }

  public void setPattern(String pattern) {
    Image i = new Image(getClass().getClassLoader().getResource(pattern).toString());
    this.currentColor = new ImagePattern(i, 15, 15, 30, 30, false);
  };

  // Returns new layer id
  public int addLayer() {
    Layer newLayer = new Layer(this.getWidth(), this.getHeight());
    newLayer.setLayerId(this.layerIdsGenerator);
    this.layerIdsGenerator += 1;
    this.getChildren().add(newLayer);

    return newLayer.getLayerId();
  }

  public void setCurrentLayer(int id) {
    this.currentLayer = id;
  }
 
  public Layer getLayer(int id) {
    ObservableList<Node> layers = this.getChildren();

    for (Node layer : layers) {
      if (((Layer) layer).getLayerId() == id) {
        return (Layer) layer;
      }
    }

    return null;
  }

  public void deleteLayer(int id) {}

  public void startDraw(double x, double y) {
    //gc of current layer id
    this.currentInstrument.setGraphicContext(this.getLayer(this.currentLayer));
    this.currentInstrument.setColor(currentColor);
    this.currentInstrument.setSize(currentSize);
    this.currentInstrument.startDraw(x, y);
  }
  public void draw(double x, double y) {
    this.currentInstrument.draw(x, y);
  }

  public void endDraw(double x, double y) {
    this.currentInstrument.endDraw(x, y);
  }

  public void setCurrentInstrument(String instrument) {
    switch (instrument) {
      case "Pen": 
        this.currentInstrument = new Pen();
        break;
      default:
        throw new Error("No instrument found");
    }
  }
}
