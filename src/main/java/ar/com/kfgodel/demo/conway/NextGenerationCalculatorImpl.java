package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.mathe.api.BidiVector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static ar.com.kfgodel.mathe.api.Mathe.vector;

/**
 * This type implements the rules of the conways game
 * Created by tenpines on 15/11/15.
 */
public class NextGenerationCalculatorImpl implements NextGenerationCalculator {

  private static final BidiVector[] RELATIVE_NEIGHBORS = new BidiVector[]{
    vector(-1,-1),vector(0,-1),vector(1,-1),
    vector(-1,0),              vector(1,0),
    vector(-1,1),vector(0,1), vector(1,1),
  };

  private Set<BidiVector> livingCells;
  private Set<BidiVector> deadNeighbors;

  public static NextGenerationCalculatorImpl create(Set<BidiVector> livingCells){
      NextGenerationCalculatorImpl calculator = new NextGenerationCalculatorImpl();
      calculator.livingCells = livingCells;
      calculator.deadNeighbors = new HashSet<>();
      return calculator;
  }

  @Override
  public Set<BidiVector> calculate() {
    Set<BidiVector> nextLivingCells = new HashSet<>();
    nextLivingCells.addAll(calculateSurvivors());
    nextLivingCells.addAll(calculateEmergents());
    return nextLivingCells;
  }

  private Set<BidiVector> calculateEmergents() {
    return deadNeighbors.stream()
      .filter(this::canRevive)
      .collect(Collectors.toSet());
  }

  private Set<BidiVector> calculateSurvivors() {
    return livingCells.stream()
      .filter(this::canSurvive)
      .collect(Collectors.toSet());
  }

  private boolean canSurvive(BidiVector livingCell) {
    Set<BidiVector> neighbors = calculateNeighborsOf(livingCell);
    int livingNeighbors = 0;
    for (BidiVector neighbor : neighbors) {
      if(livingCells.contains(neighbor)){
        livingNeighbors++;
      }else{
        // Used to verify if it becomes alive later
        deadNeighbors.add(neighbor);
      }
    }
    return livingNeighbors == 2 || livingNeighbors == 3;
  }

  private Set<BidiVector> calculateNeighborsOf(BidiVector livingCell) {
    return Arrays.stream(RELATIVE_NEIGHBORS)
      .map(livingCell::plus)
      .collect(Collectors.toSet());
  }

  private boolean canRevive(BidiVector deadCell) {
    Set<BidiVector> neighbors = calculateNeighborsOf(deadCell);
    int livingNeighbors = 0;
    for (BidiVector neighbor : neighbors) {
      if(livingCells.contains(neighbor)){
        livingNeighbors++;
      }
    }
    return livingNeighbors == 3;
  }

}
