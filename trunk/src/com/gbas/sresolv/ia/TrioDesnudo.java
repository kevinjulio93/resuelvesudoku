package com.gbas.sresolv.ia;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.Linea;
import com.gbas.sresolv.PosiblesBit;

import java.util.Map;
import java.util.HashMap;

public class TrioDesnudo extends BasicResolver {
    public Level getLevelResolver() {
        return Level.HARD;
    }

    public String getNameResolver() {
        return "Trio desnudo";
    }

    public boolean isCandidato() {
        return true;
    }

    public Map<Escaque, Integer> getSolucion(Linea[][] allOfLines) {
        Map<Escaque, Integer> mpSolucion = new HashMap<Escaque, Integer>();

        for (Linea[] block : allOfLines) {
            for (Linea l : block) {
                if (l.getRestantes().getNumeroBits() > 3) {
                    for (Escaque e : l) {
                        final int numeroBitsE = e.getValorPosible().getNumeroBits();
                        if (numeroBitsE == 2 || numeroBitsE == 3) {
                            boolean startE2=false;
                            for (Escaque e2 : l) {
                                final int numeroBitsE2 = e2.getValorPosible().getNumeroBits();
                                if (startE2 && (numeroBitsE2 == 2 || numeroBitsE2 == 3)) {
                                    boolean startE3=false;
                                    for (Escaque e3 : l) {
                                        final int numeroBitsE3 = e3.getValorPosible().getNumeroBits();
                                        if (startE3 && (numeroBitsE3 == 2 || numeroBitsE3 == 3)) {
                                            PosiblesBit pbit = new PosiblesBit(e3.getValorPosible().getValue() | e2.getValorPosible().getValue() | e.getValorPosible().getValue());
                                            if (pbit.getNumeroBits() == 3) {
                                                for (Integer vl : pbit) {
                                                    if ((e.getValorPosible().existeValorPosible(vl) && e2.getValorPosible().existeValorPosible(vl)) ||
                                                            (e.getValorPosible().existeValorPosible(vl) && e3.getValorPosible().existeValorPosible(vl)) ||
                                                            (e3.getValorPosible().existeValorPosible(vl) && e2.getValorPosible().existeValorPosible(vl))) {
                                                       // OK trio desnudo encontrado
                                                        for (Escaque e4 : l) {
                                                            if (e4 != e && e4 != e2 && e4 != e3) {
                                                                int mascara = (1 << vl-1);
                                                                if ((e4.getValorPosible().getValue() & mascara) > 0) {
                                                                    mergueToSolution(mpSolucion, e4, vl);
                                                                }

                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            if (e3==e2) {
                                                startE3=true;
                                            }
                                        }
                                    }
                                } else {
                                    if (e2 == e) {
                                        startE2=true;
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
