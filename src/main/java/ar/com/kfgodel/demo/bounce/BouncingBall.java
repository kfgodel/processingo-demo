package ar.com.kfgodel.demo.bounce;

import ar.com.kfgodel.processingo.api.space.Vector2d;

/**
 * This type represents a bouncing ball that moves between a square space of [0,1],[0,1]
 * with a given velocity, bouncing back from each wall
 * Created by tenpines on 30/10/15.
 */
public interface BouncingBall {

  /**
   * Velocity of this ball
   */
  Vector2d velocity();

  /**
   * Current position of the ball
   */
  Vector2d position();

  /**
   * Size of the ball indicated as radius
   */
  float radius();

  /**
   * Creates a default bouncing ball centered in the space, with a default velocity
   * @return The created ball
   */
  static BouncingBall createDefault() {
    return BouncingBallImpl.create(defaultPosition(), Vector2d.xy(0.02, 0.01), defaultRadius());
  }

  static Vector2d defaultPosition() {
    return Vector2d.xy(0.5, 0.5);
  }

  /**
   * Moves this ball one position according to velocity
   */
  void move();

  /**
   * Changes the position of the ball to the given position
   */
  void positionOn(Vector2d newPosition);

  static BouncingBall withVelocity(Vector2d velocity) {
    return BouncingBallImpl.create(defaultPosition(), velocity, defaultRadius());
  }

  static double defaultRadius() {
    return 0.03;
  }

  float highestXPoint();

  float lowestXPoint();

  float highestYPoint();

  float lowestYPoint();
}
