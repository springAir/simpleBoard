package demo.repository;

import demo.model.Post;
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
public class PostRepositoryJdbc implements PostRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class PostMapper implements RowMapper<Post> {
        private boolean isContentExist;

        //조금더 좋은 방법이 없는지 고민하기.
        public PostMapper(boolean isContentExist) {
            this.isContentExist = isContentExist;
        }

        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {

            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setTitle(rs.getString("title"));
            post.setWriter(rs.getString("writer"));
            post.setWriteDate(rs.getTimestamp("write_date"));
            if (isContentExist)
                post.setContent(rs.getString("content"));

            return post;
        }
    }


    @Override
    public Post find(long id) {
        boolean isContentExist = true;
        String sql = "SELECT id, title, writer, content, write_date FROM POST WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PostMapper(isContentExist));
    }

    @Override
    public List<Post> findPage() {
        boolean isContentExist = false;
        String sql = "SELECT id, title, writer, write_date FROM POST ORDER BY id DESC";
        return jdbcTemplate.query(sql, new Object[]{}, new PostMapper(isContentExist));
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM POST";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public long add(final Post post) {
        final String sql = "INSERT INTO POST(board_Id, title, writer, content, write_date) VALUES(?,?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

                //FIXME!!
                ps.setInt(1, 0);
                ps.setString(2, post.getTitle());
                ps.setString(3, post.getWriter());
                ps.setString(4, post.getContent());
                ps.setString(5, convertToDateTime(post.getWriteDate()));
                return ps;
            }
        }, holder);

        return holder.getKey().longValue();
    }

    @Override
    public void modify(Post post) {
        String sql = "UPDATE POST SET TITLE = ?, WRITER = ?, CONTENT = ?, WRITE_DATE = ? WHERE ID = ?";
        jdbcTemplate.update(sql, post.getTitle(), post.getWriter(), post.getContent(), post.getWriteDate(), post.getId());
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM POST WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }


    // TODO... 조금더 고민하기.. String으로 반환하는것이 최선임??
    private String convertToDateTime(java.util.Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(date);
    }
}
