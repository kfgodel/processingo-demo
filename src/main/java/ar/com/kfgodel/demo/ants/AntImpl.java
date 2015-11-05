package ar.com.kfgodel.demo.ants;

import ar.com.kfgodel.processingo.api.space.Vector2d;

/**
 * Created by tenpines on 04/11/15.
 */
public class AntImpl implements Ant {

  private Vector2d position;
  private Vector2d direction;

  public static AntImpl create(Vector2d initialPosition, Vector2d initialDirection) {
    AntImpl ant = new AntImpl();
    ant.position = initialPosition;
    ant.direction = initialDirection;
    return ant;
  }

  @Override
  public Vector2d position() {
    return position.integered();
  }

  @Override
  public Vector2d direction() {
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
  public Vector2d nextPosition() {
    return position.plus(direction);
  }

}
