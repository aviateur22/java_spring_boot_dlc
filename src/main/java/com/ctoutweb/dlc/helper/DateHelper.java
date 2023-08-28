package com.ctoutweb.dlc.helper;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
@Component
public class DateHelper {
    public long getNumberOfDay(Date refDate, TimeUnit timeUnit, boolean isOpenTime) {
        Date actualDate = new Date();
        Calendar refDateCalendar = Calendar.getInstance();
        Calendar actualDateCalendar = Calendar.getInstance();
        refDateCalendar.setTime(refDate);

        actualDateCalendar.setTime(actualDate);
        actualDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        actualDateCalendar.set(Calendar.MINUTE, 59);
        actualDateCalendar.set(Calendar.SECOND, 59);
        actualDateCalendar.set(Calendar.MILLISECOND, 0);

        if(isOpenTime) {
            refDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
            refDateCalendar.set(Calendar.MINUTE, 1);
            refDateCalendar.set(Calendar.SECOND,1);
            refDateCalendar.set(Calendar.MILLISECOND, 0);
            System.out.println(refDateCalendar.getTime());
        }  else {
            if(actualDateCalendar.getTime().getTime() > refDateCalendar.getTime().getTime()){
                return -1;
            }
            refDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            refDateCalendar.set(Calendar.MINUTE, 59);
            refDateCalendar.set(Calendar.SECOND,59);
            refDateCalendar.set(Calendar.MILLISECOND, 59);
        }
        long diffInMillies = refDateCalendar.getTime().getTime() - actualDateCalendar.getTime().getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}
