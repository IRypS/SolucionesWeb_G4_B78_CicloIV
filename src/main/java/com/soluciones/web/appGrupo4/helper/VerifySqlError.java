package com.soluciones.web.appGrupo4.helper;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
public class VerifySqlError implements IVerifySqlError {

    @Override
    public boolean isConstraintViolation(DataAccessException e) {

        if( e.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) e.getRootCause();
            int sqlErrorCode = sqlEx.getErrorCode();
            
            if (sqlErrorCode == 1451) { return true; }
        }
        
        return false;
    };
    
}
