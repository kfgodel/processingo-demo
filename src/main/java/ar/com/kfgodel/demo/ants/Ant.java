package ar.com.kfgodel.demo.ants;

import ar.com.kfgodel.processingo.api.space.Vector2d;

/**
 * This type represents the ant moving in langston ant world
 * Created by tenpines on 04/11/15.
 */
public interface Ant {

  static Ant create(Vector2d initialPosition, Vector2d initialDirection) {
    return AntImpl.create(initialPosition, initialDirection);
  }

  Vector2d position();

  Vector2d direction();

  void turnLeft();

  void turnRight();

  void advance();

  /**
   * The position the ant will occupy if advanced
   */
  Vector2d nextPosition();
}
