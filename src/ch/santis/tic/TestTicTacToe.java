/**
 * @author philipp gressly freimann. 15. Feb. 2011.
 * CS IL10: Rekursion: Tic Tac Toe
 */ 

package ch.santis.tic;

import java.awt.Point;
import static ch.santis.tic.TicTacToeFeld.*;


public class TestTicTacToe {
  public static void main(String[] args) {
    new TestTicTacToe().top();
  }
  
  void top() {
    // MoveTest
    TicTacToeFeld fld = testFeld();
    TicTacToeFeld neu = fld.clone();
    neu.set(2,1, PLAYER_O);
    System.out.println(fld);
    System.out.println(neu);
    
    // bestTest
    TicTacToeKI ki = new TicTacToeKI();
    Point p;
    p = ki.bestMove(fld, PLAYER_X);
    System.out.println("best Move:" + p);
  }

/**
 * Erzeuge das Testfeld, dafür mussten wir etwas suchen:
 * Das Feld sollte nur noch drei freie Felder haben,
 * darf im 1. Zug nicht sofort beendet sein und muss alle drei
 * Ausgänge (Sieg X, Sieg O und unentschieden zulassen:
 * 
 *    XO.
 *    OOX
 *    X..
 */
  TicTacToeFeld testFeld() {
    TicTacToeFeld feld = new TicTacToeFeld();
    feld.set(0, 0, PLAYER_X);
    feld.set(0, 1, PLAYER_O); 
    feld.set(0, 2, NOPLAYER);
    feld.set(1, 0, PLAYER_O);
    feld.set(1, 1, PLAYER_O); 
    feld.set(1, 2, PLAYER_X);
    feld.set(2, 0, PLAYER_X);
    feld.set(2, 1, NOPLAYER); 
    feld.set(2, 2, NOPLAYER);
    return feld;
  }

}  // end of class TestTicTacToe
