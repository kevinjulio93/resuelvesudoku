package com.gbas.sresolv.ia;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.Linea;
import com.gbas.sresolv.PosiblesBit;

import java.util.Map;
import java.util.HashMap;

public class CuartetoDesnudo extends BasicResolver {
    public Level getLevelResolver() {
        return Level.HARD;
    }

    public String getNameResolver() {
        return "Cuarteto desnudo";
    }

    public boolean isCandidato() {
        return true;
    }

    public Map<Escaque, Integer> getSolucion(Linea[][] allOfLines) {
        Map<Escaque, Integer> mpSolucion = new HashMap<Escaque, Integer>();

        for (Linea[] block : allOfLines) {
            for (Linea l : block) {
                if (l.getRestantes().getNumeroBits() > 4) {
                    for (Escaque e : l) {
                        final int numeroBitsE = e.getValorPosible().getNumeroBits();
                        if (numeroBitsE == 2 || numeroBitsE == 3 || numeroBitsE == 4) {
                            boolean startE2 = false;
                            for (Escaque e2 : l) {
                                final int numeroBitsE2 = e2.getValorPosible().getNumeroBits();
                                if (startE2 && (numeroBitsE2 == 2 || numeroBitsE2 == 3 || numeroBitsE2 == 4)) {
                                    boolean startE3 = false;
                                    for (Escaque e3 : l) {
                                        final int numeroBitsE3 = e3.getValorPosible().getNumeroBits();
                                        if (startE3 && (numeroBitsE3 == 2 || numeroBitsE3 == 3 || numeroBitsE3 == 4)) {
                                            boolean startE4 = false;
                                            for (Escaque e4 : l) {
                                                final int numeroBitsE4 = e4.getValorPosible().getNumeroBits();
                                                if (startE4 && (numeroBitsE4 == 2 || numeroBitsE4 == 3 || numeroBitsE4 == 4)) {
                                                    PosiblesBit pbit = new PosiblesBit(e3.getValorPosible().getValue() | e2.getValorPosible().getValue() | e.getValorPosible().getValue() | e4.getValorPosible().getValue());
                                                    if (pbit.getNumeroBits() == 4) {
                                                        int counter = 0;
                                                        for (Integer vl : pbit) {
                                                            if ((e.getValorPosible().existeValorPosible(vl) && e2.getValorPosible().existeValorPosible(vl)) ||
                                                                    (e.getValorPosible().existeValorPosible(vl) && e3.getValorPosible().existeValorPosible(vl)) ||
                                                                    (e.getValorPosible().existeValorPosible(vl) && e4.getValorPosible().existeValorPosible(vl)) ||
                                                                    (e2.getValorPosible().existeValorPosible(vl) && e3.getValorPosible().existeValorPosible(vl)) ||
                                                                    (e2.getValorPosible().existeValorPosible(vl) && e4.getValorPosible().existeValorPosible(vl)) ||
                                                                    (e3.getValorPosible().existeValorPosible(vl) && e4.getValorPosible().existeValorPosible(vl))
                                                                    ) {
                                                                counter++;
                                                            }
                                                        }
                                                        if (counter == pbit.getNumeroBits()) {
                                                            // OK cuarteto desnudo encontrado
                                                            for (Integer vl : pbit) {
                                                                for (Escaque e5 : l) {
                                                                    if (e5 != e && e5 != e2 && e5 != e3 && e5 != e4) {
                                                                        int mascara = (1 << vl - 1);
                                                                        if ((e5.getValorPosible().getValue() & mascara) > 0) {
                                                                            mergueToSolution(mpSolucion, e5, vl);
                                                                        }

                                                                    }
                                                                }
                                                            }
                                                        }

                                                    }
                                                } else {
                                                    if (e4 == e3) {
                                                        startE4 = true;
                                                    }
                                                }

                                            }
                                        } else {
                                            if (e3 == e2) {
                                                startE3 = true;
                                            }
                                        }
                                    }
                                } else {
                                    if (e2 == e) {
                                        startE2 = true;
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