package com.glinlf.growth.rest;

import com.glinlf.growth.NoRollbackTester;
import com.glinlf.growth.WebTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

/**
 * @author created by glinlf
 * @date 2019/1/30
 * @remark
 */
public class IndexResourceTest extends NoRollbackTester {

    @Autowired
    IndexResource ir;

    @Test
    public void test(){

    }

    @Test
    public void testIndex() throws Exception {

        WebTester.mockMvc
                .perform(requestKit(HttpMethod.GET, "/index")).andReturn();
    }
}
