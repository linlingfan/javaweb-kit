package com.glinlf.growth.kit;

import com.glinlf.growth.NoRollbackTester;
import com.glinlf.growth.utils.CSVKit;
import com.univocity.parsers.common.processor.ColumnProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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

    public Reader getReader(String relativePath) throws UnsupportedEncodingException {
        return new InputStreamReader(this.getClass().getResourceAsStream(relativePath), "UTF-8");
    }

    /**
     * 使用 uniVocity-parsers
     * 按行读取
     * 也可以映射成自定义定义的对象
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testCsvParserByRow() throws UnsupportedEncodingException {

        CsvParserSettings settings = new CsvParserSettings();
        // mac /n 换行
        settings.getFormat().setLineSeparator("\n");
        // 创建CSV解析器
        CsvParser parser = new CsvParser(settings);
        // 一行语句处理所有行
        List<String[]> allRows = parser.parseAll(getReader("/data/list.csv"));
        System.out.println(allRows);
//        System.out.println(Arrays.toString(allRows.get(0)));
    }

    @Test
    public void testCsvParserByColumn() throws UnsupportedEncodingException {

        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.getFormat().setLineSeparator("\n");
        parserSettings.setHeaderExtractionEnabled(true);
        // 使用列处理器读取所有列的值
        ColumnProcessor rowProcessor = new ColumnProcessor();
        parserSettings.setRowProcessor(rowProcessor);
        CsvParser parser = new CsvParser(parserSettings);
        // 送入列处理器
        parser.parse(getReader("/data/list.csv"));
        Map<String, List<String>> columnValues = rowProcessor.getColumnValuesAsMapOfNames();
        System.out.println(columnValues);
        List<String> columns = rowProcessor.getColumn(0);
        System.out.println(columns);
    }
}
