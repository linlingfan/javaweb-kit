package com.glinlf.growth;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


/**
 * @author com.glinlf
 * @create 2018/8/20
 * <p>
 * test environment without database rollback feature.
 * don't coding inside, you need extends it.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(value = false)
public abstract class NoRollbackTester extends WebTester {

}
