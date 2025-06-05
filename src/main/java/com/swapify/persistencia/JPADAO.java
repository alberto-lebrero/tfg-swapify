package com.swapify.persistencia;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase abstracta que implementa el patrón DAO utilizando JPA y Spring Boot.
 * <p>
 * Proporciona una implementación genérica de las operaciones CRUD, delegando
 * en el {@link EntityManager} para gestionar entidades de tipo {@code T} identificadas por {@code K}.
 *
 * @param <T> Tipo de la entidad
 * @param <K> Tipo de clave primaria de la entidad
 */
@AllArgsConstructor
@Repository
@Transactional
public abstract class JPADAO<T,K> implements DAO<T,K> {

      /**
       * Contexto de persistencia inyectado por Spring que gestiona las operaciones con la base de datos.
       */
      @PersistenceContext
      protected EntityManager em;

      /**
       * Clase del tipo de entidad gestionada. Se usa para consultas dinámicas.
       */
      private Class<T> clazz;

      /**
       * Constructor de la clase abstracta, requiere el tipo de la entidad gestionada.
       *
       * @param entityClass Clase de la entidad
       */
      public JPADAO(Class<T> entityClass){
            this.clazz = entityClass;
      }

      /**
       * {@inheritDoc}
       *
       * @throws EntityExistsException si la entidad ya existe en la base de datos
       */
      @Override
      public T create(T e) throws EntityExistsException {
            em.persist(e);
            em.flush();       // Sincronizo inmediatamente con la base de datos
            em.refresh(e);    // Actualizo el estado de la entidad desde la base de datos

            return e;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      @Transactional(readOnly = true)
      public T find(K id) {
            return em.find(clazz, id);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      @Transactional(readOnly = true)
      public List<T> findAll() {
            return em.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e", clazz).getResultList();
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public T update(T e) {
            return em.merge(e);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public void delete(T e) {
            em.remove(em.contains(e) ? e : em.merge(e)); // Elimino el elemento asegurando que esté gestionado por el EntityManager
            em.flush(); // Fuerzo la sincronización con la BBDD inmeditamente depués de eliminar el elemento
      }
}