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
    if(futurePosition.x() + radius > 1.0){
      bounced = true;
      velocity = velocity().invertX();
      futurePosition = Vector2d.xy(1 - radius, futurePosition.y());
    } else if(futurePosition.x() - radius < 0.0){
      bounced = true;
      velocity = velocity().invertX();
      futurePosition = Vector2d.xy(0 + radius, futurePosition.y());
    }
    if(futurePosition.y() + radius > 1.0){
      bounced = true;
      velocity = velocity().invertY();
      futurePosition = Vector2d.xy(futurePosition.x(), 1.0 - radius);
    } else if(futurePosition.y() - radius < 0.0){
      bounced = true;
      velocity = velocity().invertY();
      futurePosition = Vector2d.xy(futurePosition.x(), 0 + radius);
    }
    positionOn(futurePosition);
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
