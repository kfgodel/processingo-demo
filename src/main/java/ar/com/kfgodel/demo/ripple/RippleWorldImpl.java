package ar.com.kfgodel.demo.ripple;

import ar.com.kfgodel.mathe.api.BidiVector;
import ar.com.kfgodel.processingo.api.time.TimeQuantity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * This implements the demo world for mouse click ripples
 * Created by tenpines on 03/11/15.
 */
public class RippleWorldImpl implements RippleWorld {

  private CopyOnWriteArrayList<RippleWave> waves;
  private WorldClock clock;

  public static RippleWorldImpl create(WorldClock clock) {
    RippleWorldImpl world = new RippleWorldImpl();
    world.waves = new CopyOnWriteArrayList<>();
    world.clock = clock;
    return world;
  }

  @Override
  public List<RippleWave> ripples() {
    return waves;
  }

  @Override
  public void mouseClickedOn(BidiVector mousePosition) {
    waves.add(RippleWave.create(mousePosition, rippleLifespan(), clock));
  }

  @Override
  public TimeQuantity rippleLifespan() {
    return TimeQuantity.of(2, TimeUnit.SECONDS);
  }

  @Override
  public void removeDeadRipples() {
    waves.removeIf(RippleWave::isDead);
  }


}
