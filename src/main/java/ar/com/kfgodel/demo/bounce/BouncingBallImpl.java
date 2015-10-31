package ar.com.kfgodel.demo.bounce;

import ar.com.kfgodel.processingo.api.space.Vector2d;

/**
 * Implementation of the bouncing ball
 * Created by tenpines on 30/10/15.
 */
public class BouncingBallImpl implements BouncingBall {

  private Vector2d position;
  private Vector2d velocity;
  private float radius;

  public static BouncingBallImpl create(Vector2d position, Vector2d velocity, double radius) {
    BouncingBallImpl ball = new BouncingBallImpl();
    ball.position = position;
    ball.velocity = velocity;
    ball.radius = (float) radius;
    return ball;
  }

  @Override
  public Vector2d velocity() {
    return velocity;
  }

  @Override
  public Vector2d position() {
    return position;
  }

  @Override
  public float radius() {
    return radius;
  }

  @Override
  public void move() {
    Vector2d futurePosition = position.plus(velocity);
    boolean bounced = false;
    if(futurePosition.x() + radius > 1.0 || futurePosition.x() - radius < 0.0){
      bounced = true;
      velocity = velocity().invertX();
    }
    if(futurePosition.y() + radius > 1.0 || futurePosition.y() - radius < 0.0){
      bounced = true;
      velocity = velocity().invertY();
    }
    if(!bounced){
      // Only change position if not bouncing off a wall
      positionOn(futurePosition);
    }
  }

  @Override
  public void positionOn(Vector2d newPosition) {
    this.position = newPosition;
  }

  @Override
  public float highestXPoint() {
    return position.x() + radius;
  }

  @Override
  public float lowestXPoint() {
    return position.x() - radius;
  }

  @Override
  public float highestYPoint() {
    return position.y() + radius;
  }

  @Override
  public float lowestYPoint() {
    return position.y() - radius;
  }
}
