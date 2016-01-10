package ar.com.kfgodel.demo.bounce;

import ar.com.kfgodel.mathe.api.BidiVector;

import static ar.com.kfgodel.mathe.api.Mathe.vector;

/**
 * Implementation of the bouncing ball
 * Created by tenpines on 30/10/15.
 */
public class BouncingBallImpl implements BouncingBall {

  private BidiVector position;
  private BidiVector velocity;
  private float radius;

  public static BouncingBallImpl create(BidiVector position, BidiVector velocity, double radius) {
    BouncingBallImpl ball = new BouncingBallImpl();
    ball.position = position;
    ball.velocity = velocity;
    ball.radius = (float) radius;
    return ball;
  }

  @Override
  public BidiVector velocity() {
    return velocity;
  }

  @Override
  public BidiVector position() {
    return position;
  }

  @Override
  public float radius() {
    return radius;
  }

  @Override
  public void move() {
    BidiVector futurePosition = position.plus(velocity);
    boolean bounced = false;
    if(futurePosition.x().asDouble() + radius > 1.0){
      bounced = true;
      velocity = velocity().invertX();
      futurePosition = vector(1 - radius, futurePosition.y().asDouble());
    } else if(futurePosition.x().asDouble() - radius < 0.0){
      bounced = true;
      velocity = velocity().invertX();
      futurePosition = vector(0 + radius, futurePosition.y().asDouble());
    }
    if(futurePosition.y().asDouble() + radius > 1.0){
      bounced = true;
      velocity = velocity().invertY();
      futurePosition = vector(futurePosition.x().asDouble(), 1.0 - radius);
    } else if(futurePosition.y().asDouble() - radius < 0.0){
      bounced = true;
      velocity = velocity().invertY();
      futurePosition = vector(futurePosition.x().asDouble(), 0 + radius);
    }
    positionOn(futurePosition);
  }

  @Override
  public void positionOn(BidiVector newPosition) {
    this.position = newPosition;
  }

  @Override
  public float highestXPoint() {
    return position.x().asFloat() + radius;
  }

  @Override
  public float lowestXPoint() {
    return position.x().asFloat() - radius;
  }

  @Override
  public float highestYPoint() {
    return position.y().asFloat() + radius;
  }

  @Override
  public float lowestYPoint() {
    return position.y().asFloat() - radius;
  }
}
