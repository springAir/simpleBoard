package demo.repository;

import demo.Application;
import demo.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class PostRepositoryJdbcTest {

    @Autowired
    private PostRepositoryJdbc postRepositoryJdbc;

    private List<Post> postFixtureList;

    @Before
    public void setUp(){
        //fixture를 설정.
        postFixtureList = new ArrayList<>();
        postFixtureList.add(generatePost("Sejong Park", "testTitle1", "content1", new Date()));
        postFixtureList.add(generatePost("Sejong Park", "testTitle2", "content2", new Date()));
        postFixtureList.add(generatePost("Sejong Park", "testTitle3", "content3", new Date()));
        postFixtureList.add(generatePost("Sejong Park", "testTitle4", "content4", new Date()));
        postFixtureList.add(generatePost("Sejong Park", "testTitle5", "content5", new Date()));
    }

    @Test
    public void testWriteGetAndCount() throws Exception {
        //처음 count 는 0
        assertThat(postRepositoryJdbc.count(), is(0L));
        postRepositoryJdbc.add(postFixtureList.get(0));
        postRepositoryJdbc.add(postFixtureList.get(1));

        Post postFromFixture = postFixtureList.get(2);
        long postFixtureWriteId = postRepositoryJdbc.add(postFromFixture);
        //count가 3인걸 확인
        assertThat(postRepositoryJdbc.count(), is(3L));

        Post postFromDB = postRepositoryJdbc.find(postFixtureWriteId);
        assertForOnePost(postFromFixture, postFromDB);
    }


    @Test
    public void testGetList() throws Exception {
        postRepositoryJdbc.add(postFixtureList.get(0));
        postRepositoryJdbc.add(postFixtureList.get(1));
        postRepositoryJdbc.add(postFixtureList.get(2));

        assertThat(postRepositoryJdbc.count(), is(3L));

        List<Post> resultPostList = postRepositoryJdbc.findPage();

        //가장 나중에 등록된 게시물이 제일 위에 나와야 함.
        assertForListPost(resultPostList.get(0), postFixtureList.get(2));
    }

    @Test
    public void testUpdate() throws Exception {
        long post1Id = postRepositoryJdbc.add(postFixtureList.get(0));
        long post2Id = postRepositoryJdbc.add(postFixtureList.get(1));

        Post updatePost = postFixtureList.get(4);
        updatePost.setId(post2Id);

        postRepositoryJdbc.modify(updatePost);

        //기존의 데이터가 변경되어서는 안됨.
        assertForOnePost(postRepositoryJdbc.find(post1Id), postFixtureList.get(0));
        //신규 입력 데이터가 정상적으로 변경되었는지 확인.
        assertForOnePost(postRepositoryJdbc.find(post2Id), updatePost);
    }

    @Test
    public void testDelete() throws Exception {
        long post1Id = postRepositoryJdbc.add(postFixtureList.get(0));
        long post2Id = postRepositoryJdbc.add(postFixtureList.get(1));

        assertThat(postRepositoryJdbc.count(), is(2L));

        postRepositoryJdbc.delete(post1Id);

        //삭제후 카운트가 1이 되는지 확인.
        assertThat(postRepositoryJdbc.count(), is(1L));
        //삭제작업이 다른 데이터에 영향을 끼치는지 확인.
        assertForOnePost(postRepositoryJdbc.find(post2Id), postFixtureList.get(1));
    }


    private void assertForListPost(Post post1, Post post2) {
        assertThat(post1.getTitle(), is(post2.getTitle()));
        assertThat(post1.getWriter(), is(post2.getWriter()));
        assertThat(post1.getWriteDate().getTime(), is(post2.getWriteDate().getTime()));
    }

    private void assertForOnePost(Post post1, Post post2) {
        assertThat(post1.getTitle(), is(post2.getTitle()));
        assertThat(post1.getContent(), is(post2.getContent()));
        assertThat(post1.getWriter(), is(post2.getWriter()));
        assertThat(post1.getWriteDate().getTime(), is(post2.getWriteDate().getTime()));
    }

    //fixture 생성.
    private Post generatePost(String name, String title, String content, Date writeDate){
        Post post = new Post();
        post.setTitle(title);
        post.setWriter(name);
        post.setWriteDate(writeDate);
        post.setContent(content);
        return post;
    }
}