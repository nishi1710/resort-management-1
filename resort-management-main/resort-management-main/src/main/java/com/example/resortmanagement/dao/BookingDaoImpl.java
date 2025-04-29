package com.example.resortmanagement.dao;

import com.example.resortmanagement.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookingDaoImpl implements BookingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveBooking(Booking booking) {
        String sql = "INSERT INTO booking (username, email, resort_name, room_type, number_of_guests, number_of_rooms, arrival_date, departure_date, room_price, total_price, special_requests) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
            booking.getUsername(),
            booking.getEmail(),
            booking.getResortName(),
            booking.getRoomType(),
            booking.getNumberOfGuests(),
            booking.getNumberOfRooms(),
            booking.getArrivalDate(),
            booking.getDepartureDate(),
            booking.getRoomPrice(),
            booking.getTotalPrice(),
            booking.getSpecialRequests()
        );
    }

    @Override
    public List<Booking> findByEmail(String email) {
        String sql = "SELECT * FROM booking WHERE email = ?";
        return jdbcTemplate.query(sql, new BookingRowMapper(), email);
    }

    private static class BookingRowMapper implements RowMapper<Booking> {
        @Override
        public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
            Booking booking = new Booking();
            booking.setId(rs.getLong("id"));
            booking.setUsername(rs.getString("username"));
            booking.setEmail(rs.getString("email"));
            booking.setResortName(rs.getString("resort_name"));
            booking.setRoomType(rs.getString("room_type"));
            booking.setNumberOfGuests(rs.getInt("number_of_guests"));
            booking.setNumberOfRooms(rs.getInt("number_of_rooms"));
            booking.setArrivalDate(rs.getDate("arrival_date"));
            booking.setDepartureDate(rs.getDate("departure_date"));
            booking.setRoomPrice(rs.getDouble("room_price"));
            booking.setTotalPrice(rs.getDouble("total_price"));
            booking.setSpecialRequests(rs.getString("special_requests"));
            return booking;
        }
    }
}
