package com.travel.dao;

import com.travel.model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public boolean createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, package_id, num_travelers, total_price, travel_date, status) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getPackageId());
            ps.setInt(3, booking.getNumTravelers());
            ps.setBigDecimal(4, booking.getTotalPrice());
            ps.setDate(5, booking.getTravelDate());
            ps.setString(6, "CONFIRMED");
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Booking> getBookingsByUser(int userId) {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT b.*, u.full_name AS user_name, p.name AS package_name, p.destination " +
                     "FROM bookings b JOIN users u ON b.user_id=u.id JOIN travel_packages p ON b.package_id=p.id " +
                     "WHERE b.user_id=? ORDER BY b.booking_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapBooking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT b.*, u.full_name AS user_name, p.name AS package_name, p.destination " +
                     "FROM bookings b JOIN users u ON b.user_id=u.id JOIN travel_packages p ON b.package_id=p.id " +
                     "ORDER BY b.booking_date DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapBooking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Booking getBookingById(int id) {
        String sql = "SELECT b.*, u.full_name AS user_name, p.name AS package_name, p.destination " +
                     "FROM bookings b JOIN users u ON b.user_id=u.id JOIN travel_packages p ON b.package_id=p.id " +
                     "WHERE b.id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapBooking(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cancelBooking(int bookingId) {
        String sql = "UPDATE bookings SET status='CANCELLED' WHERE id=? AND status='CONFIRMED'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookingId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int countBookings() {
        String sql = "SELECT COUNT(*) FROM bookings WHERE status='CONFIRMED'";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countUsers() {
        String sql = "SELECT COUNT(*) FROM users WHERE role='user'";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Booking mapBooking(ResultSet rs) throws SQLException {
        Booking b = new Booking();
        b.setId(rs.getInt("id"));
        b.setUserId(rs.getInt("user_id"));
        b.setPackageId(rs.getInt("package_id"));
        b.setNumTravelers(rs.getInt("num_travelers"));
        b.setTotalPrice(rs.getBigDecimal("total_price"));
        b.setBookingDate(rs.getTimestamp("booking_date"));
        b.setTravelDate(rs.getDate("travel_date"));
        b.setStatus(rs.getString("status"));
        b.setUserName(rs.getString("user_name"));
        b.setPackageName(rs.getString("package_name"));
        b.setDestination(rs.getString("destination"));
        return b;
    }
}
