package com.dbproject.pharmacy.persistence.interfaces;

import java.sql.SQLException;

public interface ITransactionRepository {
    void save() throws SQLException;
    void restore() throws SQLException;
}
