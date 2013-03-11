/**
 * @author philipp gressly freimann. 15. Feb. 2011.
 * CS IL10: Rekursion: Tic Tac Toe
 */

package ch.santis.tic;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Feld: 3x3
 * @author Philipp Gressly (phi AT gressly DOT ch)
 */
public class TicTacToeFeld {
  public static final char PLAYER_X = 'X';
  public static final char PLAYER_O = 'O';
  public static final char NOPLAYER =  0 ;
  
  char [][] feld;
  
  public TicTacToeFeld() {
      feld = new char[3][3];
  }
  
  public char get(int zeile, int spalte) {
      return feld[zeile][spalte];
  }

  public void set(int zeile, int spalte, char player) {
      feld[zeile][spalte] = player;
  }

  public void set(Point move, char spieler) {
     set(move.x, move.y, spieler);  
  }
  
  
  public boolean isFull() {
    int belegt = 0;
    for(int zeile = 0; zeile < 3; zeile ++) {
        for(int spalte = 0; spalte < 3; spalte ++) {
            if(0 != get(zeile, spalte)) {
                belegt = belegt + 1;
            }
        }
    }
    return 9 == belegt; 
  }
  
  final static int [][][] SIEGESREIHEN =
  {
          // Zeilen
          {{0, 0}, {0, 1}, {0, 2}},
          {{1, 0}, {1, 1}, {1, 2}},
          {{2, 0}, {2, 1}, {2, 2}},
          // Spalten
          {{0, 0}, {1, 0}, {2, 0}},
          {{0, 1}, {1, 1}, {2, 1}},
          {{0, 2}, {1, 2}, {2, 2}},
          // Diagonalen
          {{0, 0}, {1, 1}, {2, 2}},
          {{2, 0}, {1, 1}, {0, 2}}
        };
  
  /**
   * isWin() liefert:
   *  - PLAYER_X, falls GewinnPosition für X
   *  - PLAYER_O, falls GewinnPosition für O
   *  - NOPLAYER, sonst
   */
  public char isWin() {
     if(isWin(PLAYER_X)) return PLAYER_X;
     if(isWin(PLAYER_O)) return PLAYER_O;
     return NOPLAYER;
  }
  
  public boolean isWin(char player) {
    for(int[][] siegesReihe : SIEGESREIHEN) {
        if(spielerSiegtAufReihe(siegesReihe, player)) {
            return true;
        }
     }
     return false;
  }
  
  boolean spielerSiegtAufReihe(int[][] reihe, char sp) {
    char p0 = feld[reihe[0][0]][reihe[0][1]];
    char p1 = feld[reihe[1][0]][reihe[1][1]];
    char p2 = feld[reihe[2][0]][reihe[2][1]];
    return p0 == sp && p1 == sp && p2 == sp;
  }
  
  @Override
  public TicTacToeFeld clone() {
    TicTacToeFeld kopie = new TicTacToeFeld();
    for(int zeile = 0; zeile < 3; zeile ++) {
      for (int spalte = 0; spalte < 3; spalte++) {
        kopie.feld[zeile][spalte] = this.feld[zeile][spalte];
      }
    }
    return kopie;
  }
  
  public ArrayList<Point> getAlleZuege() {
    ArrayList<Point> alleZuege;
    alleZuege = new ArrayList<Point>();
    for(int zeile = 0; zeile < 3; zeile ++) {
      for (int spalte = 0; spalte < 3; spalte++) {
        if(NOPLAYER == this.feld[zeile][spalte]){
            alleZuege.add(new Point(zeile, spalte));
        }
      }
    }
    return alleZuege;
  }

  Point randomMove() {
    if(isFull()) return null; // no more moves
    ArrayList<Point> moeglicheZuege;
    moeglicheZuege = getAlleZuege();
    int randomPos = (int) (Math.random()
                            * moeglicheZuege.size());
    return moeglicheZuege.get(randomPos);
  }

  /**
   * Wer ist der gegner?
   */
  char other(char spieler) {
    if(PLAYER_X == spieler) return PLAYER_O;
    if(PLAYER_O == spieler) return PLAYER_X;
    return NOPLAYER;
  }
  
  @Override
  public String toString() {
    String s =  "+-------+";
    for(int zeile = 0; zeile < 3; zeile ++) {
       s = s + "\n|";
       for(int spalte = 0; spalte < 3; spalte ++)
       {
           char v = feld[zeile][spalte];
           if(NOPLAYER != v) {
             s = s + " " + v; 
           } else {
             s = s + "  ";
           }
       }
       s = s + " |";
       if(zeile < 2) {
           s = s + "\n|-------|";
       }
    }
    s = s +  "\n+-------+\n";
    return s;
  }

} // end of class TicTacToeFeld