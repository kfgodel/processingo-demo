package ar.com.kfgodel.demo.ripple;

import ar.com.kfgodel.mathe.api.BidiVector;
import ar.com.kfgodel.processingo.api.time.TimeQuantity;

/**
 * Created by tenpines on 02/11/15.
 */
public class RippleWaveImpl implements RippleWave {

  private BidiVector position;
  private TimeQuantity lifeSpan;
  private WorldClock clock;
  private long startTime;

  public static RippleWaveImpl create(BidiVector position, TimeQuantity lifeSpan, WorldClock clock) {
    RippleWaveImpl wave = new RippleWaveImpl();
    wave.position = position;
    wave.lifeSpan = lifeSpan;
    wave.clock = clock;
    wave.startTime = clock.currentMillis();
    return wave;
  }

  @Override
  public BidiVector position() {
    return position;
  }

  @Override
  public float radius() {
    float maxRadius = 200.0f;
    float lifeRatio = elapsedMillis() / (float)lifeSpan.toMillis();
    return maxRadius * lifeRatio;
  }

  @Override
  public boolean isDead() {
    return elapsedMillis() > lifeSpan.toMillis();
  }

  /**
   * Amount of milliseconds elapsed since this ripple creation
   * @return The amount of milliseconds since start
   */
  private long elapsedMillis() {
    return clock.currentMillis() - startTime;
  }

}
