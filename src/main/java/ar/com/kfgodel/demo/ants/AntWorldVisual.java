package ar.com.kfgodel.demo.ants;

import ar.com.kfgodel.mathe.api.BidiVector;
import ar.com.kfgodel.mathe.api.Mathe;
import ar.com.kfgodel.processingo.api.original.ProcessingCanvas;
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
  private BidiVector cellSize;

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

  private RectangleVisual convertToRectangles(BidiVector blackCell) {
    BidiVector position = blackCell.componentProduct(getCellSize());
    BidiVector cellSize = getCellSize();
    return RectangleVisual.create(position, cellSize);
  }

  private BidiVector getCellSize() {
    if(cellSize == null){
      cellSize = calculateCellSize();
    }
    return cellSize;
  }

  private BidiVector calculateCellSize() {
    float amountOfHorizontalCells = world.cellSpace().x().asFloat();
    float cellWidth = viewSize.width() / amountOfHorizontalCells;

    float amountOfVerticalCells = world.cellSpace().y().asFloat();
    float cellHeight = viewSize.height() / amountOfVerticalCells;

    return Mathe.vector(cellWidth, cellHeight);
  }

  public static AntWorldVisual create(AntWorld world, ViewSize viewSize) {
    AntWorldVisual visual = new AntWorldVisual();
    visual.world = world;
    visual.viewSize = viewSize;
    return visual;
  }

}
