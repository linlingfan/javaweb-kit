package com.glinlf.growth.log;

import com.glinlf.growth.NoRollbackTester;
import com.glinlf.growth.rest.IndexResource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author glinlf
 * @date 2019-04-10
 * @Description TODO
 **/
public class LogTest extends NoRollbackTester {


    private static Logger LOG = LoggerFactory.getLogger(LogTest.class);


    @Test
    public void test(){
        LOG.info("hello {}");
    }
}
