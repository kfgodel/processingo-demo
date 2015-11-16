package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

/**
 * Implementation of the field of view
 * Created by tenpines on 14/11/15.
 */
public class FieldOfViewImpl implements FieldOfView {

  private Vector2d topLeft;
  private Vector2d bottomRight;

  public static FieldOfViewImpl create(Vector2d topLeft, Vector2d bottomRight) {
    FieldOfViewImpl fieldOfView = new FieldOfViewImpl();
    fieldOfView.topLeft = topLeft;
    fieldOfView.bottomRight = bottomRight;
    return fieldOfView;
  }

  @Override
  public Vector2d dimension() {
    return Vector2d.xy(bottomRight.x() - topLeft.x(), bottomRight.y() - topLeft.y());
  }

  @Override
  public boolean includes(Vector2d position) {
    return topLeft.x() <= position.x() && bottomRight.x() >= position.x()
      && topLeft.y() <= position.y() && bottomRight.y() >= position.y();
  }

  @Override
  public Vector2d makeRelative(Vector2d absolute) {
    return absolute.plus(topLeft.invert());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof FieldOfViewImpl)) return false;

    FieldOfViewImpl that = (FieldOfViewImpl) o;

    if (topLeft != null ? !topLeft.equals(that.topLeft) : that.topLeft != null) return false;
    return !(bottomRight != null ? !bottomRight.equals(that.bottomRight) : that.bottomRight != null);

  }

  @Override
  public int hashCode() {
    int result = topLeft != null ? topLeft.hashCode() : 0;
    result = 31 * result + (bottomRight != null ? bottomRight.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + topLeft + ", "+ bottomRight + "]";
  }
}
