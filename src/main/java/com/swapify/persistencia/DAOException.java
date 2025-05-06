package com.swapify.persistencia;

public class DAOException extends RuntimeException {
      private static final long serialVersionUID = 6174715895535994707L;
      public DAOException(String mensaje){
            super(mensaje);
      }

      public DAOException(String mensaje, Throwable causa){
            super(mensaje, causa);
      }

      public DAOException(Throwable causa){
            super(causa);
      }
}
