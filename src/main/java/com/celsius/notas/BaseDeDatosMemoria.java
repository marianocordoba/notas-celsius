package com.celsius.notas;

import java.util.ArrayList;
import java.util.List;

// Ctrl + . -> Agregar métodos sin implementar
public class BaseDeDatosMemoria implements IBaseDeDatos {
    private List<Nota> notas = new ArrayList<>();

    // Buscamos una nota usando búsqueda secuencial
    private int buscar(Nota nota) {
        // Comparamos el id de la nota pasada por parámetro con cada una de las notas de
        // la lista
        for (int i = 0; i < notas.size(); i++) {
            // notas.get(i) obtiene la nota en la posición i
            // notas.get(i).getId() obtiene el id de la nota en la posición i
            // equals compara dos strings
            // nota.getId() obtiene el id de la nota pasada como parámetro
            if (notas.get(i).getId().equals(nota.getId())) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void guardar(Nota nota) {
        int indice = buscar(nota);

        if (indice >= 0) {
            // La nota ya existe
            notas.set(indice, nota);
        } else {
            // La nota no existe
            notas.add(nota);
        }
    }

    @Override
    public void eliminar(Nota nota) {
        int indice = buscar(nota);
        notas.remove(indice);
    }

    @Override
    public List<Nota> listar() {
        return notas;
    }
}
