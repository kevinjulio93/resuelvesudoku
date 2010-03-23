package com.gbas.sresolv.gui;

import com.gbas.sresolv.*;
import com.gbas.sresolv.ia.Resolver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Arrays;

public class CommandPanel extends JPanel {

    public CommandPanel(final SudokuFrame sudokuFrame) {
        final Sudoku s=sudokuFrame.getSpanel().getSudoku();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        final JComboBox lbResolver=new JComboBox(s.getResolvedoresList());
        lbResolver.setSize(lbResolver.getWidth(), 32);
        
       // lbResolver.addItem(s.getResolvedoresList());
        JButton btResolution = new JButton("Resolver");
        btResolution.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final Resolver resolver = (Resolver) lbResolver.getSelectedItem();
                sudokuFrame.getMessagePanel().addMessage("Resolviendo según "+resolver.getNameResolver());

                Map<Escaque, Integer> mpSolution = resolver.getSolucion(s.getAllOfLines());

                try {
                    if (!resolver.isCandidato()) {
                        for (Map.Entry<Escaque, Integer> me : mpSolution.entrySet()) {
                            sudokuFrame.getMessagePanel().addMessage("" + me.getKey() + " unico valor de "+me.getValue());
                            me.getKey().setValue(me.getValue());
                        }
                    } else {
                        for (Map.Entry<Escaque, Integer> me : mpSolution.entrySet()) {
                            PosiblesBit pbit = new PosiblesBit(true);
                            pbit.setValue(me.getValue());
                            for (Integer value : pbit) {
                                sudokuFrame.getMessagePanel().addMessage("" + me.getKey() + " eliminada opcion  "+value);
                                me.getKey().getValorPosible().removeValorPosible(value);
                            }
                        }
                    }
                } catch (SudokuLogicError sudokuLogicError) {
                    sudokuLogicError.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                sudokuFrame.getSpanel().repaint();
            }
        });

        add(lbResolver);
        add(btResolution);

        final JComboBox lbCargador=new JComboBox(sudokuFrame.getInitialList().toArray());
        lbCargador.setSize(lbCargador.getWidth(), 32);
        JButton btCargar = new JButton("Cargar");
        JButton btNuevo = new JButton("Nuevo");
        JButton btGuardar = new JButton("Guardar");
        JButton btBorrar = new JButton("Borrar");

        add(lbCargador);
        add(btCargar);
        add(btNuevo);
        add(btGuardar);
        add(btBorrar);

        btCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final SudokuInitial sudokuInitial= (SudokuInitial) lbCargador.getSelectedItem();
                s.clear();
                sudokuFrame.clear();
                initMotor(sudokuInitial.dataInitial, s);
                sudokuFrame.repaint();
            }
        });
        btNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                s.clear();
                sudokuFrame.clear();
                sudokuFrame.repaint();
            }
        });

        btBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final SudokuInitial sudokuInitial= (SudokuInitial) lbCargador.getSelectedItem();
                if (JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(CommandPanel.this, "Seguro que desea borrar el sudoku " + sudokuInitial.name)) {
                    sudokuFrame.getInitialList().remove(sudokuInitial);
                    SudokuInitial.saveAll(sudokuFrame.getInitialList());

                    lbCargador.removeAllItems();
                    for (SudokuInitial si : sudokuFrame.getInitialList()) {
                        lbCargador.addItem(si);
                    }
                }
            }
        });

        btGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name=JOptionPane.showInputDialog("Nombre del sudoku");
                if (name != null && name.length() > 0) {
                    Object[] possibleValues = { "Fácil", "Medio", "Difícil" };
                    Object selectedValue = JOptionPane.showInputDialog(null, "Elige nivel", "Nivel de dificultad", JOptionPane.INFORMATION_MESSAGE, null,
                            possibleValues, possibleValues[1]);


                    lbCargador.removeAllItems();
                    SudokuInitial sudokuInitial = new SudokuInitial(actualValues(s), name, Arrays.binarySearch(possibleValues, selectedValue));
                    sudokuFrame.getInitialList().add(sudokuInitial);
                    SudokuInitial.saveAll(sudokuFrame.getInitialList());
                    for (SudokuInitial si : sudokuFrame.getInitialList()) {
                        lbCargador.addItem(si);
                    }
                    sudokuFrame.repaint();

                }
            }
        });
    }
    public void initMotor(int values[][], Sudoku sudoku) {
        for (int ypos = 0; ypos < 9; ypos++) {
            for (int xpos = 0; xpos < 9; xpos++) {
                //Integer defValue = new Integer(0);
                int valor =values[ypos][xpos];
                if (valor > 0) {
                    sudoku.setValueEnFila(ypos, xpos, valor);
                }
            }
        }
    }

    public int[][] actualValues(Sudoku sudoku) {
        int[][] actualSudoku = new int[9][9];
        int fila=0;
        for (Linea l : sudoku.getFilas()) {
            int columna=0;
            for (Escaque e : l.getEscaques()) {
                actualSudoku[fila][columna++] = e.getValorActual();
            }
            fila++;
        }
        return actualSudoku;

    }
}
