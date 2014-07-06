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

    private static final class BoardMapper implements RowMapper<Board> {
        private boolean isContentExist;

        //조금더 좋은 방법이 없는지 고민하기.
        public BoardMapper(boolean isContentExist) {
            this.isContentExist = isContentExist;
        }

        public Board mapRow(ResultSet rs, int rowNum) throws SQLException {

            Board board = new Board();
            board.setId(rs.getLong("id"));
            board.setTitle(rs.getString("title"));
            board.setName(rs.getString("name"));
            board.setWriteDate(rs.getTimestamp("write_date"));
            if (isContentExist)
                board.setContent(rs.getString("content"));


            return board;
        }
    }


    private static final class BoardListMapper implements RowMapper<Board> {
        public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
            Board board = new Board();
            board.setId(rs.getLong("id"));
            board.setTitle(rs.getString("title"));
            board.setName(rs.getString("name"));
            board.setWriteDate(rs.getTimestamp("write_date"));

            return board;
        }
    }

    // TODO... 조금더 고민하기.. String으로 반환하는것이 최선임??
    private String convertToDateTime(java.util.Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(date);
    }

    @Override
    public Board find(long id) {
        boolean isContentExist = true;
        String sql = "SELECT id, title, name, content, write_date FROM Board WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BoardMapper(isContentExist));
    }

    @Override
    public List<Board> findPage() {
        boolean isContentExist = false;
        String sql = "SELECT id, title, name, write_date FROM Board ORDER BY id DESC";
        return jdbcTemplate.query(sql, new Object[]{}, new BoardMapper(isContentExist));
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM Board";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public long write(final Board board) {
        final String sql = "INSERT INTO BOARD(title, name, content, write_date) VALUES(?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, board.getTitle());
                ps.setString(2, board.getName());
                ps.setString(3, board.getContent());
                ps.setString(4, convertToDateTime(board.getWriteDate()));
                return ps;
            }
        }, holder);

        return holder.getKey().longValue();
    }

    @Override
    public void modify(Board board) {
        String sql = "UPDATE BOARD SET TITLE = ?, NAME = ?, CONTENT = ?, WRITE_DATE = ? WHERE ID = ?";
        jdbcTemplate.update(sql, board.getTitle(), board.getName(), board.getContent(), board.getWriteDate(), board.getId());
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM BOARD WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }
}
