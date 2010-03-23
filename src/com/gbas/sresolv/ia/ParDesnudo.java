package com.gbas.sresolv.ia;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.Linea;

import java.util.Map;
import java.util.HashMap;

public class ParDesnudo extends BasicResolver {
    public Level getLevelResolver() {
        return Level.HARD;
    }

    public String getNameResolver() {
        return "Par Desnudo";
    }

    public boolean isCandidato() {
        return true;
    }

    public Map<Escaque, Integer> getSolucion(Linea[][] allOfLines) {
        Map<Escaque, Integer> mpSolucion = new HashMap<Escaque, Integer>();
        for (Linea[] block : allOfLines) {
            for (Linea l : block) {
                Map<Escaque, Integer> candidato = new HashMap<Escaque, Integer>();
                // Agrupamos 2 soluciones
                for (Escaque e : l) {
                    if (e.getValorPosible().getNumeroBits() == 2) {
                        candidato.put(e, e.getValorPosible().getValue());
                    }
                }

                // Hay más de 1 con 2 soluciones?
                if (candidato.size() > 1) {
                    Map<Integer, Integer> cuenta = new HashMap<Integer, Integer>();
                    // Agrupa y suma los valores iguales
                    for (Integer value : candidato.values()) {
                        if (cuenta.containsKey(value)) {
                            cuenta.put(value, cuenta.get(value) + 1);
                        } else {
                            cuenta.put(value, 1);
                        }
                    }

                    for (Map.Entry<Integer, Integer> em : cuenta.entrySet()) {
                        // Hay 2 resultados... pues par desnudo
                        if (em.getValue() == 2) {
                            for (Escaque e : l) {
                                if (e.getValorPosible().getValue() != em.getKey()) {
                                    for (int k = 0; k < 9; k++) {
                                        int mascara = (1 << k) & em.getKey();
                                        if (mascara > 0) {
                                            if ((e.getValorPosible().getValue() & mascara) > 0) {
                                                mergueToSolution(mpSolucion, e, k+1);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        return mpSolucion;
    }

}
