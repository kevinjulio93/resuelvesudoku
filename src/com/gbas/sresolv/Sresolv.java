package com.gbas.sresolv;

import com.gbas.sresolv.gui.SudokuFrame;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class Sresolv {
    public static int baseEasy[][] = {
        {0, 0, 0, 0, 0, 0, 2, 0, 0},
        {0, 9, 0, 5, 0, 0, 8, 0, 6},
        {4, 0, 0, 2, 7, 0, 0, 0, 0},
        {0, 0, 8, 1, 9, 3, 0, 0, 7},
        {0, 1, 0, 0, 0, 0, 0, 4, 0},
        {3, 0, 0, 6, 4, 8, 5, 0, 0},
        {0, 0, 0, 0, 6, 9, 0, 0, 5},
        {8, 0, 4, 0, 0, 7, 0, 9, 0},
        {0, 0, 7, 0, 0, 0, 0, 0, 0},
};
    public static int baseMedium[][] = {
        {0, 0, 0, 5, 0, 0, 8, 0, 0},
        {0, 0, 8, 4, 0, 2, 0, 6, 7},
        {0, 2, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 9, 0, 0, 2, 0, 3},
        {0, 9, 0, 0, 0, 0, 0, 4, 0},
        {8, 0, 2, 0, 0, 6, 0, 0, 0},
        {0, 0, 9, 0, 0, 0, 0, 1, 0},
        {4, 7, 0, 8, 0, 3, 9, 0, 0},
        {0, 0, 6, 0, 0, 7, 0, 0, 0},
};
    public static int baseMedium2[][] = {
        {6, 0, 0, 0, 0, 0, 0, 2, 0},
        {0, 0, 0, 6, 0, 0, 8, 0, 0},
        {2, 0, 0, 0, 7, 0, 5, 0, 3},
        {0, 0, 7, 0, 1, 0, 3, 0, 0},
        {4, 0, 0, 7, 0, 3, 0, 0, 8},
        {0, 0, 1, 0, 2, 0, 6, 0, 0},
        {9, 0, 4, 0, 3, 0, 0, 0, 7},
        {0, 0, 5, 0, 0, 9, 0, 0, 0},
        {0, 8, 0, 0, 0, 0, 0, 0, 9},
};
    public static int baseHard[][] = {
        {7, 0, 5, 0, 4, 0, 0, 8, 0},
        {0, 0, 0, 2, 8, 0, 0, 0, 3},
        {0, 1, 0, 7, 0, 0, 4, 0, 0},
        {0, 0, 0, 0, 3, 0, 2, 0, 8},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {6, 0, 3, 0, 7, 0, 0, 0, 0},
        {0, 0, 4, 0, 0, 7, 0, 5, 0},
        {8, 0, 0, 0, 5, 9, 0, 0, 0},
        {0, 9, 0, 0, 6, 0, 1, 0, 4},
};
    public static int baseHard2[][] = {
            {0, 4, 0, 0, 8, 0, 7, 0, 0},
            {0, 9, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 8, 0, 0, 9, 3, 0, 0},
            {0, 7, 5, 3, 0, 0, 0, 0, 4},
            {0, 0, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 0, 0, 5, 9, 7, 0},
            {0, 0, 9, 5, 0, 0, 4, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 6, 0},
            {0, 0, 4, 0, 6, 0, 0, 5, 0},
    };
    public static int baseHard3[][] = {
            {0, 0, 0, 9, 0, 0, 0, 0, 0},
            {3, 0, 5, 0, 0, 0, 8, 0, 0},
            {0, 0, 0, 0, 0, 5, 6, 4, 9},
            {0, 4, 0, 0, 2, 0, 0, 0, 0},
            {8, 0, 7, 0, 0, 0, 1, 0, 2},
            {0, 0, 0, 0, 8, 0, 0, 7, 0},
            {7, 5, 2, 4, 0, 0, 0, 0, 0},
            {0, 0, 9, 0, 0, 0, 2, 0, 5},
            {0, 0, 0, 0, 0, 9, 0, 0, 0},
    };

    public static void main(String[] args) {
        SudokuFrame frame = new SudokuFrame("Sudoku Resolutor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SudokuFrame.ancho, SudokuFrame.alto);
        frame.setVisible(true);

/*
        List<SudokuInitial> initialList=new ArrayList<SudokuInitial>();
        initialList.add(new SudokuInitial(baseEasy, "Easy1", 1));
        initialList.add(new SudokuInitial(baseMedium, "Medium1", 2));
        initialList.add(new SudokuInitial(baseMedium2, "Medium2", 2));
        initialList.add(new SudokuInitial(baseHard, "Hard1", 3));
        initialList.add(new SudokuInitial(baseHard2, "Hard2", 4));
        initialList.add(new SudokuInitial(baseHard3, "Hard3", 5));

        SudokuInitial.saveAll(initialList);
*/
    }

}
