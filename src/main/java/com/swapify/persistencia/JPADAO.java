package com.swapify.persistencia;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Repository
@Transactional
public abstract class JPADAO<T,K> implements DAO<T,K> {
      @PersistenceContext
      protected EntityManager em;

      private Class<T> clazz;

      public JPADAO(Class<T> entityClass){
            this.clazz = entityClass;
      }

      /**
       *
       * @param e elemento a crear
       * @return Devuelve el elemento creado.
       * @throws EntityExistsException
       */
      @Override
      public T crear(T e) throws EntityExistsException {
            em.persist(e);
            em.flush();
            em.refresh(e);

            return e;
      }

      @Override
      @Transactional(readOnly = true)
      public T encontrar(K id) {
            return em.find(clazz, id);
      }

      @Override
      @Transactional(readOnly = true)
      public List<T> encontrarTodos() {
            return em.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e", clazz).getResultList();
      }

      @Override
      public T actualizar(T e) {
            return em.merge(e);
      }

      @Override
      public void borrar(T e) {
            em.remove(em.contains(e) ? e : em.merge(e)); // Elimino el elemento asegurando que esté gestionado por el EntityManager
            em.flush(); //Sincronizo con la BBDD inmeditamente depués de eliminar el elemento
      }
}