package ar.com.kfgodel.demo.conway;

/**
 * Created by tenpines on 14/11/15.
 */
public class SurvivingState implements CellState {

  public static final SurvivingState INSTANCE = new SurvivingState();

  @Override
  public void whenSurviving(Runnable action) {
    action.run();
  }

  @Override
  public void whenDying(Runnable action) {
    // Do nothing
  }

  @Override
  public void whenEmerging(Runnable action) {
    // Do nothing
  }

}
