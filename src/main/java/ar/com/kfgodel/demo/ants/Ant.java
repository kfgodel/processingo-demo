package ar.com.kfgodel.demo.ants;

import ar.com.kfgodel.mathe.api.BidiVector;

/**
 * This type represents the ant moving in langston ant world
 * Created by tenpines on 04/11/15.
 */
public interface Ant {

  static Ant create(BidiVector initialPosition, BidiVector initialDirection) {
    return AntImpl.create(initialPosition, initialDirection);
  }

  BidiVector position();

  BidiVector direction();

  void turnLeft();

  void turnRight();

  void advance();

  /**
   * The position the ant will occupy if advanced
   */
  BidiVector nextPosition();
}
