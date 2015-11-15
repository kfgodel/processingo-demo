package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

import java.util.Set;

/**
 * Implementation of the conway game
 * Created by tenpines on 14/11/15.
 */
public class ConwayWorldImpl implements ConwayWorld {

  private Set<Vector2d> currentLivingCells;
  private Set<Vector2d> previousLivingCells;

  @Override
  public void advanceOneGeneration() {

  }

  public static ConwayWorldImpl create(Set<Vector2d> survivingCells) {
    ConwayWorldImpl world = new ConwayWorldImpl();
    world.currentLivingCells = survivingCells;
    world.previousLivingCells = survivingCells;
    return world;
  }

  @Override
  public WorldAreaState getStateInside(FieldOfView segment) {
    return WorldAreaState.create(segment, previousLivingCells, currentLivingCells);
  }


}
