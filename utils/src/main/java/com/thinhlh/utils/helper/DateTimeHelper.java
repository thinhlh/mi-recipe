package com.thinhlh.utils.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by thinhlh on 3/1/2022.
 * Copyright 2022 (c)
 */
public abstract class DateTimeHelper {
    public static String getTimeFromMillis(Long millis) {
        final StringBuilder timeBuilder = new StringBuilder();

        // Minutes

        final int remainingMinutes = (int) TimeUnit.MILLISECONDS.toMinutes(millis);

        if (remainingMinutes < 10) {
            timeBuilder.append(0).append(remainingMinutes);
        } else {
            timeBuilder.append(remainingMinutes);
        }

        timeBuilder.append(":");

        // Seconds
        final int remainingSeconds = (int) TimeUnit.MILLISECONDS.toSeconds(millis);

        if (remainingSeconds < 10) {
            timeBuilder.append(0).append(remainingSeconds);
        } else {
            if (remainingSeconds == 60) {
                timeBuilder.append("00");
            } else {
                timeBuilder.append(remainingSeconds);
            }
        }

        return timeBuilder.toString();
    }

    /**
     * Convert current milliseconds to date
     * */
    public static String currentMillisToDate(String dateFormat) {
        final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    /**
     * Change date to new date with different date format
     */
    public static String changeFormat(String inDate, String inDateFormat, String outDateFormat) {
        final SimpleDateFormat inSdf = new SimpleDateFormat(inDateFormat, Locale.ENGLISH);

        if (inDate != null) {
            try {
                final Long parsedDateNullable = Objects.requireNonNull(inSdf.parse(inDate)).getTime();

                return new SimpleDateFormat(outDateFormat, Locale.ENGLISH).format(parsedDateNullable);

            } catch (ParseException e) {
                e.printStackTrace();
                return inDate;
            }
        } else {
            return "";
        }
    }
}
