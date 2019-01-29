package com.glinlf.growth;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author com.glinlf
 * @create 2018/8/20
 * <p>
 * test environment with database rollback feature.
 * don't coding inside, you need extends it.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback
@Transactional
public abstract class RollbackTester extends WebTester {
}
