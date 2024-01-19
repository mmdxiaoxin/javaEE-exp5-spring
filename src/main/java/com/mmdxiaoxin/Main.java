package com.mmdxiaoxin;

import com.mmdxiaoxin.entity.User;
import com.mmdxiaoxin.model.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] argv) throws SQLException, ClassNotFoundException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        UserService userService = context.getBean(UserService.class);

        // 获取用户列表
        System.out.println("User List:");
        System.out.println(userService.list());

        // 添加用户
        User newUser = new User();
        newUser.setUserName("JohnDoe");
        newUser.setPasswd("123");
        userService.addUser(newUser);
        System.out.println("用户添加. 添加后结果:");
        System.out.println(userService.list());

        // 更新用户信息
        User userToUpdate = userService.list().get(0);
        userToUpdate.setUserName("Updated");
        userToUpdate.setPasswd("234");
        userService.updateUser(userToUpdate);
        System.out.println("用户修改. 修改后结果:");
        System.out.println(userService.list());

        // 删除用户
        User userToDelete = userService.list().get(0);
        userService.deleteUser(userToDelete.getId());
        System.out.println("用户删除. 删除后结果:");
        System.out.println(userService.list());
    }
}
