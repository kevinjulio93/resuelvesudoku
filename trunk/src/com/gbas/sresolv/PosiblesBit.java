package com.gbas.sresolv;

import java.util.Iterator;
import java.util.Enumeration;

public class PosiblesBit implements Iterable<Integer> {
    private static final int base[] = {
            1 << 0,
            1 << 1,
            1 << 2,
            1 << 3,
            1 << 4,
            1 << 5,
            1 << 6,
            1 << 7,
            1 << 8,
            1 << 9,
    };

    private static final int maxValues = 9;

    private int value;
    private static int fullValue;

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for (int k = maxValues-1; k >-1; k--) {
            sb.append((value & (1 << k)) > 0 ? "1" : "0");
        }
        return sb.toString();    
    }

    static {
        for (int k = 0; k < maxValues; k++) {
            fullValue += (1 << k);
        }
    }

    public PosiblesBit(boolean full) {
        if (full) {
            full();
        } else {
            clear();
        }
    }
    public PosiblesBit(int toSet) {
        setValue(toSet);
    }

    public void full() {
        value = fullValue;
    }

    public void clear() {
        value = 0;
    }

    public int getNumeroBits() {
        int count = 0;
        for (int k = 0; k < maxValues; k++) {
            if (((value >> k) & 1) > 0)
                count++;
        }
        return count;
    }

    public boolean existeValorPosible(int valor) {
        return (value & (1 << (valor - 1))) > 0;
    }

    public void removeValorPosible(int valor) {
        value &= (fullValue - (1 << (valor - 1)));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int rotacion = 0;

            public boolean hasNext() {
                while (rotacion < maxValues) {
                    if ((value & (1 << rotacion)) > 0) {
                        return true;
                    }
                    rotacion++;
                }
                return false;
            }

            public Integer next() {
                while (rotacion < maxValues) {
                    if ((value & (1 << rotacion)) > 0) {
                        return ++rotacion;
                    }
                    rotacion++;
                }
                return null;
            }

            public void remove() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }
}
