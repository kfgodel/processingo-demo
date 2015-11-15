package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

/**
 * Created by tenpines on 14/11/15.
 */
public class WorldAreaStateImpl implements WorldAreaState {

  private FieldOfView fieldOfView;

  public static WorldAreaStateImpl create(FieldOfView fieldOfView) {
    WorldAreaStateImpl state = new WorldAreaStateImpl();
    state.fieldOfView = fieldOfView;
    return state;
  }

  @Override
  public Vector2d dimension() {
    return fieldOfView.dimension();
  }

}
