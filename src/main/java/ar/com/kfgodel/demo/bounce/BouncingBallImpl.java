package ar.com.kfgodel.demo.bounce;

import ar.com.kfgodel.mathe.api.BidiVector;
import ar.com.kfgodel.mathe.api.Scalar;

import static ar.com.kfgodel.mathe.api.Mathe.*;

/**
 * Implementation of the bouncing ball
 * Created by tenpines on 30/10/15.
 */
public class BouncingBallImpl implements BouncingBall {

  private BidiVector position;
  private BidiVector velocity;
  private Scalar radius;

  public static BouncingBallImpl create(BidiVector position, BidiVector velocity, Scalar radius) {
    BouncingBallImpl ball = new BouncingBallImpl();
    ball.position = position;
    ball.velocity = velocity;
    ball.radius = radius;
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
  public Scalar radius() {
    return radius;
  }

  @Override
  public void move() {
    BidiVector futurePosition = position.plus(velocity);
    if(futurePosition.x().plus(radius).isGreaterThan(ONE_SCALAR)){
      velocity = velocity().invertX();
      futurePosition = vector(ONE_SCALAR.minus(radius), futurePosition.y());
    } else if(futurePosition.x().minus(radius).isLessThan(ZERO_SCALAR)){
      velocity = velocity().invertX();
      futurePosition = vector(ZERO_SCALAR.plus(radius), futurePosition.y());
    }
    if(futurePosition.y().plus(radius).isGreaterThan(ONE_SCALAR)){
      velocity = velocity().invertY();
      futurePosition = vector(futurePosition.x(), ONE_SCALAR.minus(radius));
    } else if(futurePosition.y().minus(radius).isLessThan(ZERO_SCALAR)){
      velocity = velocity().invertY();
      futurePosition = vector(futurePosition.x(), ZERO_SCALAR.plus(radius));
    }
    positionOn(futurePosition);
  }

  @Override
  public void positionOn(BidiVector newPosition) {
    this.position = newPosition;
  }

  @Override
  public Scalar rightSide() {
    return position.x().plus(radius);
  }

  @Override
  public Scalar leftSide() {
    return position.x().minus(radius);
  }

  @Override
  public Scalar bottomSide() {
    return position.y().plus(radius);
  }

  @Override
  public Scalar topSide() {
    return position.y().minus(radius);
  }

  @Override
  public Scalar diameter() {
    return radius().doubled();
  }
}
