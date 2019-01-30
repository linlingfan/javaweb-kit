package com.glinlf.growth;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author com.glinlf
 * @create 2018/6/01
 * <p>
 * unit test super class, use for be extended.
 * don't coding inside, you need extends it.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public abstract class WebTester {

    // 测试方法一
    public final static int TEST_USER_ID_1 = 1;
    public final static int TEST_USER_ID_2 = 2;

    protected final Principal p1 = mock(Principal.class);
    protected final Principal p2 = mock(Principal.class);

    //测试方法二 mock api 模拟http请求
    public static MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    public static RequestBuilder request;

    @Before
    public void setup() {
        //测试方法- 模拟request请求 setup mock MVC
        this.mockMvc = MockMvcBuilders
                // 整个环境做测试，包括Interceptor
                .webAppContextSetup(this.wac)
                .build();

        // 测试方法二 构造 Principal 传入相应Principal的参的直接请求 resource api中的方法
        when(p1.getName()).thenReturn(Integer.toString(TEST_USER_ID_1));
        when(p2.getName()).thenReturn(Integer.toString(TEST_USER_ID_2));
    }

    /**
     * request 构造统一方法 携带token 参数格式等
     *
     * @param method
     * @param uri
     * @return
     */
    public static MockHttpServletRequestBuilder requestKit(HttpMethod method, String uri) {

        return MockMvcRequestBuilders
                .request(method, uri)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE); // 文件扩展名 json

    }

    /**
     * @param method
     * @param uri
     * @param auth   校验头
     * @return
     */
    public static MockHttpServletRequestBuilder requestKit(HttpMethod method, String uri, String auth) {

        return MockMvcRequestBuilders
                .request(method, uri)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE) // 文件扩展名 json
                .header("Authorization", auth);
    }

}



