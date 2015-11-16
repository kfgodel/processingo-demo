package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

import java.util.Set;

/**
 * This type represents the rule processor of the conways world
 * Created by tenpines on 15/11/15.
 */
public interface NextGenerationCalculator {

  static NextGenerationCalculator create(Set<Vector2d> livingCells) {
    return NextGenerationCalculatorImpl.create(livingCells);
  }

  Set<Vector2d> calculate();
}
