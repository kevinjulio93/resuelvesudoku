package com.gbas.sresolv.ia;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.Linea;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class UnicoOculto extends BasicResolver {
    public Level getLevelResolver() {
        return Level.EASY;
    }

    public String getNameResolver() {
        return "UnicoOculto";
    }

    public boolean isCandidato() {
        return false;
    }

    public Map<Escaque, Integer> getSolucion(Linea[][] allOfLines) {
        Map<Escaque, Integer> mpSolucion=new HashMap<Escaque, Integer>();

        // Cada uno de los 3 bloques
        for (Linea[] block : allOfLines) {
            // Cada una de las lineas
            for (Linea l : block) {
                // Cada uno de los valores restantes
                for (Integer values : l.getRestantes()) {
                    Set<Escaque> conValor = l.getConValor(values);
                    if (conValor.size() == 1) {
                        for (Escaque e : conValor) {
                            mpSolucion.put(e, values);

                        }
                    }
                }
            }
        }
        return mpSolucion;
    }
}