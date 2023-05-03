package com.dbproject.pharmacy.persistence.repository;

import com.dbproject.pharmacy.model.Supply;
import com.dbproject.pharmacy.persistence.DataBaseConnection;
import com.dbproject.pharmacy.persistence.interfaces.ISupplyRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
public class SupplyRepository implements ISupplyRepository {
    private final Connection connection = DataBaseConnection.getConnection();

    @Override
    public void createTable() throws SQLException {
        Statement statement =connection.createStatement();
        statement.executeUpdate(" CREATE TABLE supply( "+
                "id NUMBER(30) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1)," +
                "date_s DATE CONSTRAINT date_aprov_nn NOT NULL, "+
                "quantity NUMBER(30) CONSTRAINT quantity_aprov_nn NOT NULL,"+
                "id_medicament NUMBER(30) UNIQUE CONSTRAINT id_med_aprov_nn NOT NULL,"+
                "CONSTRAINT fk_id_medicament_aprov FOREIGN KEY (id_medicament) REFERENCES medicines (id),"+
                "CONSTRAINT pk_id_s PRIMARY KEY (id)," +
                "CONSTRAINT ck_q CHECK (quantity > 0)" +
                ")"
         );
    }

    @Override
    //add
    public void create(Supply supply) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(supply.getDate_supply());
            c.add(Calendar.DATE, 1);
            supply.setDate_supply(c.getTime());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO supply (date_s, quantity, id_medicament) VALUES (?,?,?)");
            statement.setDate(1, new java.sql.Date(supply.getDate_supply().getTime()));
            statement.setInt(2, supply.getQuantity());
            statement.setInt(3, supply.getId_medicament());
            statement.executeUpdate();
            statement.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Supply> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM supply");
        ResultSet rs = statement.executeQuery();
        List<Supply> supplies = new ArrayList<>();
        while (rs.next()) {
            Supply supply = new Supply(
                    rs.getInt("id"),
                    rs.getDate("date_s"),
                    rs.getInt("quantity"),
                    rs.getInt("id_medicament")

            );
            supplies.add(supply);
        }
        statement.close();
        return supplies;
    }

    @Override
    public Supply getById(Integer id) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM supply WHERE id=?");
            statement.setInt(1, id);
            ResultSet rs=statement.executeQuery();
            if(rs.next()) {
                Supply supply = new Supply(
                        rs.getInt("id"),
                        rs.getDate("date_s"),
                        rs.getInt("quantity"),
                        rs.getInt("id_medicament")
                );
                statement.close();
                return supply;
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
    public Supply getByMedicineId(Integer id) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM supply WHERE id_medicament=?");
            statement.setInt(1, id);
            ResultSet rs=statement.executeQuery();
            if(rs.next()) {
                Supply supply = new Supply(
                        rs.getInt("id"),
                        rs.getDate("date_s"),
                        rs.getInt("quantity"),
                        rs.getInt("id_medicament")
                );
                statement.close();
                return supply;
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
    public void update(Supply supply) {
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE supply SET date_s=?, quantity=?, id_medicament=? WHERE id=?");
            statement.setDate(1, new java.sql.Date(supply.getDate_supply().getTime()));
            statement.setInt(2, supply.getQuantity());
            statement.setInt(3, supply.getId_medicament());
            statement.setInt(4, supply.getId());
            statement.executeUpdate();
            statement.close();
        } catch(UncategorizedSQLException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM supply WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch(UncategorizedSQLException | SQLException e){
            throw new RuntimeException(e);
        }
    }
}
