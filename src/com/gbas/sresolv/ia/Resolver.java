package com.gbas.sresolv.ia;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.Linea;

import java.util.Map;

public interface Resolver {
    /**
     * Nivel de dificultad superado
     * @return El nivel seg�n Level
     */
    public Level getLevelResolver();

    /**
     * Nombre del resolvedor
     * @return El nombre comun
     */
    public String getNameResolver();

    /**
     * Si retorna soluci�n o candidato. Si es soluci�n se establece la soluci�n y se procesan los borrados autom�ticos, etc. Si
     * es candidato se eliminan las posibilidades.
     * @return true si es candidato, false si es soluci�n
     */
    public boolean isCandidato();

    /**
     * Retorna el analisis. Un mapa de escaques con soluciones a establecer o candidatos a eliminar.
     * @param allOfLines, conjunto de l�neas a analizar, por orden fila, columna y bloque
     * @return el mapa de escaques con valores a modificar.
     */
    public Map<Escaque, Integer> getSolucion(Linea[][] allOfLines);
}
