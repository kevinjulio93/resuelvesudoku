package com.gbas.sresolv.gui;

import com.gbas.sresolv.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SudokuFrame extends JFrame {
    public static final int ancho=600;
    public static final int alto=480;
    public static final int sudokuancho=9*40;
    public static final int sudokualto=9*40;

    Sudoku sudoku = new Sudoku();
    CommandPanel commandPanel;
    MessagePanel messagePanel;
    SudokuPanel spanel;
java.util.List<SudokuInitial> initialList =null;

    public CommandPanel getCommandPanel() {
        return commandPanel;
    }

    public MessagePanel getMessagePanel() {
        return messagePanel;
    }

    public SudokuPanel getSpanel() {
        return spanel;
    }

    public SudokuFrame(String title) throws HeadlessException {
        super(title);

        initialList =SudokuInitial.loadAll();

        spanel = new SudokuPanel(sudoku);
        spanel.setSize(sudokuancho, sudokualto);

        commandPanel = new CommandPanel(this);
        commandPanel.setSize(ancho - sudokuancho, alto-(alto - sudokualto));

        messagePanel = new MessagePanel();
        messagePanel.setSize(ancho, alto - sudokualto);

        spanel.setLocation(0, 0);
        commandPanel.setLocation(sudokuancho, 0);
        messagePanel.setLocation(0, sudokualto);

        JPanel pnFondo = new JPanel();
        pnFondo.setBackground(Color.white);
        getContentPane().add(pnFondo);
        pnFondo.setLayout(null);

        pnFondo.add(spanel);
        pnFondo.add(messagePanel);
        pnFondo.add(commandPanel);

        messagePanel.setBackground(Color.blue);
        commandPanel.setBackground(Color.black);
        spanel.setBackground(Color.yellow);


    }

    public List<SudokuInitial> getInitialList() {
        return initialList;
    }

    public void clear() {
        spanel.clear();
    }
}
