package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.mathe.api.BidiVector;

/**
 * This type represents a segment of the conways world that is observable by a camera
 * Created by tenpines on 14/11/15.
 */
public interface FieldOfView {

  static FieldOfView create(BidiVector topLeft, BidiVector bottomRight) {
    return FieldOfViewImpl.create(topLeft, bottomRight);
  }

  BidiVector dimension();

  boolean includes(BidiVector position);

  BidiVector makeRelative(BidiVector absolute);
}
