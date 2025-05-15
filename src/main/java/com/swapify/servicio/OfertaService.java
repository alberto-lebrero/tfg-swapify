package com.swapify.servicio;

import com.swapify.modelo.Oferta;
import com.swapify.persistencia.OfertaJPADAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfertaService {
      private final OfertaJPADAO ofertaJPADAO;

      @Autowired
      public OfertaService(OfertaJPADAO ofertaJPADAO) {
            this.ofertaJPADAO = ofertaJPADAO;
      }

      public void crearOferta(Oferta ofertaACrear) {
            ofertaJPADAO.create(ofertaACrear);
      }

      @Transactional(readOnly = true)
      public Oferta encontrarOferta(Long id) {
            return ofertaJPADAO.find(id);
      }

      @Transactional(readOnly = true)
      public List<Oferta> listarOfertas() {
            return ofertaJPADAO.findAll();
      }

      public void actualizarOferta(Oferta ofertaAActualizar) {
            ofertaJPADAO.update(ofertaAActualizar);
      }

      public void eliminarOferta(Oferta ofertaAEliminar) {
            ofertaJPADAO.delete(ofertaAEliminar);
      }
}
