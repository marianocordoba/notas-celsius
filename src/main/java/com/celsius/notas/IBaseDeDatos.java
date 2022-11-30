package com.celsius.notas;

import java.util.List;

public interface IBaseDeDatos {
    void guardar(Nota nota);

    void eliminar(Nota nota);

    List<Nota> listar();
}
