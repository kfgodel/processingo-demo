package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.mathe.api.BidiVector;

/**
 * Implementation of the field of view
 * Created by tenpines on 14/11/15.
 */
public class FieldOfViewImpl implements FieldOfView {

  private BidiVector topLeft;
  private BidiVector bottomRight;

  public static FieldOfViewImpl create(BidiVector topLeft, BidiVector bottomRight) {
    FieldOfViewImpl fieldOfView = new FieldOfViewImpl();
    fieldOfView.topLeft = topLeft;
    fieldOfView.bottomRight = bottomRight;
    return fieldOfView;
  }

  @Override
  public BidiVector dimension() {
    return bottomRight.minus(topLeft);
  }

  @Override
  public boolean includes(BidiVector position) {
    return topLeft.x().isLessOrEqualTo(position.x()) && bottomRight.x().isGreaterOrEqualTo(position.x())
      && topLeft.y().isLessOrEqualTo(position.y()) && bottomRight.y().isGreaterOrEqualTo(position.y());
  }

  @Override
  public BidiVector makeRelative(BidiVector absolute) {
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
