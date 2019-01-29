package com.glinlf.growth;

import java.util.List;

public class Constants {

    /**
     * SchemaItemType 类型
     */
    public enum SchemaItemType {
        BOOLEAN("Boolean", Boolean.class),
        INTEGER("Integer", Integer.class),
        STRING("String", String.class),
        LIST("List", List.class),;
        private final String val;
        private final Class type;

        SchemaItemType(String val, Class type) {
            this.val = val;
            this.type = type;
        }

        public String getVal() {
            return val;
        }

        public Class getType() {
            return type;
        }

        public static SchemaItemType getSchemaItemType(String val) {
            SchemaItemType ret = null;
            final SchemaItemType[] vs = SchemaItemType.values();
            for (SchemaItemType v : vs) {
                if (v.getVal().equals(val)) {
                    ret = v;
                    break;
                }
            }
            return ret;
        }
    }

    /**
     * SchemaItemStatus 状态
     */
    public enum SchemaItemStatus {
        EDIT(0),
        FINISHED(1),;
        private final int val;

        SchemaItemStatus(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }


    /**
     * QuizItemType 类型
     */
    public enum QuizItemType {
        Single("single"),
        Multiple("multiple");
        private final String val;

        QuizItemType(String val) {
            this.val = val;
        }

        public String getVal() {
            return val;
        }

        public static QuizItemType getQuizItemType(String val) {
            QuizItemType ret = null;
            final QuizItemType[] vs = QuizItemType.values();
            for (QuizItemType v : vs) {
                if (v.getVal().equals(val)) {
                    ret = v;
                    break;
                }
            }
            return ret;
        }
    }
}
