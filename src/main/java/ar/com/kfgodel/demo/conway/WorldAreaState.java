package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

import java.util.Map;
import java.util.Set;

/**
 * This type represents a portion of the conway space
 * Created by tenpines on 14/11/15.
 */
public interface WorldAreaState {

  static WorldAreaState create(FieldOfView fieldOfView, Set<Vector2d> previousLivingCells, Set<Vector2d> currentLivingCells) {
    return WorldAreaStateImpl.create(fieldOfView, previousLivingCells, currentLivingCells);
  }

  /**
   * @return The width and height of this area
   */
  Vector2d dimension();

  /**
   * @return Each of the associated states for non dead cells
   */
  Map<Vector2d, CellState> cellStates();
}
