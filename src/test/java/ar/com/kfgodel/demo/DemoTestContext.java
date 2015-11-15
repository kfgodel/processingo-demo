package ar.com.kfgodel.demo;

import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.kfgodel.demo.ants.Ant;
import ar.com.kfgodel.demo.ants.AntWorld;
import ar.com.kfgodel.demo.bounce.BouncingBall;
import ar.com.kfgodel.demo.conway.*;
import ar.com.kfgodel.demo.ripple.RippleWave;
import ar.com.kfgodel.demo.ripple.RippleWorld;
import ar.com.kfgodel.demo.ripple.WorldClock;
import ar.com.kfgodel.processingo.api.space.Vector2d;

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

  Vector2d velocity();
  void velocity(Supplier<Vector2d> definition);

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

  void antDirection(Supplier<Vector2d> definition);
  Vector2d antDirection();

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

  void cellStates(Supplier<Map<Vector2d, CellState>> definition);
  Map<Vector2d, CellState> cellStates();

  Set<Vector2d> previousCells();
  void previousCells(Supplier<Set<Vector2d>> definition);

  Set<Vector2d> currentCells();
  void currentCells(Supplier<Set<Vector2d>> definition);
}
