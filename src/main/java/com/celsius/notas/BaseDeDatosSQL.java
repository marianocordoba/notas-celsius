package com.celsius.notas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// BaseDeDatosSQL.java
public class BaseDeDatosSQL implements IBaseDeDatos {

  private Connection connection;

  public BaseDeDatosSQL() {
    // try-catch evita la propagación de un error
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:data.db");
      connection.createStatement().execute(
          "CREATE TABLE IF NOT EXISTS notas (id TEXT PRIMARY KEY, titulo TEXT, contenido TEXT)");
    } catch (SQLException e) {
      // Si hay un error, lanzo una excepción con un mensaje
      // que me permita identificar el problema.
      throw new RuntimeException("Error al conectar con la base de datos");
    }
  }

  @Override
  public void guardar(Nota nota) {
    try {
      // UPSERT
      // Inserta un registro o lo reemplaza si ya existe
      // uno con el mismo id
      PreparedStatement statement = connection.prepareStatement(
          "INSERT OR REPLACE INTO notas VALUES (?,?,?)");
      statement.setString(1, nota.getId());
      statement.setString(2, nota.getTitulo());
      statement.setString(3, nota.getContenido());
      statement.execute();
    } catch (SQLException e) {
      throw new RuntimeException("Error al guardar una nota");
    }
  }

  @Override
  public void eliminar(Nota nota) {
    try {
      // MUY IMPORTANTE
      // Incluir el WHERE en la consulta para evitar
      // eliminar todos los registros
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM notas WHERE id = ?");
      statement.setString(1, nota.getId());
      statement.execute();
    } catch (SQLException e) {
      throw new RuntimeException("Error al eliminar la nota");
    }
  }

  @Override
  public List<Nota> listar() {
    try {
      // SELECT * FROM = seleccionar todos los campos
      // WHERE = filtrar resultados
      // ORDER BY = ordenar
      // LIMIT = limitar cantidad de resultados
      ResultSet rs = connection.createStatement().executeQuery(
          "SELECT * FROM notas");
      List<Nota> notas = new ArrayList<>();

      while (rs.next()) {
        Nota nota = new Nota(
            rs.getString("id"),
            rs.getString("titulo"),
            rs.getString("contenido"));

        notas.add(nota);
      }

      return notas;
    } catch (SQLException e) {
      throw new RuntimeException("Error al obtener las notas");
    }
  }

}
