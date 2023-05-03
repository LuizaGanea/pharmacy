package com.dbproject.pharmacy.persistence.repository;
import com.dbproject.pharmacy.model.Medicine;
import com.dbproject.pharmacy.persistence.DataBaseConnection;
import com.dbproject.pharmacy.persistence.interfaces.IMedicineRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicineRepository implements IMedicineRepository {

    private final Connection connection = DataBaseConnection.getConnection();


    @Override
    public void createTable() throws SQLException {
        Statement statement =connection.createStatement();
        statement.executeUpdate(" CREATE TABLE medicines(" +
                "id NUMBER(30) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1) , " +
                "name VARCHAR2(30) CONSTRAINT name_nn NOT NULL, " +
                "description VARCHAR2(350) CONSTRAINT description_nn NOT NULL, " +
                "quantity NUMBER(30) CONSTRAINT quantity_nn NOT NULL, " +
                "requiresPrescription NUMBER(1) CONSTRAINT requires_prescription_nn NOT NULL, " +
                "minAge NUMBER(30) CONSTRAINT min_age_nn NOT NULL, " +
                "CONSTRAINT id_m_pf PRIMARY KEY (id)" +
                ")"
        );
    }

    @Override
    public void create(Medicine newMedicine) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO medicines(name, description, quantity, requiresPrescription, minAge) VALUES (?,?,?,?,?)");
            statement.setString(1, newMedicine.getName());
            statement.setString(2, newMedicine.getDescription());
            statement.setInt(3, 0);
            statement.setBoolean(4, newMedicine.getRequiresPrescription());
            statement.setInt(5, newMedicine.getMinAge());

            statement.executeUpdate();
            statement.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Medicine> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM medicines");
        ResultSet rs = statement.executeQuery();
        List<Medicine> medicines = new ArrayList<>();
        while (rs.next()) {
            Medicine medicine = new Medicine(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("quantity"),
                    rs.getBoolean("requiresPrescription"),
                    rs.getInt("minAge")
            );
            medicines.add(medicine);
        }
        statement.close();
        return medicines;
    }

    @Override
    public Medicine getById(Integer id) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM medicines WHERE id=?");
            statement.setInt(1, id);
            ResultSet rs=statement.executeQuery();
            if(rs.next()) {
                Medicine medicine = new Medicine(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getBoolean("requiresPrescription"),
                        rs.getInt("minAge")
                );
                statement.close();
                return medicine;
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
    public void update(Medicine medicine) {
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE medicines SET name=?, description=?, quantity=?, requiresPrescription=?, minAge=? WHERE id=?");
            statement.setString(1, medicine.getName());
            statement.setString(2, medicine.getDescription());
            statement.setInt(3, medicine.getQuantity());
            statement.setBoolean(4, medicine.getRequiresPrescription());
            statement.setInt(5, medicine.getMinAge());
            statement.setInt(6, medicine.getId());
            statement.executeUpdate();
            statement.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM medicines WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch(UncategorizedSQLException | SQLException e){
            throw new RuntimeException(e);
        }
    }
}
