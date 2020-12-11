package com.cjc.chatchat;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
class ChatChatApplicationTests {


    @Autowired
    private DataSource dataSource;

    private Logger logger = LoggerFactory.getLogger(ChatChatApplicationTests.class);

    @Test
    public void testDataSource(){

        logger.info(dataSource.toString());

    }

}
