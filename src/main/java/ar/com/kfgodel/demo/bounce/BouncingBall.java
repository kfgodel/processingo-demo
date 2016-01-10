package ar.com.kfgodel.demo.bounce;

import ar.com.kfgodel.mathe.api.BidiVector;
import ar.com.kfgodel.mathe.api.Mathe;
import ar.com.kfgodel.mathe.api.Scalar;

/**
 * This type represents a bouncing ball that moves between a square space of [0,1],[0,1]
 * with a given velocity, bouncing back from each wall
 * Created by tenpines on 30/10/15.
 */
public interface BouncingBall {

  /**
   * Velocity of this ball
   */
  BidiVector velocity();

  /**
   * Current position of the ball
   */
  BidiVector position();

  /**
   * Size of the ball indicated as radius
   */
  Scalar radius();

  /**
   * Creates a default bouncing ball centered in the space, with a default velocity
   * @return The created ball
   */
  static BouncingBall createDefault() {
    return BouncingBallImpl.create(defaultPosition(), Mathe.vector(0.02, 0.01), defaultRadius());
  }

  /**
   * Moves this ball one position according to velocity
   */
  void move();

  /**
   * Changes the position of the ball to the given position
   */
  void positionOn(BidiVector newPosition);

  static BouncingBall withVelocity(BidiVector velocity) {
    return BouncingBallImpl.create(defaultPosition(), velocity, defaultRadius());
  }

  static Scalar defaultRadius() {
    return Mathe.scalar(0.03);
  }

  static BidiVector defaultPosition() {
    return Mathe.vector(0.5, 0.5);
  }

  Scalar rightSide();

  Scalar leftSide();

  Scalar bottomSide();

  Scalar topSide();

  /**
   * @return The ball diameter (double of the radius)
   */
  Scalar diameter();
}
