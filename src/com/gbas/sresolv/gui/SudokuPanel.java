package com.gbas.sresolv.gui;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.Linea;
import com.gbas.sresolv.Sudoku;

import javax.swing.*;
import java.awt.*;

public class SudokuPanel extends JPanel {
    Sudoku sudoku;
    EscaquePanel escaques[][] = new EscaquePanel[9][9];

    public SudokuPanel(Sudoku sudoku) {
        super();
        this.sudoku = sudoku;

        setLayout(new GridLayout(9, 9));
        
        Linea filas[] = sudoku.getFilas();
        int cfila=0;
        for (Linea fila : filas) {
            for (int columna = 0; columna < 9; columna++) {
                Escaque e = fila.getEscateOffset(columna);

                final EscaquePanel panel = new EscaquePanel(e);
                int top = cfila == 0? 4:cfila%3==0 ? 2:1;
                int left = columna==0? 4:columna%3==0 ?2:1;
                int bottom = cfila == 8? 4:cfila%3==2 ? 2:1;
                int right = columna==8? 4:columna%3==2 ?2:1;
                panel.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.black));
                add(panel);
                escaques[cfila][columna]=panel;
            }
            cfila++;
        }

    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public void clear() {
        for (EscaquePanel es[] : escaques) {
            for (EscaquePanel e : es) {
                e.clear();
            }
        }
    }
}
