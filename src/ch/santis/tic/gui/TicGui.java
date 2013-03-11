/**
 * @author philipp gressly freimann. 15. Feb. 2011.
 * CS IL10: Rekursion: Tic Tac Toe
 */ 


package ch.santis.tic.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

// project specific
import ch.santis.tic.TicTacToeFeld;
import static ch.santis.tic.TicTacToeFeld.*;
import ch.santis.tic.TicTacToeKI;

public class TicGui extends JFrame implements ActionListener {
    JButton[][]   buttons  = new JButton[3][3];

    TicTacToeFeld fld      = new TicTacToeFeld();
    TicTacToeKI   ki       = new TicTacToeKI();

    public static void main(String[] args) {
        new TicGui();
    }

    public TicGui(){
        super("TicTacToe");
        JPanel mp = new JPanel();
        makeButtons(mp);
        mp.setLayout(new GridLayout(3, 3));
        add(mp);
        setSize(180, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void makeButtons(JPanel mp) {
        for (int zeile = 0; zeile < 3; zeile++) {
            for (int spalte = 0; spalte < 3; spalte++) {
                JButton b = new JButton(".");
                buttons[zeile][spalte] = b;
                mp.add(b);
                b.addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        movePlayerThenKI(src);
        redrawAll();
    }

    private void movePlayerThenKI(JButton src) {
        for (int zeile = 0; zeile < 3; zeile++) {
            for (int spalte = 0; spalte < 3; spalte++) {
                behandleGeklicktenButton(src, zeile, spalte);
            } // end for spalte
        } // end for zeile
    } // movePlayerThenKI()

  
    private void behandleGeklicktenButton(JButton src, int zeile, int spalte) {
        // war das nicht der geklickte?
        if (buttons[zeile][spalte] != src) {
            return;
        }
        fld.set(zeile, spalte, PLAYER_O);
        if (fld.isWin(PLAYER_O)) {
            JOptionPane.showMessageDialog(this, "Mensch siegt!!");
        }
        if (!fld.isFull()) {
            Point p = ki.bestMove(fld, PLAYER_X);
            fld.set(p, PLAYER_X);
            if (fld.isWin(PLAYER_X)) {
                redrawAll();
                JOptionPane.showMessageDialog(this, "MiniMax siegt!!");
            }

        }

    } // end behandleGeklicktenButton()

    void redrawAll() {
        for (int zeile = 0; zeile < 3; zeile++) {
            for (int spalte = 0; spalte < 3; spalte++) {
                JButton b = buttons[zeile][spalte];
                if (NOPLAYER != fld.get(zeile, spalte)) {
                    b.setText("" + fld.get(zeile, spalte));
                    b.setEnabled(false);
                }
            }
        }
    } // end redrawAll
    
} // end of class TicGui