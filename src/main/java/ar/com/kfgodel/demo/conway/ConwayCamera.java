package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

/**
 * This type represents a limited size view of a conway game of life infinite space
 *
 * Created by tenpines on 14/11/15.
 */
public interface ConwayCamera {
  /**
   * Takes the portion of space looked by this camera and captures it in a non changing snapshot, that
   * won't be affected by the passing of time on the game
   * @return The captured partial state from the game
   */
  Snapshot takeSnapshot();

  static ConwayCamera create(Vector2d target, Vector2d size, ConwayWorld world) {
    return ConwayCameraImpl.create(target, size, world);
  }

  /**
   * @return The cell this camera is targeting at
   */
  Vector2d target();

  /**
   * @return The 2d dimensions of this camera view in terms of cells
   */
  Vector2d size();
}
