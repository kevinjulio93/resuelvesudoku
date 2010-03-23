package com.gbas.sresolv.ia;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.Linea;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class TrioOculto extends BasicResolver {
    public Level getLevelResolver() {
        return Level.HARD;
    }

    public String getNameResolver() {
        return "Trio Oculto";
    }

    public boolean isCandidato() {
        return true;
    }

    public Map<Escaque, Integer> getSolucion(Linea[][] allOfLines) {
        Map<Escaque, Integer> mpSolucion = new HashMap<Escaque, Integer>();
        for (Linea[] block : allOfLines) {
            for (Linea l : block) {
                if (l.getRestantes().getNumeroBits() > 3) {
                    Map<Integer, Set<Escaque>> candidato = new HashMap<Integer, Set<Escaque>>();
                    for (Integer solucionBuscada : l.getRestantes()) {
                        Set<Escaque> lista = l.getConValor(solucionBuscada);
                        if (lista.size() == 2 || lista.size() == 3) {
                            candidato.put(solucionBuscada, lista);
                        }
                    }
/*                    for (Map.Entry<Integer, Set<Escaque>> opt : candidato.entrySet()) {
                        boolean post = false;
                        for (Map.Entry<Integer, Set<Escaque>> opt2 : candidato.entrySet()) {
                            if (post && opt2 != opt) {
                                final Iterator<Escaque> iterator = opt.getValue().iterator();
                                Escaque e1 = iterator.next();
                                Escaque e2 = iterator.next();
                                if (opt2.getValue().contains(e1) && opt2.getValue().contains(e2)) {
                                    // Ok Par oculto cumplido
                                    for (int k = 0; k < 9; k++) {
                                        if (k + 1 != opt.getKey() && k + 1 != opt2.getKey()) {
                                            int mascara = (1 << k);
                                            if ((e1.getValorPosible().getValue() & mascara) > 0) {
                                                mergueToSolution(mpSolucion, e1, k + 1);
                                            }
                                            if ((e2.getValorPosible().getValue() & mascara) > 0) {
                                                mergueToSolution(mpSolucion, e2, k + 1);
                                            }
                                        }
                                    }
                                }

                            } else {
                                if (opt == opt2) {
                                    post = true;
                                }
                            }
                        }
                    }*/
                }
            }
        }
        return mpSolucion;
    }

}