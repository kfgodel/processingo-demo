package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.mathe.api.BidiVector;

import java.util.Map;
import java.util.Set;

/**
 * This type represents a portion of the conway space
 * Created by tenpines on 14/11/15.
 */
public interface WorldAreaState {

  static WorldAreaState create(FieldOfView fieldOfView, Set<BidiVector> previousLivingCells, Set<BidiVector> currentLivingCells) {
    return WorldAreaStateImpl.create(fieldOfView, previousLivingCells, currentLivingCells);
  }

  /**
   * @return The width and height of this area
   */
  BidiVector dimension();

  /**
   * @return Each of the associated states for non dead cells
   */
  Map<BidiVector, CellState> activeCellStates();

  /**
   * Makes the given world position relative to this area (taking the top lef corner as 0,0)
   * and the bottom right the positive infinities
   * @param absolute The absolute position
   * @return The relative to this area position
   */
  BidiVector makeRelative(BidiVector absolute);
}
