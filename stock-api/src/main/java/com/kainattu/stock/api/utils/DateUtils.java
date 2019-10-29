package com.kainattu.stock.api.utils;

import java.util.Calendar;

public class DateUtils {

    private DateUtils() {

    }

  public   static Integer getCurrentYear() {

        return Calendar.getInstance().get(Calendar.YEAR);
    }


   public static boolean isFirstQuetorCompleted() {
        return 3 > Calendar.getInstance().get(Calendar.MONTH);
    }

}
