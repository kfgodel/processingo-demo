package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

/**
 * This type represents a portion of the conway space
 * Created by tenpines on 14/11/15.
 */
public interface WorldAreaState {

  static WorldAreaState create(FieldOfView fieldOfView) {
    return WorldAreaStateImpl.create(fieldOfView);
  }

  Vector2d dimension();
}
