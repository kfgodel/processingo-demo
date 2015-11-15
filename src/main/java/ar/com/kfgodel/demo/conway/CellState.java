package ar.com.kfgodel.demo.conway;

/**
 * This type represents the state of a cell
 * Created by tenpines on 14/11/15.
 */
public interface CellState {
  static CellState surviving(){
    return new CellState() {
    };
  }
}
