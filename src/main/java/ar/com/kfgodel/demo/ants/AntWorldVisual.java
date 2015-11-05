package ar.com.kfgodel.demo.ants;

import ar.com.kfgodel.processingo.api.original.ProcessingCanvas;
import ar.com.kfgodel.processingo.api.space.Vector2d;
import ar.com.kfgodel.processingo.api.viewports.ViewSize;
import ar.com.kfgodel.processingo.api.visuals.VisualDescription;
import ar.com.kfgodel.processingo.impl.visuals.BackgroundVisual;
import ar.com.kfgodel.processingo.impl.visuals.RectangleVisual;

import java.awt.*;

/**
 * This type represents the visual description (in terms of processing) of the ant world for each frame
 * Created by tenpines on 04/11/15.
 */
public class AntWorldVisual implements VisualDescription {

  private AntWorld world;
  private ViewSize viewSize;
  private Vector2d cellSize;

  @Override
  public void applyOn(ProcessingCanvas canvas) {
    BackgroundVisual.create(0xAAAAAAAA)
      .applyOn(canvas);

    canvas.fill(Color.BLACK.getRGB());
    world.blackCells().stream()
      .map(this::convertToRectangles)
      .forEach((rectangle) -> rectangle.applyOn(canvas));

    canvas.fill(Color.RED.getRGB());
    RectangleVisual antRectangle = this.convertToRectangles(world.ant().position());
    antRectangle.applyOn(canvas);
  }

  private RectangleVisual convertToRectangles(Vector2d blackCell) {
    Vector2d position = blackCell.elementProduct(getCellSize());
    Vector2d cellSize = getCellSize();
    return RectangleVisual.create(position, cellSize);
  }

  private Vector2d getCellSize() {
    if(cellSize == null){
      cellSize = calculateCellSize();
    }
    return cellSize;
  }

  private Vector2d calculateCellSize() {
    float amountOfHorizontalCells = world.cellSpace().x();
    float cellWidth = viewSize.width() / amountOfHorizontalCells;

    float amountOfVerticalCells = world.cellSpace().y();
    float cellHeight = viewSize.height() / amountOfVerticalCells;

    return Vector2d.xy(cellWidth, cellHeight);
  }

  public static AntWorldVisual create(AntWorld world, ViewSize viewSize) {
    AntWorldVisual visual = new AntWorldVisual();
    visual.world = world;
    visual.viewSize = viewSize;
    return visual;
  }

}
