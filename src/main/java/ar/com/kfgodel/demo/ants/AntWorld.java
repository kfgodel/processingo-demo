package ar.com.kfgodel.demo.ants;

import ar.com.kfgodel.processingo.api.space.Vector2d;

import java.util.Set;

/**
 * This type represents the simulation world of langston ants
 * Created by tenpines on 04/11/15.
 */
public interface AntWorld {

  static AntWorld create(Vector2d cellSpace) {
    return AntWorldImpl.create(cellSpace);
  }

  /**
   * Changes the state of this world advancing on time unit
   */
  void advanceOneTimeUnit();

  /**
   * @return The cell coordinates for the black spaces
   */
  Set<Vector2d> blackCells();

  /**
   * @return The cell coordinate of the ant
   */
  Ant ant();

  /**
   * @return Amount of horizontal and vertical cell (x, y)
   */
  Vector2d cellSpace();

  void setBlackCellOn(Vector2d cellCoordinate);

  void replaceAntWith(Ant newAnt);
}
