package demo.service;

import demo.ApplicationTests;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
//TODO 아직까지 특별한 로직이 없으므로 생략한다.
@Ignore
public class PostServiceImplTest {
    @Autowired
    BoardServiceImpl boardServiceImpl;

}