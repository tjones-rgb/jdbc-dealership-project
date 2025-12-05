package com.pluralsight.dao;


import com.pluralsight.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private Connection getConnection() throws SQLException {
        return Database.getDataSource().getConnection();

    }

    private Vehicle mapRow(ResultSet rs) throws SQLException {
        return new Vehicle(
                rs.getString("Vin"),
                rs.getString("make"),
                rs.getString("model"),
                rs.getInt("year"),
                rs.getString("color"),
                rs.getDouble("price")
                rs.getBoolean("sold")
        );
    }

    public List<Vehicle> getPriceRange(double min, double max) {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ? AND sold = 0";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Vehicle> getByMakeModel(String make, String model){
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ? AND sold = )";

        try(Connection conn = getConnection();
       PreparedStatement ps  = conn.prepareStatement(sql)) {
           ps.setString(1, make);
           ps.setString(2, model);

           ResultSet rs = ps.executeQuery();
           while (rs.next()) list.add(mapRow(rs));

        }catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
    public LIst<Vehicle> getYearRange(int min, int max) {
        List<Vehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ? AND sold = )";

        try(Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,min);
            ps.setInt(2,max);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Vehicle>
}