package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable VALUES (NULL, ?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    //    Удаление пользователя
    public void deleteById(int id) {
        String deleteQuery = "DELETE FROM userTable WHERE id=?";
        jdbc.update(deleteQuery, id);
    }

    //    Обновление информации о пользователе
    public User getOne(int id) {
        for (User u :
                findAll()) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public User updateById(User user) {
        String updateQuery = "UPDATE userTable SET firstName=?, lastName=? WHERE id=?";
        jdbc.update(updateQuery, user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }
}
