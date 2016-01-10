package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.mathe.api.BidiVector;

import java.util.Arrays;
import java.util.HashSet;

/**
 * This type represents the infinite space of cells for conway's game of life
 * Created by tenpines on 14/11/15.
 */
public interface ConwayWorld {
  /**
   * Changes the state of the conway world advancing one generation
   */
  void advanceOneGeneration();

  static ConwayWorld create(BidiVector... survivingCells) {
    return ConwayWorldImpl.create(new HashSet<>(Arrays.asList(survivingCells)));
  }

  /**
   * Returns the state of every active cell contained in the given segment.<br>
   *   The dead cells are omitted
   * @param segment The 2d space segment to look for
   * @return The position and state of every cell
   */
  WorldAreaState getStateInside(FieldOfView segment);
}
