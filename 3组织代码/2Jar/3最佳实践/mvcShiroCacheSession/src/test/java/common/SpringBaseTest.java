package common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author : zhenyun.su
 * @since : 2018/9/4
 */

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class SpringBaseTest extends AbstractTestNGSpringContextTests {

}
