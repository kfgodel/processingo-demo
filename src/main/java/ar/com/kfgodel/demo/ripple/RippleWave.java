package ar.com.kfgodel.demo.ripple;

import ar.com.kfgodel.mathe.api.BidiVector;
import ar.com.kfgodel.processingo.api.time.TimeQuantity;

/**
 * This type represents a ripple wave created after clicking in a point of the screen
 *
 * Created by tenpines on 02/11/15.
 */
public interface RippleWave {

  static RippleWave create(BidiVector position, TimeQuantity lifeSpan, WorldClock worldClock) {
    return RippleWaveImpl.create(position, lifeSpan, worldClock);
  }

  BidiVector position();

  float radius();

  /**
   * Indicates if this ripple has no more activity
   */
  boolean isDead();
}
