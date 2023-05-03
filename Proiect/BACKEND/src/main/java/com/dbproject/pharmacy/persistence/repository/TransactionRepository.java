package com.dbproject.pharmacy.persistence.repository;

import com.dbproject.pharmacy.persistence.DataBaseConnection;
import com.dbproject.pharmacy.persistence.interfaces.ITransactionRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionRepository implements ITransactionRepository {
    private final Connection connection= DataBaseConnection.getConnection();

    @Override
    public void save() throws SQLException {
        Statement statement=connection.createStatement();
        connection.commit();
        statement.execute("savepoint a");
        statement.close();
    }

    @Override
    public void restore() throws SQLException {
        Statement statement=connection.createStatement();
        statement.execute("rollback to a");
        statement.close();
    }
}
