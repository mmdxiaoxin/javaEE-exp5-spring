package com.mmdxiaoxin.model;

import com.mmdxiaoxin.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Resource
    DBBase dbBase;
    static String SELECT_SQL = "SELECT * FROM user";
    static String INSERT_SQL = "INSERT INTO user (user_name, passwd) VALUES (?, ?)";
    static String UPDATE_SQL = "UPDATE user SET user_name=?, passwd=? WHERE id=?";
    static String DELETE_SQL = "DELETE FROM user WHERE id=?";

    public List<User> list() throws SQLException, ClassNotFoundException {
        ResultSet rs = dbBase.selectAll(SELECT_SQL);
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("user_name"));
            user.setPasswd(rs.getString("passwd"));
            users.add(user);
        }
        return users;
    }

    public void addUser(User user) throws SQLException, ClassNotFoundException {
        dbBase.insert(INSERT_SQL, user.getUserName(), user.getPasswd());
    }

    public void updateUser(User user) throws SQLException, ClassNotFoundException {
        dbBase.update(UPDATE_SQL, user.getUserName(), user.getPasswd(), user.getId());
    }

    public void deleteUser(int userId) throws SQLException, ClassNotFoundException {
        dbBase.delete(DELETE_SQL, userId);
    }
}
