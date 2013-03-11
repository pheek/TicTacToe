/**
 * @author philipp gressly freimann. 15. Feb. 2011.
 * CS IL10: Rekursion: Tic Tac Toe
 */

package ch.santis.tic;

import java.awt.Point;
import static ch.santis.tic.TicTacToeFeld.*;

public class TicTacToeKI {
  public Point bestMove(TicTacToeFeld feld, char spieler) {
    int bestWert  = -1;
    Point bestMove = null;
    for(Point move: feld.getAlleZuege()) {
       TicTacToeFeld tempFeld = feld.clone();
       tempFeld.set(move, spieler);
       int aktWert = bewerte(tempFeld, spieler, false);
       if(aktWert > bestWert) {
           bestWert = aktWert;
           bestMove = move;
       }
    }
    if(null == bestMove) {
        return feld.randomMove();
    }
    return bestMove;
  }

/**
 * Bewerte rekursiv ein TicTacToe-Feld.
 * @param feld
 * @param spieler    Für diesen Spieler wird
 *                   bewertet.
 * @param maximieren Es handelt sich um einen
 *                   Maximierungsschritt
 * @return Wert der Postition "feld" für den
 *         Spieler "spieler"
 */
  int bewerte(TicTacToeFeld feld, char spieler, boolean maximieren) {
    if(feld.isWin(PLAYER_X)) {
      if(PLAYER_X == spieler) {
        return  1; 
      } else {
        return -1;
      }
    }
    if(feld.isWin(PLAYER_O)) {
      if(PLAYER_O == spieler) {
        return  1; 
      } else {
        return -1;
      } 
    }
    if(feld.isFull()) {
      return 0; 
    }
    
    // Rekursionsschritt
    int bestWert;
    if(maximieren) { // Maximierungsschritt?
        bestWert = -2;
    } else {
        bestWert =  2;
    }
    for(Point move: feld.getAlleZuege()) {
      TicTacToeFeld tempFld = feld.clone();
      char zieher = maximieren ?
                    spieler    :
                    feld.other(spieler);
      tempFld.set(move, zieher);
      int aktWert = bewerte(tempFld, spieler, !maximieren);
      if(maximieren) {
          if(aktWert > bestWert) {
              bestWert = aktWert;
          }
      } else { // minimieren 
          if(aktWert < bestWert) {
              bestWert = aktWert;
          }
      }
    }
    return bestWert;
  } //bewerte
  

  
} // end of class TicTacToeKI