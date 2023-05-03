package com.dbproject.pharmacy.persistence.repository;
import com.dbproject.pharmacy.model.Sale;
import com.dbproject.pharmacy.persistence.DataBaseConnection;
import com.dbproject.pharmacy.persistence.interfaces.ISaleRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
public class SaleRepository implements ISaleRepository {
    private final Connection connection = DataBaseConnection.getConnection();

    @Override
    public void createTable() throws SQLException {
        Statement statement =connection.createStatement();
        statement.executeUpdate(" CREATE TABLE sale( "+
                "id NUMBER(30) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1)," +
                "date_sale DATE CONSTRAINT date_sale_nn NOT NULL, "+
                "quantity NUMBER(30) CONSTRAINT quantity_sale_nn NOT NULL,"+
                "id_client NUMBER(30) CONSTRAINT id_client_sale_nn NOT NULL,"+
                "id_medicament NUMBER(30) CONSTRAINT id_med_sale_nn NOT NULL,"+
                "CONSTRAINT fk_id_client_sale FOREIGN KEY (id_client) REFERENCES client (id),"+
                "CONSTRAINT fk_id_medicament_sale FOREIGN KEY (id_medicament) REFERENCES medicines (id),"+
                "CONSTRAINT pk_id_sale PRIMARY KEY (id)" +
                ")"
        );
    }

    @Override
    public void create(Sale sale) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO sale (date_sale, quantity, id_client, id_medicament) VALUES (?,?,?,?)");
            statement.setDate(1, new java.sql.Date(sale.getDate_sale().getTime()));
            statement.setInt(2, sale.getQuantity());
            statement.setInt(3, sale.getId_client());
            statement.setInt(4, sale.getId_medicament());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Sale> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM sale");
        ResultSet rs = statement.executeQuery();
        List<Sale> sales = new ArrayList<>();
        while (rs.next()) {
            Sale sale=new Sale(
                    rs.getInt("id"),
                    rs.getDate("date_sale"),
                    rs.getInt("quantity"),
                    rs.getInt("id_client"),
                    rs.getInt("id_medicament")

            );
            sales.add(sale);
        }
        statement.close();
        return sales;
    }

    @Override
    public Sale getById(Integer id) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sale WHERE id=?");
            statement.setInt(1, id);
            ResultSet rs=statement.executeQuery();
            if(rs.next()) {
                Sale sale=new Sale(
                        rs.getInt("id"),
                        rs.getDate("date_sale"),
                        rs.getInt("quantity"),
                        rs.getInt("id_client"),
                        rs.getInt("id_medicament")
                );
                statement.close();
                return sale;
            }
            statement.close();
            return null;
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Sale sale) {
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE sale SET date_sale=?, quantity=?, id_client=?, id_medicament=? WHERE id=?");
            statement.setDate(1, new java.sql.Date(sale.getDate_sale().getTime()));
            statement.setInt(2, sale.getQuantity());
            statement.setInt(3, sale.getId_client());
            statement.setInt(4, sale.getId_medicament());
            statement.setInt(5, sale.getId());
            statement.executeUpdate();
            statement.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM sale WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch(UncategorizedSQLException | SQLException e){
            throw new RuntimeException(e);
        }
    }
}
