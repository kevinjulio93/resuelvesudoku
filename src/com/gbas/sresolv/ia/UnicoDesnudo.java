package com.gbas.sresolv.ia;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.Linea;

import java.util.Map;
import java.util.HashMap;

public class UnicoDesnudo extends BasicResolver {
    public Level getLevelResolver() {
        return Level.EASY;
    }

    public String getNameResolver() {
        return "UnicoDesnudo";
    }

    public boolean isCandidato() {
        return false;
    }

    public Map<Escaque, Integer> getSolucion(Linea[][] allOfLines) {
        Map<Escaque, Integer> mpSolucion=new HashMap<Escaque, Integer>();
        Linea l[] = allOfLines[0];
        for (Linea l2 : l) {
            for (Escaque e : l2) {
                if (e.getValorPosible().getNumeroBits() == 1) {
                    mpSolucion.put(e, e.getValorPosible().iterator().next());
                }
            }
        }
        return mpSolucion;
    }
}
