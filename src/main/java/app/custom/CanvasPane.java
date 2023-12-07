package app.custom;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;

import app.custom.brushes.Instrument;
import app.custom.brushes.Pen;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;

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

    this.setCurrentInstrument(new Pen());

    this.setStyle("-fx-background-color: #FFFFFF;");
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

    newLayer.heightProperty().bind(this.heightProperty());
    newLayer.widthProperty().bind(this.widthProperty());
    
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

  public void setLayerOpacity(int id, double opacity) {
    Layer layer = this.getLayer(id);
    layer.setOpacity(opacity);
  }

  public void deleteLayer(int id) throws Exception {
    if (id == this.currentLayer) {
      if (this.getChildren().size() == 0) {
        throw new Exception("Cannot delete the last layer");
      }

      throw new Exception("Please select another layer to be current before deleting");
    }

    Layer layer = this.getLayer(id);
    this.getChildren().remove(layer);
  }

  public void startDraw(double x, double y) {
    try {
      //gc of current layer id
      this.currentInstrument.setGraphicContext(this.getLayer(this.currentLayer));
      this.currentInstrument.setColor(currentColor);
      this.currentInstrument.setSize(currentSize);
      this.currentInstrument.startDraw(x, y);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void draw(double x, double y) {
    try {
      this.currentInstrument.draw(x, y);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void endDraw(double x, double y) {
    try {
      this.currentInstrument.endDraw(x, y);
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void setCurrentInstrument(Instrument instrument) {
    this.currentInstrument = instrument;
  }

  public void openImage(Image image) {
    Layer layer = this.getLayer(this.currentLayer);

    double x = (this.getWidth() - image.getWidth()) / 2;
    double y = (this.getHeight() - image.getHeight()) / 2;

    layer.getGraphicsContext2D().drawImage(image, x, y);
  }

  public void reset() {
    ObservableList<Node> layers = this.getChildren();

    for (Node nodeLayer : layers) {
      Layer layer = (Layer) nodeLayer;

      layer.getGraphicsContext2D().clearRect(0.0, 0.0, layer.getWidth(), layer.getHeight());
    }
  }

  public void save() throws Exception {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
    File file = fileChooser.showSaveDialog(null);

    if (file == null) throw new Exception("No file selected");

    if (file != null) {
      WritableImage image = this.snapshot(new SnapshotParameters(), null);

      RenderedImage renderedImage = SwingFXUtils.fromFXImage(image, null);
      try {
        ImageIO.write(renderedImage, "png", file);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
