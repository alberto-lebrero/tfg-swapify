package com.swapify.persistencia;

import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD básicas de acceso a datos.
 * <p>
 * Esta interfaz define un contrato común para las clases DAO que gestionan
 * entidades de persistencia de cualquier tipo {@code T} con identificador {@code K}.
 *
 * @param <T> Tipo de entidad persistente
 * @param <K> Tipo del identificador de la entidad
 */
public interface DAO<T, K> {

      /**
       * Persiste un nuevo elemento en la base de datos.
       *
       * @param e Elemento a crear.
       * @return El elemento creado con estado gestionado por el contexto de persistencia.
       */
      T create(T e);

      /**
       * Busca una entidad según su identificador.
       *
       * @param id Identificador de la entidad.
       * @return La entidad correspondiente o {@code null} si no se encuentra.
       */
      T find(K id);

      /**
       * Obtiene una lista de todas las entidades persistidas del tipo gestionado.
       *
       * @return Lista de entidades
       */
      List<T> findAll();

      /**
       * Actualiza una entidad existente en la base de datos.
       *
       * @param e Entidad con los datos a actualizar
       * @return La entidad actualizada y gestionada por el contexto de persistencia
       */
      T update(T e);

      /**
       * Elimina una entidad del almacenamiento persistente.
       *
       * @param e Entidad a eliminar
       */
      void delete(T e);
}