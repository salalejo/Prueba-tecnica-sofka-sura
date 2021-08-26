package com.co.constructores.pruebatecnica.helpers;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class SetDates {

    public Date setInitialDate(Date date){
        if(date==null){
            date = new Date();
        }
        date = datePlusDays(date, 1);
        return date;
    }

    public Date setFinalDate(Date date, Integer daysToConstruct){
        date = datePlusDays(date, daysToConstruct);
        return date;
    }

    private Date datePlusDays(Date date,Integer daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,daysToAdd);
        date = calendar.getTime();
        return date;
    }

}
