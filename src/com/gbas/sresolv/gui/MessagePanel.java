package com.gbas.sresolv.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: t50
 * Date: 13-mar-2010
 * Time: 19:03:49
 * To change this template use File | Settings | File Templates.
 */
public class MessagePanel extends JPanel {
    JTextArea ta = new JTextArea();
    public MessagePanel() {
        ScrollPane sc=new ScrollPane();
        sc.setSize(SudokuFrame.ancho, SudokuFrame.alto - SudokuFrame.sudokualto);
        sc.add(ta);
        add(sc);
        ta.setEditable(false);

    }
    public void addMessage(String text) {
        ta.setText(ta.getText() + text + "\n");
    }
}
