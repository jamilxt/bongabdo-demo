package com.example.bongabdodemo.utils;

import com.hellohasan.bongabdo.api.Bongabdo;
import com.hellohasan.bongabdo.api.BongabdoMethod;
import com.hellohasan.bongabdo.localization.BengaliLocalizationConfig;
import com.hellohasan.bongabdo.localization.BongabdoLocalizationConfig;
import com.hellohasan.bongabdo.localization.EnglishLocalizationConfig;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BongabdoCalculation {

    private static final String DD_MMM_YYYY = "yyyy-MM-dd";

    /**
     * There are two available calculation methods: BANGLA_ACADEMY and INDIAN_DRIK_SIDDHANTA available in our library.
     * But you can extend the Bongabdo class and implement your own logic inside getBongabdoData method.
     * Example: SurjaShiddhantaBongabdo
     */
    public static String getBanglaAcademyBongabdo(Calendar calendar) {
        Bongabdo bongabdo = Bongabdo.getInstance(BongabdoMethod.BANGLA_ACADEMY);
        bongabdo.setLocalizationConfig(getLocalizationConfig());
        var bongabdoDate = bongabdo.getBongabdoData(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        return bongabdoDate.getFullDate();
    }

    public static String getDrikShiddhantaBongabdo(Calendar calendar) {
        Bongabdo bongabdo = Bongabdo.getInstance(BongabdoMethod.INDIAN_DRIK_SIDDHANTA);
        bongabdo.setLocalizationConfig(getLocalizationConfig());
        var bongabdoDate = bongabdo.getBongabdoData(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        return bongabdoDate.getFullDate();
    }

    /**
     * You can implement your own localization config class for Hindi, Arabic or any other language.
     * Just create a subclass of BongabdoLocalizationConfig. Then return it from this method.
     * Example: HindiLocalizationConfig
     */
    private static BongabdoLocalizationConfig getLocalizationConfig() {
        Locale locale = LocaleContextHolder.getLocale();
        if ("bn".equals(locale.getLanguage())) {
            return new BengaliLocalizationConfig();
        } else {
            return new EnglishLocalizationConfig();
        }
    }

    public static String getDateString(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat(DD_MMM_YYYY, Locale.getDefault());
        return formatter.format(calendar.getTime());
    }

    public static Calendar getCalendarFromString(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat(DD_MMM_YYYY, Locale.getDefault());
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(dateString));
            return calendar;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString);
        }
    }

}