package com.travel.dao;

import com.travel.model.TravelPackage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PackageDAO {

    public List<TravelPackage> getAllPackages() {
        List<TravelPackage> list = new ArrayList<>();
        String sql = "SELECT * FROM travel_packages ORDER BY created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapPackage(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public TravelPackage getPackageById(int id) {
        String sql = "SELECT * FROM travel_packages WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapPackage(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addPackage(TravelPackage pkg) {
        String sql = "INSERT INTO travel_packages (name, destination, description, duration_days, price, available_slots, image_url) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pkg.getName());
            ps.setString(2, pkg.getDestination());
            ps.setString(3, pkg.getDescription());
            ps.setInt(4, pkg.getDurationDays());
            ps.setBigDecimal(5, pkg.getPrice());
            ps.setInt(6, pkg.getAvailableSlots());
            ps.setString(7, pkg.getImageUrl());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePackage(TravelPackage pkg) {
        String sql = "UPDATE travel_packages SET name=?, destination=?, description=?, duration_days=?, price=?, available_slots=?, image_url=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pkg.getName());
            ps.setString(2, pkg.getDestination());
            ps.setString(3, pkg.getDescription());
            ps.setInt(4, pkg.getDurationDays());
            ps.setBigDecimal(5, pkg.getPrice());
            ps.setInt(6, pkg.getAvailableSlots());
            ps.setString(7, pkg.getImageUrl());
            ps.setInt(8, pkg.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePackage(int id) {
        String sql = "DELETE FROM travel_packages WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reduceSlots(int packageId, int count) {
        String sql = "UPDATE travel_packages SET available_slots = available_slots - ? WHERE id=? AND available_slots >= ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, count);
            ps.setInt(2, packageId);
            ps.setInt(3, count);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void restoreSlots(int packageId, int count) {
        String sql = "UPDATE travel_packages SET available_slots = available_slots + ? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, count);
            ps.setInt(2, packageId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TravelPackage mapPackage(ResultSet rs) throws SQLException {
        TravelPackage p = new TravelPackage();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setDestination(rs.getString("destination"));
        p.setDescription(rs.getString("description"));
        p.setDurationDays(rs.getInt("duration_days"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setAvailableSlots(rs.getInt("available_slots"));
        p.setImageUrl(rs.getString("image_url"));
        p.setCreatedAt(rs.getTimestamp("created_at"));
        return p;
    }
}
