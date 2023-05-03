package com.dbproject.pharmacy.persistence.repository;

import com.dbproject.pharmacy.model.Client;
import com.dbproject.pharmacy.persistence.DataBaseConnection;
import com.dbproject.pharmacy.persistence.interfaces.IClientRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
public class ClientRepository implements IClientRepository {

    private final Connection connection = DataBaseConnection.getConnection();

    @Override
    public void createTable() throws SQLException {
        Statement statement =connection.createStatement();
        statement.executeUpdate(" CREATE TABLE client(" +
                "id NUMBER(30) GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1) , " +
                "first_name VARCHAR2(30) CONSTRAINT client_first_name_nn NOT NULL, " +
                "last_name VARCHAR2(30) CONSTRAINT client_last_name_nn NOT NULL, " +
                "cnp VARCHAR2(10) CONSTRAINT client_cnp_nn NOT NULL, " +
                "date_of_birth DATE CONSTRAINT client_date_of_birth_nn NOT NULL, " +
                "CONSTRAINT client_cnp_uk UNIQUE(cnp), " +
                "CONSTRAINT id_c_pk PRIMARY KEY (id)" +
                ")"
        );
    }

    @Override
    public void create(Client client) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(client.getDateOfBirth());
            c.add(Calendar.DATE, 1);
            client.setDateOfBirth(c.getTime());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO client(first_name, last_name, cnp, date_of_birth) VALUES (?,?,?,?)");
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getCnp());
            statement.setDate(4, new java.sql.Date(client.getDateOfBirth().getTime()));
            statement.executeUpdate();
            statement.close();

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> getAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Client");
        ResultSet rs= statement.executeQuery();
        List<Client> clients = new ArrayList<>();
        while (rs.next()) {
            Client client =  new Client(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("cnp"),
                    rs.getDate("date_of_birth")
            );
            clients.add(client);
        }
        statement.close();
        return clients;
    }

    @Override
    public Client getById(Integer id) {
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM client WHERE id=?");
            statement.setInt(1, id);
            ResultSet rs=statement.executeQuery();
            if(rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("cnp"),
                        rs.getDate("date_of_birth")
                );
                statement.close();
                return client;
            }
            statement.close();
            return null;
        } catch (SQLException e){
            return null;
        }
    }

    @Override
    public void update(Client client) {
        try{
            Calendar c = Calendar.getInstance();
            c.setTime(client.getDateOfBirth());
            c.add(Calendar.DATE, 1);
            client.setDateOfBirth(c.getTime());
            PreparedStatement statement = connection.prepareStatement("UPDATE client SET first_name=?, last_name=?, cnp=?, date_of_birth=? WHERE id=?");
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getCnp());
            statement.setDate(4, new java.sql.Date(client.getDateOfBirth().getTime()));
            statement.setInt(5, client.getId());
            statement.executeUpdate();
            statement.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM client WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
