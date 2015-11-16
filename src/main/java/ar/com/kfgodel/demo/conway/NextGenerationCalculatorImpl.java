package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This type implements the rules of the conways game
 * Created by tenpines on 15/11/15.
 */
public class NextGenerationCalculatorImpl implements NextGenerationCalculator {

  private static final Vector2d[] RELATIVE_NEIGHBORS = new Vector2d[]{
    Vector2d.xy(-1,-1),Vector2d.xy(0,-1),Vector2d.xy(1,-1),
    Vector2d.xy(-1,0),                    Vector2d.xy(1,0),
    Vector2d.xy(-1,1),Vector2d.xy(0,1),Vector2d.xy(1,1),
  };

  private Set<Vector2d> livingCells;
  private Set<Vector2d> deadNeighbors;

  public static NextGenerationCalculatorImpl create(Set<Vector2d> livingCells){
      NextGenerationCalculatorImpl calculator = new NextGenerationCalculatorImpl();
      calculator.livingCells = livingCells;
      calculator.deadNeighbors = new HashSet<>();
      return calculator;
  }

  @Override
  public Set<Vector2d> calculate() {
    Set<Vector2d> nextLivingCells = new HashSet<>();
    nextLivingCells.addAll(calculateSurvivors());
    nextLivingCells.addAll(calculateEmergents());
    return nextLivingCells;
  }

  private Set<Vector2d> calculateEmergents() {
    return deadNeighbors.stream()
      .filter(this::canRevive)
      .collect(Collectors.toSet());
  }

  private Set<Vector2d> calculateSurvivors() {
    return livingCells.stream()
      .filter(this::canSurvive)
      .collect(Collectors.toSet());
  }

  private boolean canSurvive(Vector2d livingCell) {
    Set<Vector2d> neighbors = calculateNeighborsOf(livingCell);
    int livingNeighbors = 0;
    for (Vector2d neighbor : neighbors) {
      if(livingCells.contains(neighbor)){
        livingNeighbors++;
      }else{
        // Used to verify if it becomes alive later
        deadNeighbors.add(neighbor);
      }
    }
    return livingNeighbors == 2 || livingNeighbors == 3;
  }

  private Set<Vector2d> calculateNeighborsOf(Vector2d livingCell) {
    return Arrays.stream(RELATIVE_NEIGHBORS)
      .map(livingCell::plus)
      .collect(Collectors.toSet());
  }

  private boolean canRevive(Vector2d deadCell) {
    Set<Vector2d> neighbors = calculateNeighborsOf(deadCell);
    int livingNeighbors = 0;
    for (Vector2d neighbor : neighbors) {
      if(livingCells.contains(neighbor)){
        livingNeighbors++;
      }
    }
    return livingNeighbors == 3;
  }

}
