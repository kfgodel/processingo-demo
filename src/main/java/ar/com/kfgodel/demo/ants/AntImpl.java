package ar.com.kfgodel.demo.ants;

import ar.com.kfgodel.mathe.api.BidiVector;

/**
 * Created by tenpines on 04/11/15.
 */
public class AntImpl implements Ant {

  private BidiVector position;
  private BidiVector direction;

  public static AntImpl create(BidiVector initialPosition, BidiVector initialDirection) {
    AntImpl ant = new AntImpl();
    ant.position = initialPosition;
    ant.direction = initialDirection;
    return ant;
  }

  @Override
  public BidiVector position() {
    return position.integered();
  }

  @Override
  public BidiVector direction() {
    return direction.integered();
  }

  @Override
  public void turnLeft() {
    direction = direction.rotate(-90);
  }

  @Override
  public void turnRight() {
    direction = direction.rotate(90);
  }

  @Override
  public void advance() {
    position = nextPosition();
  }

  @Override
  public BidiVector nextPosition() {
    return position.plus(direction);
  }

}
