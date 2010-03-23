package com.gbas.sresolv;

import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class SudokuInitial implements Serializable {
    static final String nameFile = "sudoku_perst.sdk";

    public int dataInitial[][];
    public String name;
    public int dificultada;


    public SudokuInitial(int[][] dataInitial, String name, int dificultada) {
        this.dataInitial = dataInitial;
        this.name = name;
        this.dificultada = dificultada;
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<SudokuInitial> loadAll() {
        List<SudokuInitial> sudokuInitials = new ArrayList<SudokuInitial>();

        try {
            FileInputStream fis = new FileInputStream(nameFile);
            do {
                sudokuInitials.add(deserializa(fis));
            } while (fis.available() >= 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  
        }
        return sudokuInitials;
    }

    public static void saveAll(List<SudokuInitial> sudokus) {
        try {
            FileOutputStream fos = new FileOutputStream(nameFile);
            for (SudokuInitial si : sudokus) {
                serializa(si, fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializa(SudokuInitial sudokuInitial, FileOutputStream fos) throws IOException {
        ObjectOutputStream  salida = new ObjectOutputStream(fos);
        salida.writeObject(sudokuInitial);
    }

    public static SudokuInitial deserializa(FileInputStream fis) throws IOException, ClassNotFoundException {
        ObjectInputStream  entrada = new ObjectInputStream(fis);
        return (SudokuInitial) entrada.readObject();
    }

}
