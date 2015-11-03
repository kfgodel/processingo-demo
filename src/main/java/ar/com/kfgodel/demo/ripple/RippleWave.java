package ar.com.kfgodel.demo.ripple;

import ar.com.kfgodel.processingo.api.space.Vector2d;
import ar.com.kfgodel.processingo.api.time.TimeQuantity;

/**
 * This type represents a ripple wave created after clicking in a point of the screen
 *
 * Created by tenpines on 02/11/15.
 */
public interface RippleWave {

  static RippleWave create(Vector2d position, TimeQuantity lifeSpan, WorldClock worldClock) {
    return RippleWaveImpl.create(position, lifeSpan, worldClock);
  }

  Vector2d position();

  float radius();

  /**
   * Indicates if this ripple has no more activity
   */
  boolean isDead();
}
