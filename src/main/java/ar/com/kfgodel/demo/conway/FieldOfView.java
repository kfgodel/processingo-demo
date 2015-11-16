package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

/**
 * This type represents a segment of the conways world that is observable by a camera
 * Created by tenpines on 14/11/15.
 */
public interface FieldOfView {

  static FieldOfView create(Vector2d topLeft, Vector2d bottomRight) {
    return FieldOfViewImpl.create(topLeft, bottomRight);
  }

  Vector2d dimension();

  boolean includes(Vector2d position);

  Vector2d makeRelative(Vector2d absolute);
}
