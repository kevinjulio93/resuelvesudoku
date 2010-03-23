package com.gbas.sresolv.ia;

import com.gbas.sresolv.Escaque;
import com.gbas.sresolv.Linea;

import java.util.Map;

public interface Resolver {
    /**
     * Nivel de dificultad superado
     * @return El nivel según Level
     */
    public Level getLevelResolver();

    /**
     * Nombre del resolvedor
     * @return El nombre comun
     */
    public String getNameResolver();

    /**
     * Si retorna solución o candidato. Si es solución se establece la solución y se procesan los borrados automáticos, etc. Si
     * es candidato se eliminan las posibilidades.
     * @return true si es candidato, false si es solución
     */
    public boolean isCandidato();

    /**
     * Retorna el analisis. Un mapa de escaques con soluciones a establecer o candidatos a eliminar.
     * @param allOfLines, conjunto de líneas a analizar, por orden fila, columna y bloque
     * @return el mapa de escaques con valores a modificar.
     */
    public Map<Escaque, Integer> getSolucion(Linea[][] allOfLines);
}
