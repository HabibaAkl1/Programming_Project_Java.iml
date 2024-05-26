package de.thd.dwd_data_retriever.util;

public class FloatParser {

    public static Float parseFloat(String value) {
        float val = Float.parseFloat(value);
        if (val == -999.0f) {
            return null;
        } else {
            return val;
        }
    }
}
