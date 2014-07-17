package demo.repository;

import demo.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class BoardRepositoryJdbc implements BoardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;


    private static final class BoardMapper implements RowMapper<Board> {
        public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
            Board board = new Board();
            board.setId(rs.getInt("id"));
            board.setKeyName(rs.getString("key_name"));
            board.setName(rs.getString("name"));
            return board;
        }
    }

    @Override
    public Board find(String keyName) {
        sql = "SELECT id, key_name, name FROM BOARD WHERE KEY_NAME = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{keyName}, new BoardMapper());
    }


    @Override
    public List<Board> findList() {
        sql = "SELECT id, key_name, name FROM BOARD";
        return jdbcTemplate.query(sql, new Object[]{}, new BoardMapper());
    }

    @Override
    public long add(final Board board) {

        final String sql = "INSERT INTO BOARD(KEY_NAME, NAME) VALUES(?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, board.getKeyName());
                ps.setString(2, board.getName());
                return ps;
            }
        }, holder);

        return holder.getKey().longValue();
    }
}
