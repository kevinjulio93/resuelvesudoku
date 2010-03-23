package com.gbas.sresolv.ia;

import com.gbas.sresolv.Escaque;

import java.util.Map;

public abstract class BasicResolver implements Resolver {
    public boolean isSolution() {
        return !isCandidato();
    }
    protected void mergueToSolution(Map<Escaque, Integer> mpSolucion, Escaque e, Integer valor) {
        if (mpSolucion.containsKey(e)) {
            int bits=mpSolucion.get(e)|((1<<(valor-1)));
            mpSolucion.put(e, bits);
        } else {
            int bits=(1<<(valor-1));
            mpSolucion.put(e, bits);
        }
    }

    @Override
    public String toString() {
        return getNameResolver();
    }
}
