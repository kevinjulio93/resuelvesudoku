package com.gbas.sresolv;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Escaque {
    private int valorActual=0;
    private boolean asignadoPorUsuario=false;

    private Set<Linea>anuncios=new HashSet<Linea>();
//    private List<Integer> valoresPosibles=new ArrayList<Integer>();
    private PosiblesBit valorPosible;

    private int px;
    private int py;

    Sudoku papa;
    public Escaque(Sudoku papa, int px, int py) {
        this.papa=papa;
        this.px=px;
        this.py=py;
        
        clear();
    }

    public boolean isAsignadoPorUsuario() {
        return asignadoPorUsuario;
    }

    public void addListener(Linea l) {
    	anuncios.add(l);
    }
    public Set<Linea>getAnuncios() {
        return anuncios;
    }
    public boolean isAsignada() {
        return valorActual > 0;
    }
    
    public Escaque(Escaque original) {
        this.px = original.getColumna();
        this.py = original.getFila();
        this.valorActual = original.getValorActual();
        this.asignadoPorUsuario = original.asignadoPorUsuario;

        this.valorPosible = new PosiblesBit(true);
    }

    public int getFila() {
        return py;
    }
    public int getOffsetFila() {
        return px;
    }
    public int getColumna() {
        return px;
    }
    public int getOffsetColumna() {
        return py;
    }
    public int getBloque() {
        return (3 * (py / 3)) + (px / 3);
    }
    public int getOffsetBloque() {
        return (3*(py%3))+(px%3);
    }

    public void setValue(int value) throws SudokuLogicError {
    	setValue(value, true);
        if (value == 0) {
            setValue(value, false);
        }
    }
    public void setValue(int value, boolean update) throws SudokuLogicError {
        papa.updateData(this);

        valorActual = value;
        valorPosible.clear();

        if (update) {
            for (Linea l : anuncios) {
                l.anuncioCambio(this);
            }
        }
    }

    public int getValorActual() {
        return valorActual;
    }

//    public boolean isAsignadoPorUsuario() {
//        return asignadoPorUsuario;
//    }


    @Override
    public String toString() {
        return "Escaque{" +
                "valor=" + valorActual +
                ", [Fila=" + py +
                ", Columna=" + px +
                ", Bloque=" + getBloque() +
                "], Valores=" + valorPosible +
                '}';
    }

    public void clear() {
        valorActual=0;
        valorPosible = new PosiblesBit(true);
    }

    public PosiblesBit getValorPosible() {
        return valorPosible;
    }
}
