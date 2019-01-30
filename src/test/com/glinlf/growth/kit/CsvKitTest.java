package com.glinlf.growth.kit;

import com.glinlf.growth.NoRollbackTester;
import com.glinlf.growth.util.CSVKit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author created by glinlf
 * @date 2019/1/30
 * @remark
 */
public class CsvKitTest extends NoRollbackTester {

    @Test
    public void test() {

    }

    @Test
    public void testKit() throws IOException {
        String url = "/data/list.csv";
        Map<Object, List> data = CSVKit.readerCVSMapByClass(url);
        System.out.println(data);
        System.out.println(data.get(0));

        List list = CSVKit.readerCVSListByClass(url);
        System.out.println(list);
    }
}
