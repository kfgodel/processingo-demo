package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

/**
 * Implementation of the camera
 * Created by tenpines on 14/11/15.
 */
public class ConwayCameraImpl implements ConwayCamera {

  private ConwayWorld world;
  private Vector2d target;
  private Vector2d size;

  @Override
  public Snapshot takeSnapshot() {
    FieldOfView fieldOfview = calculateFieldOfView();
    WorldAreaState areaState = world.getStateInside(fieldOfview);
    return Snapshot.create(areaState);
  }

  private FieldOfView calculateFieldOfView() {
    Vector2d margin = size.scale(0.5);
    Vector2d topLeft = Vector2d.xy(target.x() - margin.x(), target.y() - margin.y());
    Vector2d bottomRight = Vector2d.xy(target.x() + margin.x(), target.y() + margin.y());
    return FieldOfView.create(topLeft, bottomRight);
  }

  public static ConwayCameraImpl create(Vector2d target, Vector2d size, ConwayWorld world) {
    ConwayCameraImpl camera = new ConwayCameraImpl();
    camera.world = world;
    camera.target = target;
    camera.size = size;
    return camera;
  }

  @Override
  public Vector2d target() {
    return target;
  }

  @Override
  public Vector2d size() {
    return size;
  }

}
