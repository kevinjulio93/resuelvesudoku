package com.gbas.sresolv;

import java.util.*;

public class Linea implements Iterable<Escaque> {
	public static final int LINEA_UNDEFINED = 0;
	public static final int LINEA_FILA = 1;
	public static final int LINEA_COLUMNA = 2;
	public static final int LINEA_BLOQUE = 3;

//	private List<Integer>numerosRestantes;
    PosiblesBit restantes;
	
	private Escaque[] escaques=new Escaque[9];
	private int tipoLinea=LINEA_UNDEFINED;
	
	public Linea(int tipoLinea) {
		this.tipoLinea=tipoLinea;

        clear();
	}

    public int getTipoLinea() {
        return tipoLinea;
    }

    public Escaque[] getEscaques() {
		return escaques;
	}
	public Escaque getEscateOffset(int position) {
		return escaques[position];
	}
	
	public void addEscaque(Escaque escaque, int posicion) {
		escaques[posicion]=escaque;
		
		escaque.addListener(this);
	}

    public PosiblesBit getRestantes() {
        return restantes;
    }

    public void anuncioCambio(Escaque e) throws SudokuLogicError {
		if (restantes.existeValorPosible(e.getValorActual())) {
            restantes.removeValorPosible(e.getValorActual());
            for (Escaque e2 : escaques) {
                if (e2 != e && !e2.isAsignada()) {
                    if (e2.getValorPosible().existeValorPosible(e.getValorActual())) {
                        e2.getValorPosible().removeValorPosible(e.getValorActual());
                    }
                }
            }
        } else {
			throw new SudokuLogicError("Error se ha asignado un númeor que no existia");
		}
		
	}

    public void clear() {
        restantes=new PosiblesBit(true);
    }

    public Iterator<Escaque> iterator() {
        return new Iterator<Escaque>() {
            int position=0;

            public boolean hasNext() {
                for (; position < 9; position++) {
                    if (!escaques[position].isAsignada()) {
                        return true;
                    }
                }
                return false;
            }

            public Escaque next() {
                return escaques[position++];
            }

            public void remove() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }

    public Set<Escaque> getConValor(int valor) {
        Set<Escaque>conValor=new HashSet<Escaque>();

        for (Escaque e : this) {
            if (e.getValorPosible().existeValorPosible(valor)) {
                conValor.add(e);
            }
        }
        return conValor;
    }
}
