package ar.com.kfgodel.demo.ripple;

import ar.com.kfgodel.processingo.api.space.Vector2d;
import ar.com.kfgodel.processingo.api.time.TimeQuantity;

import java.util.List;

/**
 * This type represents the model of the world represented for ripple wave clicks
 *
 * Created by tenpines on 03/11/15.
 */
public interface RippleWorld {

  static RippleWorld create(WorldClock clock) {
    return RippleWorldImpl.create(clock);
  }

  /**
   * The current active ripple waves
   */
  List<RippleWave> ripples();

  /**
   * It indicates to this world that a new click has been done
   * @param mousePosition The position where the click happened
   */
  void mouseClickedOn(Vector2d mousePosition);

  /**
   * @return How much time do ripples last
   */
  TimeQuantity rippleLifespan();

  /**
   * Cleans the dead ripples from the list
   */
  void removeDeadRipples();
}
