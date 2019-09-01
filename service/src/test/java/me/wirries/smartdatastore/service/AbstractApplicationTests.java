package me.wirries.smartdatastore.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Abstract test class for all tests with the spring context.
 */
@SpringBootTest
@ActiveProfiles({"test", "noJobs"})
@RunWith(SpringRunner.class)
public abstract class AbstractApplicationTests {

}
