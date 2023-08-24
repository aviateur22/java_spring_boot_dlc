package com.ctoutweb.dlc.helper;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;
@Component
public class DateHelper {
    public long getNumberOfDay(Date oldestDate, Date newestDate, TimeUnit timeUnit) {

        long diffInMillies = oldestDate.getTime() - newestDate.getTime();

        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}
