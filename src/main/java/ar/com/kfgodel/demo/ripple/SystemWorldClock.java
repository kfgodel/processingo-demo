package ar.com.kfgodel.demo.ripple;

/**
 * This type represents the world clock that uses the real system time
 * Created by tenpines on 02/11/15.
 */
public class SystemWorldClock implements WorldClock {
  @Override
  public long currentMillis() {
    return System.currentTimeMillis();
  }

  public static SystemWorldClock create(){
      SystemWorldClock clock = new SystemWorldClock();
      return clock;
  }

}
