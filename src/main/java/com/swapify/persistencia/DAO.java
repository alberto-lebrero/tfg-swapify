package com.swapify.persistencia;

import java.util.List;

public interface DAO<T, K> {

      /**
       * Añade un nuevo elemento a los almacenados
       *
       * @param e elemento a crear
       * @throws DAOException si no se ha podido realizar la operación
       */
      T crear(T e);

      /**
       * Devuelve el elemento con el código dado
       *
       * @param id código del elemento a obtener
       * @return elemento indicado o null si no existe
       */
      T encontrar(K id);

      /**
       * Devuelve una lista con todos los elementos almacenados
       *
       * @return lista de elementos
       */
      List<T> encontrarTodos();

      /**
       * Actualiza un elemento de los almacenados
       *
       * @param e elemento a guardar
       * @throws DAOException si no se ha podido realizar la operación
       */
      T actualizar(T e);

      /**
       * Borra el elemento indicado de los elementos almacenados
       *
       * @param e elemento a borrar
       * @throws DAOException si no se ha podido realizar la operación
       */
      void borrar(T e);
}