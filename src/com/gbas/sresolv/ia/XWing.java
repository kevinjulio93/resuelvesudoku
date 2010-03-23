package com.gbas.sresolv.ia;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.Linea;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class XWing extends BasicResolver {
    public Level getLevelResolver() {
        return Level.HARD;
    }

    public String getNameResolver() {
        return "X-Wing";
    }

    public boolean isCandidato() {
        return true;
    }

    public Map<Escaque, Integer> getSolucion(Linea[][] allOfLines) {
        Map<Escaque, Integer> mpSolucion = new HashMap<Escaque, Integer>();
        for (int clinea = 0; clinea < 2; clinea++) {
            Linea[] lineas = allOfLines[clinea];
            for (Linea linea : lineas) {
                for (Integer v : linea.getRestantes()) {
                    final Set<Escaque> escaques = linea.getConValor(v);
                    if (escaques.size() == 2) {
                        boolean nextLine=false;
                        for (Linea linea2 : lineas) {
                            if (nextLine && linea2 != linea) {
                                final Set<Escaque> escaques2 = linea2.getConValor(v);
                                if (escaques2.size() == 2) {
                                    final Iterator<Escaque> iterator = escaques.iterator();
                                    Escaque e1 = iterator.next();
                                    Escaque e2 = iterator.next();
                                    final Iterator<Escaque> iterator1 = escaques2.iterator();
                                    Escaque e21 = iterator1.next();
                                    Escaque e22 = iterator1.next();

                                    if (clinea == 0) {
                                        // Check Columnas
                                        if ((e2.getColumna() == e22.getColumna() && e1.getColumna() == e21.getColumna())||
                                                (e2.getColumna() == e21.getColumna() && e1.getColumna() == e22.getColumna())){
                                            // Borramos valores de columnas
                                           // for (Escaque e:)

                                        }
                                    } else {
                                        // Check Filas
                                        if ((e2.getFila() == e22.getFila() && e1.getFila() == e21.getFila())||
                                                (e2.getFila() == e21.getFila() && e1.getFila() == e22.getFila())){
                                            
                                        }

                                    }
                                }

                            } else {
                                if (linea == linea2) {
                                    nextLine=true;
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