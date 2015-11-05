package ar.com.kfgodel.demo.ants;

import ar.com.kfgodel.processingo.api.space.Vector2d;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * This type implements the ant world
 * Created by tenpines on 04/11/15.
 */
public class AntWorldImpl implements AntWorld {

  private Vector2d cellSpace;
  private Ant ant;
  private Set<Vector2d> blackCells;

  public static AntWorldImpl create(Vector2d cellSpace) {
    AntWorldImpl world = new AntWorldImpl();
    world.cellSpace = cellSpace;
    Vector2d initialAntPosition = cellSpace.scale(0.5).integered();
    world.ant = Ant.create(initialAntPosition, Vector2d.xy(0, -1));
    world.blackCells = new CopyOnWriteArraySet<>();
    return world;
  }

  @Override
  public void advanceOneTimeUnit() {
    if(antIsOnBlackCell()){
      ant().turnLeft();
    }else{
      ant().turnRight();
    }
    if(antCanMove()){
      flipAntCell();
      ant().advance();
    }
  }

  private void flipAntCell() {
    if(antIsOnBlackCell()){
      blackCells.remove(ant().position());
    }else{
      blackCells.add(ant().position());
    }
  }

  private boolean antCanMove() {
    Vector2d nextPosition = ant().nextPosition();
    boolean willBeOnValidXSpace = nextPosition.x() >= 0 && nextPosition.x() < cellSpace.x();
    boolean willBeOnValidYSpace = nextPosition.y() >= 0 && nextPosition.y() < cellSpace.y();
    return willBeOnValidXSpace && willBeOnValidYSpace;
  }

  private boolean antIsOnBlackCell() {
    return blackCells.contains(ant().position());
  }

  @Override
  public Set<Vector2d> blackCells() {
    return blackCells;
  }

  @Override
  public Ant ant() {
    return ant;
  }

  @Override
  public Vector2d cellSpace() {
    return cellSpace;
  }

  @Override
  public void setBlackCellOn(Vector2d cellCoordinate) {
    blackCells.add(cellCoordinate);
  }

  @Override
  public void replaceAntWith(Ant newAnt) {
    this.ant = newAnt;
  }


}
