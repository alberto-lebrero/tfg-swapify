package com.swapify.servicio;

import com.swapify.modelo.Categoria;
import com.swapify.persistencia.CategoriaJPADAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoriaService {
      private final CategoriaJPADAO categoriaJPADAO;

      @Autowired
      public CategoriaService(CategoriaJPADAO categoriaJPADAO) {
            this.categoriaJPADAO = categoriaJPADAO;
      }

      public void crearCategoria(Categoria categoriaACrear) {
            categoriaJPADAO.create(categoriaACrear);
      }

      @Transactional(readOnly = true)
      public Categoria encontrarCategoria(Long id) {
            return categoriaJPADAO.find(id);
      }

      @Transactional(readOnly = true)
      public List<Categoria> listarCategorias() {
            return categoriaJPADAO.findAll();
      }

      public void actualizarCategoria(Categoria categoriaAActualizar) {
            categoriaJPADAO.update(categoriaAActualizar);
      }

      public void eliminarCategoria(Categoria categoriaAEliminar) {
            categoriaJPADAO.delete(categoriaAEliminar);
      }
}
