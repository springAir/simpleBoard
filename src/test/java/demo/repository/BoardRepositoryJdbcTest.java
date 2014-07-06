package demo.repository;

import demo.Application;
import demo.model.Board;
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
public class BoardRepositoryJdbcTest {

    @Autowired
    private BoardRepositoryJdbc boardRepositoryJdbc;

    private List<Board> boardFixtureList;

    @Before
    public void setUp(){
        //fixture를 설정.
        boardFixtureList = new ArrayList<>();
        boardFixtureList.add(generateBoard("Sejong Park", "testTitle1", "content1", new Date()));
        boardFixtureList.add(generateBoard("Sejong Park", "testTitle2", "content2", new Date()));
        boardFixtureList.add(generateBoard("Sejong Park", "testTitle3", "content3", new Date()));
        boardFixtureList.add(generateBoard("Sejong Park", "testTitle4", "content4", new Date()));
        boardFixtureList.add(generateBoard("Sejong Park", "testTitle5", "content5", new Date()));
    }

    @Test
    public void testWriteGetAndCount() throws Exception {
        //처음 count 는 0
        assertThat(boardRepositoryJdbc.count(), is(0L));
        boardRepositoryJdbc.write(boardFixtureList.get(0));
        boardRepositoryJdbc.write(boardFixtureList.get(1));

        Board boardFromFixture = boardFixtureList.get(2);
        long boardFixtureWriteId = boardRepositoryJdbc.write(boardFromFixture);
        //count가 3인걸 확인
        assertThat(boardRepositoryJdbc.count(), is(3L));

        Board boardFromDB = boardRepositoryJdbc.find(boardFixtureWriteId);
        assertForOneBoard(boardFromFixture, boardFromDB);
    }


    @Test
    public void testGetList() throws Exception {
        boardRepositoryJdbc.write(boardFixtureList.get(0));
        boardRepositoryJdbc.write(boardFixtureList.get(1));
        boardRepositoryJdbc.write(boardFixtureList.get(2));

        assertThat(boardRepositoryJdbc.count(), is(3L));

        List<Board> resultBoardList = boardRepositoryJdbc.findPage();

        //가장 나중에 등록된 게시물이 제일 위에 나와야 함.
        assertForListBoard(resultBoardList.get(0), boardFixtureList.get(2));
    }

    @Test
    public void testUpdate() throws Exception {
        long board1Id = boardRepositoryJdbc.write(boardFixtureList.get(0));
        long board2Id = boardRepositoryJdbc.write(boardFixtureList.get(1));

        Board updateBoard = boardFixtureList.get(4);
        updateBoard.setId(board2Id);

        boardRepositoryJdbc.modify(updateBoard);

        //기존의 데이터가 변경되어서는 안됨.
        assertForOneBoard(boardRepositoryJdbc.find(board1Id), boardFixtureList.get(0));
        //신규 입력 데이터가 정상적으로 변경되었는지 확인.
        assertForOneBoard(boardRepositoryJdbc.find(board2Id), updateBoard);
    }

    @Test
    public void testDelete() throws Exception {
        long board1Id = boardRepositoryJdbc.write(boardFixtureList.get(0));
        long board2Id = boardRepositoryJdbc.write(boardFixtureList.get(1));

        assertThat(boardRepositoryJdbc.count(), is(2L));

        boardRepositoryJdbc.delete(board1Id);

        //삭제후 카운트가 1이 되는지 확인.
        assertThat(boardRepositoryJdbc.count(), is(1L));
        //삭제작업이 다른 데이터에 영향을 끼치는지 확인.
        assertForOneBoard(boardRepositoryJdbc.find(board2Id), boardFixtureList.get(1));
    }


    private void assertForListBoard(Board board1, Board board2) {
        assertThat(board1.getTitle(), is(board2.getTitle()));
        assertThat(board1.getName(), is(board2.getName()));
        assertThat(board1.getWriteDate().getTime(), is(board2.getWriteDate().getTime()));
    }

    private void assertForOneBoard(Board board1, Board board2) {
        assertThat(board1.getTitle(), is(board2.getTitle()));
        assertThat(board1.getContent(), is(board2.getContent()));
        assertThat(board1.getName(), is(board2.getName()));
        assertThat(board1.getWriteDate().getTime(), is(board2.getWriteDate().getTime()));
    }

    //fixture 생성.
    private Board generateBoard(String name, String title, String content, Date writeDate){
        Board board = new Board();
        board.setTitle(title);
        board.setName(name);
        board.setWriteDate(writeDate);
        board.setContent(content);
        return board;
    }
}