package com.soluciones.web.appGrupo4.helper;

import org.springframework.dao.DataAccessException;

public interface IVerifySqlError {

    public boolean isConstraintViolation(DataAccessException e);
    
}
