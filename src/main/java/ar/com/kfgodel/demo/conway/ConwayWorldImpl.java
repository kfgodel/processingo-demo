package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.mathe.api.BidiVector;

import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementation of the conway game
 * Created by tenpines on 14/11/15.
 */
public class ConwayWorldImpl implements ConwayWorld {

  private Set<BidiVector> currentLivingCells;
  private Set<BidiVector> previousLivingCells;
  private ReentrantLock stateLock;

  @Override
  public void advanceOneGeneration() {
    Set<BidiVector> nextLivingCells = calculateNextGeneration();
    stateLock.lock();
    try {
      previousLivingCells = currentLivingCells;
      currentLivingCells = nextLivingCells;
    }finally {
      stateLock.unlock();
    }
  }

  private Set<BidiVector> calculateNextGeneration() {
    return NextGenerationCalculator.create(currentLivingCells).calculate();
  }

  public static ConwayWorldImpl create(Set<BidiVector> survivingCells) {
    ConwayWorldImpl world = new ConwayWorldImpl();
    world.currentLivingCells = survivingCells;
    world.previousLivingCells = survivingCells;
    world.stateLock = new ReentrantLock();
    return world;
  }

  @Override
  public WorldAreaState getStateInside(FieldOfView segment) {
    stateLock.lock();
    try{
      return WorldAreaState.create(segment, previousLivingCells, currentLivingCells);
    }finally {
      stateLock.unlock();
    }
  }


}
