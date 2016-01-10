package ar.com.kfgodel.demo.ants;

import ar.com.kfgodel.mathe.api.BidiVector;

import java.util.Set;

/**
 * This type represents the simulation world of langston ants
 * Created by tenpines on 04/11/15.
 */
public interface AntWorld {

  static AntWorld create(BidiVector cellSpace) {
    return AntWorldImpl.create(cellSpace);
  }

  /**
   * Changes the state of this world advancing on time unit
   */
  void advanceOneTimeUnit();

  /**
   * @return The cell coordinates for the black spaces
   */
  Set<BidiVector> blackCells();

  /**
   * @return The cell coordinate of the ant
   */
  Ant ant();

  /**
   * @return Amount of horizontal and vertical cell (x, y)
   */
  BidiVector cellSpace();

  void setBlackCellOn(BidiVector cellCoordinate);

  void replaceAntWith(Ant newAnt);
}
