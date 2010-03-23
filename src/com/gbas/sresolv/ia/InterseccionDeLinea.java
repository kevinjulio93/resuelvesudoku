package com.gbas.sresolv.ia;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.Linea;
import com.gbas.sresolv.PosiblesBit;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class InterseccionDeLinea extends BasicResolver {
    public Level getLevelResolver() {
        return Level.MEDIUM;
    }

    public String getNameResolver() {
        return "Intersección de Línea";
    }

    public boolean isCandidato() {
        return true;
    }

    public Map<Escaque, Integer> getSolucion(Linea[][] allOfLines) {
        Map<Escaque, Integer> mpSolucion=new HashMap<Escaque, Integer>();
        // Ciclo sobre columnas y filas (líneas)
        for (int cLinea = 0; cLinea < 2; cLinea++) {
            Linea lineas[] = allOfLines[cLinea];
            for (Linea linea : lineas) {
                // Mira valroes restantes
                for (Integer valor : linea.getRestantes()) {
                    Set<Escaque> enLinea = linea.getConValor(valor);

                    PosiblesBit numRegiones = new PosiblesBit(false);
                    // Comprueba si soluciones están todas en región
                    for (Escaque e : enLinea) {
                        numRegiones.setValue(numRegiones.getValue() | ((1 << e.getBloque())));
                    }

                    if (numRegiones.getNumeroBits() == 1) {
                        // Quita en la región todas soluciones que no esten en linea
                        final Escaque escaqueSample = enLinea.iterator().next();
                        int regionSample = escaqueSample.getBloque();

                        for (Escaque e : allOfLines[2][regionSample]) {
                            if (!e.isAsignada() && e.getValorPosible().existeValorPosible(valor) && !enLinea.contains(e)) {
                                mergueToSolution(mpSolucion, e, valor);
                            }
                        }
                    }
                }
            }
        }

        return mpSolucion;
    }

}