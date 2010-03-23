package com.gbas.sresolv;

import com.gbas.sresolv.ia.*;

public class Sudoku {
    private Linea[] filas = new Linea[9];
    private Linea[] columnas = new Linea[9];
    private Linea[] bloques = new Linea[9];
    private Linea[][] allOfLines = new Linea[3][9];

    private Resolver[] resolvedoresList=new Resolver[]{
        new UnicoDesnudo(),
        new UnicoOculto(),
        new InterseccionDeLinea(),
        new InterseccionDeRegion(),
        new ParDesnudo(),
        new TrioDesnudo(),
        new CuartetoDesnudo(),
        new ParOculto(),
    };

    int casillasToResolver=9*9;
    int opcionesToResolver=9*9*9;

    public Resolver[] getResolvedoresList() {
        return resolvedoresList;
    }

    public Linea[] getFilas() {
        return filas;
    }

    public Linea[][] getAllOfLines() {
        return allOfLines;
    }

    public Sudoku() {
        for (int linea = 0; linea < 9; linea++) {
            filas[linea] = new Linea(Linea.LINEA_FILA);
            columnas[linea] = new Linea(Linea.LINEA_COLUMNA);
            bloques[linea] = new Linea(Linea.LINEA_BLOQUE);
        }
        allOfLines[0] = filas;
        allOfLines[1] = columnas;
        allOfLines[2] = bloques;

        for (int ypos = 0; ypos < 9; ypos++) {
            for (int xpos = 0; xpos < 9; xpos++) {
                //Integer defValue = new Integer(0);
                Escaque e = new Escaque(this, xpos, ypos);

                filas[e.getFila()].addEscaque(e, e.getOffsetFila());
                columnas[e.getColumna()].addEscaque(e, e.getOffsetColumna());
                bloques[e.getBloque()].addEscaque(e, e.getOffsetBloque());
            }
        }
        clear();
    }

    public void setValueEnFila(int fila, int offset, int value) {
        try {
            filas[fila].getEscateOffset(offset).setValue(value);
        } catch (SudokuLogicError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(90);
        for (int ypos = 0; ypos < 9; ypos++) {
            if (ypos == 0) {
                sb.append("-------------\n");
            }
            for (int xpos = 0; xpos < 9; xpos++) {
                if (xpos == 0) {
                    sb.append("|");
                }
                Escaque i = filas[ypos].getEscateOffset(xpos);
                if (i.getValorActual() > 0) {
                    sb.append(i.getValorActual());
                } else {
                    sb.append(" ");
                    //System.out.println("Escaque "+i.toString());
                }

                if (xpos % 3 == 2) {
                    sb.append("|");
                }
                if (xpos == 8) {
                    sb.append("\n");
                }
            }
            if (ypos % 3 == 2) {
                sb.append("-------------\n");
            }
        }
        return sb.toString();
    }

    public void clear() {
        for (int fila = 0; fila < 9; fila++) {
            filas[fila].clear();
            columnas[fila].clear();
            bloques[fila].clear();
            for (int offset = 0; offset < 9; offset++) {
                filas[fila].getEscateOffset(offset).clear();
            }
        }


    }

    public void updateData(Escaque escaque) {
        casillasToResolver--;
        opcionesToResolver -= escaque.getValorPosible().getNumeroBits();
    }
}
