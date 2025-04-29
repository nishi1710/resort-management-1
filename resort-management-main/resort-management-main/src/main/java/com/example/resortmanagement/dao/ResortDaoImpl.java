package com.example.resortmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.resortmanagement.model.Resort;

@Repository
public class ResortDaoImpl implements ResortDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public void save(Resort resort) {
        // First, check if the resort already exists by resortId
        String checkSql = "SELECT COUNT(*) FROM resort WHERE resort_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setLong(1, resort.getResortId());
            ResultSet resultSet = checkStmt.executeQuery();
    
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                // Resort already exists, perform an update on other fields except resort_id
                System.out.println("Resort with ID " + resort.getResortId() + " already exists. Updating other fields.");
    
                String updateSql = "UPDATE resort SET name = ?, location = ?, description = ?, longdescription = ? WHERE resort_id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setString(1, resort.getName());
                    updateStmt.setString(2, resort.getLocation());
                    updateStmt.setString(3, resort.getDescription());
                    updateStmt.setString(4, resort.getLongdescription());
                    updateStmt.setLong(5, resort.getResortId()); // Use resortId for the update condition
                    updateStmt.executeUpdate();
                    System.out.println("Resort with ID " + resort.getResortId() + " updated.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                // Resort does not exist, proceed with insert
                String insertSql = "INSERT INTO resort (resort_id, name, location, description, longdescription) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setLong(1, resort.getResortId());
                    insertStmt.setString(2, resort.getName());
                    insertStmt.setString(3, resort.getLocation());
                    insertStmt.setString(4, resort.getDescription());
                    insertStmt.setString(5, resort.getLongdescription());
                    insertStmt.executeUpdate();
                    System.out.println("Resort added with ID " + resort.getResortId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    @Override
    public Resort findById(Long id) {
        String sql = "SELECT * FROM resort WHERE resort_id = ?";
        Resort resort = null;

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                resort = mapRowToResort(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resort;
    }

    @Override
    public List<Resort> findAll() {
        List<Resort> resorts = new ArrayList<>();
        String sql = "SELECT * FROM resort";

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                resorts.add(mapRowToResort(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resorts;
    }

    @Override
    public void update(Resort resort) {
        String sql = "UPDATE resort SET name=?, location=?, description=?, longdescription=? WHERE resort_id=?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, resort.getName());
            stmt.setString(2, resort.getLocation());
            stmt.setString(3, resort.getDescription());
            stmt.setString(4, resort.getLongdescription());
            stmt.setLong(5, resort.getResortId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM resort WHERE resort_id = ?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Resort mapRowToResort(ResultSet rs) throws SQLException {
        Resort resort = new Resort();
        resort.setResortId(rs.getLong("resort_id"));
        resort.setName(rs.getString("name"));
        resort.setLocation(rs.getString("location"));
        resort.setDescription(rs.getString("description"));
        resort.setLongdescription(rs.getString("longdescription"));
        return resort;
    }
}
