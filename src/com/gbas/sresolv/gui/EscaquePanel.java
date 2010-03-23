package com.gbas.sresolv.gui;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.SudokuLogicError;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EscaquePanel extends JPanel {
    final Font fontPosibles = new Font(Font.MONOSPACED, Font.PLAIN, 10);
    final Font fontAsignado = new Font(Font.MONOSPACED, Font.BOLD, 20);
    Escaque escaque;
    int lastValue;
    int lastValorPosible;

    public EscaquePanel(final Escaque escaque) {
        clear();
        this.escaque = escaque;

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (EscaquePanel.this.escaque.isAsignada()) {
                    if (EscaquePanel.this.escaque.isAsignadoPorUsuario()) {

                    }
                } else {
                    String valor = JOptionPane.showInputDialog("Introduzca valor 1-9");
                    if (valor != null) {
                        try {
                            int v = Integer.parseInt(valor);
                            if (v >= 1 && v <= 9) {
                                EscaquePanel.this.escaque.setValue(v);
                            }
                        } catch (SudokuLogicError sudokuLogicError) {
                            JOptionPane.showMessageDialog(EscaquePanel.this, "Valor invalido", "Atencion", JOptionPane.ERROR);
                            try {
                                EscaquePanel.this.escaque.setValue(0);
                            } catch (SudokuLogicError sudokuLogicError1) {
                                sudokuLogicError1.printStackTrace();
                            }
                        }
                        //repaint();
                        EscaquePanel.this.getParent().repaint();
                    }
                }
            }
        });
//        setLayout(new GridLayout(3,3));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (escaque.isAsignada()) {
            if (lastValue != escaque.getValorActual()) {
                lastValue = escaque.getValorActual();
                g.setColor(Color.gray);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            g.setFont(fontAsignado);
            if (escaque.isAsignadoPorUsuario()) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.black);
            }
            g.drawString("" + escaque.getValorActual(), 14, 28);
        } else {
            g.setFont(fontPosibles);
            for (int k = 1; k < 10; k++) {
                g.setColor(escaque.getValorPosible().existeValorPosible(k) ? Color.green : Color.white);
                int bit = 1 << (k - 1);
                if ((lastValorPosible & bit) != (escaque.getValorPosible().getValue() & bit)) {
                    g.setColor(Color.GRAY);
                }
                g.drawString("" + k, 4 + (40 / 3) * ((k - 1) % 3), 10 + (40 / 3) * ((k - 1) / 3));
            }
            lastValorPosible = escaque.getValorPosible().getValue();

        }
    }

    public void clear() {
        this.lastValue = 0;
        this.lastValorPosible = 511;
    }
}
