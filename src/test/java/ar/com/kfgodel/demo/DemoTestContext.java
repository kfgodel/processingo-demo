package ar.com.kfgodel.demo;

import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.kfgodel.demo.ants.Ant;
import ar.com.kfgodel.demo.ants.AntWorld;
import ar.com.kfgodel.demo.bounce.BouncingBall;
import ar.com.kfgodel.demo.conway.*;
import ar.com.kfgodel.demo.ripple.RippleWave;
import ar.com.kfgodel.demo.ripple.RippleWorld;
import ar.com.kfgodel.demo.ripple.WorldClock;
import ar.com.kfgodel.mathe.api.BidiVector;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * This type defines useful test objects
 * Created by tenpines on 30/10/15.
 */
public interface DemoTestContext extends TestContext {

  BouncingBall ball();
  void ball(Supplier<BouncingBall> definition);

  BidiVector velocity();
  void velocity(Supplier<BidiVector> definition);

  RippleWave ripple();
  void ripple(Supplier<RippleWave> definition);

  WorldClock clock();
  void clock(Supplier<WorldClock> definition);

  void rippleWorld(Supplier<RippleWorld> definition);
  RippleWorld rippleWorld();

  void antWorld(Supplier<AntWorld> definition);
  AntWorld antWorld();

  void ant(Supplier<Ant> definition);
  Ant ant();

  void antDirection(Supplier<BidiVector> definition);
  BidiVector antDirection();

  ConwayCamera camera();
  void camera(Supplier<ConwayCamera> definition);

  ConwayWorld conwayWorld();
  void conwayWorld(Supplier<ConwayWorld> definition);

  Snapshot snapshot();
  void snapshot(Supplier<Snapshot> definition);

  WorldAreaState areaState();
  void areaState(Supplier<WorldAreaState> definition);

  FieldOfView fieldOfView();
  void fieldOfView(Supplier<FieldOfView> definition);

  void cellStates(Supplier<Map<BidiVector, CellState>> definition);
  Map<BidiVector, CellState> cellStates();

  Set<BidiVector> previousCells();
  void previousCells(Supplier<Set<BidiVector>> definition);

  Set<BidiVector> currentCells();
  void currentCells(Supplier<Set<BidiVector>> definition);

  BidiVector[] initialCells();
  void initialCells(Supplier<BidiVector[]> definition);
}
