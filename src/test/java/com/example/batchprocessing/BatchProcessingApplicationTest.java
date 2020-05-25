package com.example.batchprocessing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class
})
public class BatchProcessingApplicationTest {

    @Test
    @ExpectedDatabase(
        value="/dbunit/expectedDataset1.xml",
        assertionMode=DatabaseAssertionMode.NON_STRICT
    )
    public void test() throws Exception{
        BatchProcessingApplication.main(new String[] {""});
    }


}