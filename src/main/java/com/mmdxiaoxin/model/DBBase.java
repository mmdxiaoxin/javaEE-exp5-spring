package com.mmdxiaoxin.model;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.*;

@Component
public class DBBase {
    @Resource
    DBConnection connection;

    public ResultSet selectAll(String sql) throws SQLException, ClassNotFoundException {
        Connection con = connection.getCon();
        Statement stat = con.createStatement();
        ResultSet res = stat.executeQuery(sql);
        return res;
    }

    public void insert(String sql, Object... params) throws SQLException, ClassNotFoundException {
        Connection con = connection.getCon();
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
        }
    }

    public void update(String sql, Object... params) throws SQLException, ClassNotFoundException {
        Connection con = connection.getCon();
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
        }
    }

    public void delete(String sql, Object... params) throws SQLException, ClassNotFoundException {
        Connection con = connection.getCon();
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
        }
    }
}
