package com.codegym.dao;

import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDao implements IUserDao {
    public static final String SELECT_ALL_FROM_USER = "select * from users";
    public static final String SELECT_ONE_FROM_USER = "SELECT * FROM users where id = ?";
    public static final String INSERT_USER = "insert into users (name,password,birthday,address,email,role_id) values (?,?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE users SET name = ?, password = ?, birthday = ?, address = ?, email=?,role_id =? where  id = ?";
    public static final String DELETE_USER = "delete from users where id = ?";
    Connection connection = DBConnection.getConnection();

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_USER);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id  = rs.getInt("id");
                String username = rs.getString("name");
                String password = rs.getString("password");
                Date birthday = rs.getDate("birthday");
                String address = rs.getString("address");
                String email = rs.getString("email");
                int role_id = rs.getInt("role_id");
                User user = new User(id, username, password,birthday,address,email,role_id);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = new User();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ONE_FROM_USER);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String username = resultSet.getString("name");
                String password = resultSet.getString("password");
                Date birthday = resultSet.getDate("birthday");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                int role_id =resultSet.getInt("role_id");
                user = new User(id,username, password,birthday,address,email,role_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, user.getBirthday());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setInt(6,user.getRole_id());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, user.getBirthday());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setInt(6,user.getRole_id());
            preparedStatement.setInt(7,id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
