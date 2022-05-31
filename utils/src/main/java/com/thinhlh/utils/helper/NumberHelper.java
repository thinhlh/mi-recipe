package com.thinhlh.utils.helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 */
public abstract class NumberHelper {
    private static int defNumAfterDecimal = 0;

    static void setDefaultNumberAfterDecimal(Integer numberAfterDecimal) {
        defNumAfterDecimal = numberAfterDecimal < 0 ? defNumAfterDecimal : numberAfterDecimal;
    }

    /**
     * Number with decimal format
     * <p>
     * <p>
     * <p>
     * ●	Use comma (,) for thousand delimeter.
     * ●	Use dot (.) for decimal delimiter.
     * ●	Ex : 123,456.789
     * ●	Right align.
     *
     * @param value
     * @return String
     */
    public static String decimalFormat(Double value, Integer numOfDecimal) {
        return String.format("%s", decimalFormal(numOfDecimal == null ? 0 : numOfDecimal).format(value));
    }

    private static NumberFormat decimalFormal(Integer numOfDecimal) {
        final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());

        final DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();

        // Use dot (.) for decimal delimiter.
        formatSymbols.setDecimalSeparator(',');
        // Use comma (,) for thousand delimiter (if it's over 1000).
        formatSymbols.setGroupingSeparator('.');
        // Apply symbol format
        ((DecimalFormat) numberFormat).setDecimalFormatSymbols(formatSymbols);
        numberFormat.setGroupingUsed(true);

        // Set fixed decimal.
        numberFormat.setMinimumFractionDigits(numOfDecimal);
        numberFormat.setMaximumFractionDigits(numOfDecimal);
        return numberFormat;
    }

}
