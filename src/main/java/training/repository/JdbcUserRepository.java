package training.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import training.domain.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Repository
public class JdbcUserRepository implements UserRepository {
    private JdbcTemplate template;
    private static long nextId = 4;

    public JdbcUserRepository(DataSource dataSource){
        template = new JdbcTemplate(dataSource);
    }

    public List<User> getUsers() {
        String sqlText = "select * from user";
        return template.query(sqlText,new UserMapper());
    }

    public User getUser(Long id) {
        String sqlText = "select * from user where id = ?";
        return template.queryForObject(sqlText,new UserMapper(),id);
    }

    public int getNumberOfUsers() {
        String sqlText = "select count(*) from user";
        return template.queryForObject(sqlText,Integer.class);
    }

    public Long createUser(String firstName, String name) {
        String sqlText = "insert into user(id,first_name,name) values(?,?,?)";
        long id = nextId++;
        int uc = template.update(sqlText,id,firstName,name);
        return id;
    }

    public int deleteUser(Long id) {
        String sqlText = "delete from user where id=?";
        return template.update(sqlText,id);
    }

    public void updateUser(User user) {
        String sqlText = "update user set first_name= ?,name= ? where id = ?";
        template.update(sqlText,user.getFirsName(),user.getName(),user.getId());
    }

    private class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(resultSet.getLong("id"),
                    resultSet.getString("first_name"),resultSet.getString("name"));
        }
    }
}
