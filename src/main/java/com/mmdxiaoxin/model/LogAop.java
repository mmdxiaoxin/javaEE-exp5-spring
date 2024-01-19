package com.mmdxiaoxin.model;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

@Aspect
@Component
public class LogAop {
    @Resource
    DBConnection dbConnection;

    @Pointcut(value = "execution(* com.mmdxiaoxin.model.*Service.*(..))")
    public void cutPoint() {
    }

    @After(value = "cutPoint()")
    public void log(JoinPoint point) throws SQLException, ClassNotFoundException {
        String methodName = point.getSignature().getName();
        String className = point.getTarget().getClass().getSimpleName();
        String params = Arrays.toString(point.getArgs());
        System.out.println(methodName + "方法被执行.....");

        // 将日志写入数据库
        String sql = "INSERT INTO db_operation_log (method_name, class_name, params) VALUES (?, ?, ?)";

        Connection connection = dbConnection.getCon();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, methodName);
        preparedStatement.setString(2, className);
        preparedStatement.setString(3, params);
        preparedStatement.executeUpdate();

        System.out.println("日志写入数据库完成");

    }
}
