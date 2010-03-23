package com.gbas.sresolv.ia;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.Linea;
import com.gbas.sresolv.PosiblesBit;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class InterseccionDeRegion extends BasicResolver {
    public Level getLevelResolver() {
        return Level.MEDIUM;
    }

    public String getNameResolver() {
        return "Intersección de Región";
    }

    public boolean isCandidato() {
        return true;
    }

    public Map<Escaque, Integer> getSolucion(Linea[][] allOfLines) {
        Linea regiones[] = allOfLines[2];

        Map<Escaque, Integer> mpSolucion=new HashMap<Escaque, Integer>();
        for (Linea region : regiones) {
            for (Integer valor : region.getRestantes()) {
                Set<Escaque> enRegion = region.getConValor(valor);
                PosiblesBit numFilas = new PosiblesBit(false);
                PosiblesBit numColumnas = new PosiblesBit(false);
                for (Escaque e : enRegion) {
                    numFilas.setValue(numFilas.getValue() | ((1 << e.getFila())));
                    numColumnas.setValue(numColumnas.getValue() | ((1 << e.getColumna())));
                }
                if (numFilas.getNumeroBits() == 1) {
                    // Toda la fila que no esté en la región se quita el valor
                    final Escaque escaqueSample = enRegion.iterator().next();
                    int fila = escaqueSample.getFila();
                    int bloque = escaqueSample.getBloque();
                    for (Escaque e : allOfLines[0][fila]) {
                        if (e.getBloque() != bloque && e.getValorPosible().existeValorPosible(valor)) {
                            mergueToSolution(mpSolucion, e, valor);
                        }
                    }
                }
                if (numColumnas.getNumeroBits() == 1) {
                    // Toda la fila que no esté en la región se quita el valor
                    final Escaque escaqueSample = enRegion.iterator().next();
                    int columna = escaqueSample.getColumna();
                    int bloque = escaqueSample.getBloque();
                    for (Escaque e : allOfLines[1][columna]) {
                        if (e.getBloque() != bloque && e.getValorPosible().existeValorPosible(valor)) {
                            mergueToSolution(mpSolucion, e, valor);
                        }
                    }
                }
            }
        }

        return mpSolucion;
    }

}
