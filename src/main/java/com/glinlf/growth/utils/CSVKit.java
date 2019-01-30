package com.glinlf.growth.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * @author created by glinlf
 * @data 2019/01/30
 * @remark 读取csv文件工具类
 */
public class CSVKit {

    private static final Logger LOG = LoggerFactory.getLogger(CSVKit.class);

    /**
     * 读取某一行需要的数据
     * 普通 IO new File()读取文件
     * 相对路径是否好使，取决于user.dir的值  系统属性 user.dir是JVM启动的时候设置的.
     * 比如 多项目结构 user.dir 便为两个项目的根目录。/Users/linlingfan/Workspace/gitlab/java/oms
     * 结论：无论是相对路径，还是绝对路径都不是好的做法，能不使用就不要使用
     *
     * @return
     */
    public static List<String> readerCSVByFile(String filePath) throws FileNotFoundException {
        List<String> dataList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        return getCSVDataList(reader);
    }

    /**
     * 使用Class.getResource()或ClassLoader.getResource() 读取jar包中或者classpath下的资源文件
     * 结论：
     * 使用相对路径或绝对路径都能读取本jar或其他jar中的资源文件。
     * 但区别是，读取本jar包中的文件支持..这种写法，但是不能通过..读取其他jar下的文件。
     *
     * @throws Exception
     */
    public static List readerCVSListByClass(String fileUrl) throws IOException {
        URL url = CSVKit.class.getResource(fileUrl);
        BufferedReader br =
                new BufferedReader(
                        new InputStreamReader(url.openStream()));
        return getCSVDataList(br);
    }

    /**
     * 返回各个列的值
     * 注意 每个列可能不和其他列一一对应。 如某行 第一列有值 第二列没值
     *
     * @param fileUrl
     * @return
     * @throws IOException
     */
    public static Map<Object, List> readerCVSMapByClass(String fileUrl) throws IOException {
        URL url = CSVKit.class.getResource(fileUrl);
        BufferedReader br =
                new BufferedReader(
                        new InputStreamReader(url.openStream()));
        return getCSVDataMap(br);
    }

    /**
     * parse and  get csv
     *
     * @param br
     * @return 默认返回csv第一列数据
     */
    public static List getCSVDataList(BufferedReader br) {
        List dataList = new ArrayList<>();
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                Object item[] = line.split(",");
                Object needStr = item[0];
                dataList.add(needStr);
            }
            br.close();
        } catch (Exception e) {
            LOG.error("reader CSV data error : " + e);
        }
        return dataList;
    }

    // 将所有列都 解析出来？
    public static Map<Object, List> getCSVDataMap(BufferedReader br) {
        Map<Object, List> dataMap = new HashMap<>();
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                Object item[] = line.split(",");
                for (int index = 0; index < item.length; index++) {
                    List list = dataMap.get(index);
                    if (null == list) {
                        list = new ArrayList();
                    }
                    list.add(item[index]);
                    dataMap.put(index, list);
                }
            }
            br.close();
        } catch (Exception e) {
            LOG.error("reader CSV data error : " + e);
        }
        return dataMap;
    }

    public static void main(String[] args) {
        String line = "123123,";
        Object item[] = line.split(",");
        System.out.println(Arrays.toString(item));
    }

}
