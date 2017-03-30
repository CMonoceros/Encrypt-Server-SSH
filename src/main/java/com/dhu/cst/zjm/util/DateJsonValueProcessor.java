package com.dhu.cst.zjm.util;

/**
 * Created by zjm on 2017/3/8.
 */
import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {

    private String format;

    public DateJsonValueProcessor(String format) {
        this.format = format;
    }


    public Object processArrayValue(Object arg0, JsonConfig arg1) {
        // TODO Auto-generated method stub
        return null;
    }


    public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
        // TODO Auto-generated method stub
        if (arg1 == null) {
            return "";
        }
        if (arg1 instanceof java.sql.Timestamp) {
            String str = new SimpleDateFormat(format).format((java.sql.Timestamp) arg1);
            return str;
        }
        if (arg1 instanceof java.util.Date) {
            String str = new SimpleDateFormat(format)
                    .format((java.util.Date) arg1);
            return str;
        }
        return arg1.toString();
    }
}
