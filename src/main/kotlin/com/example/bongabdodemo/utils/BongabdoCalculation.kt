package com.example.bongabdodemo.utils

import com.hellohasan.bongabdo.api.Bongabdo
import com.hellohasan.bongabdo.api.BongabdoMethod
import com.hellohasan.bongabdo.localization.BengaliLocalizationConfig
import com.hellohasan.bongabdo.localization.BongabdoLocalizationConfig
import com.hellohasan.bongabdo.localization.EnglishLocalizationConfig
import org.springframework.context.i18n.LocaleContextHolder
import java.text.SimpleDateFormat
import java.util.*

private const val DD_MMM_YYYY = "yyyy-MM-dd"

object BongabdoCalculation {

    /**
     * There are two available calculation methods: [BongabdoMethod.BANGLA_ACADEMY] and [BongabdoMethod.INDIAN_DRIK_SIDDHANTA] available in our library.
     * But you can extend [Bongabdo] class and implement your own logic inside [getBongabdoData] method.
     * Example: [SurjaShiddhantaBongabdo]
     */
    fun getBanglaAcademyBongabdo(calendar: Calendar): String {
        val bongabdo = Bongabdo.getInstance(BongabdoMethod.BANGLA_ACADEMY)
        bongabdo.localizationConfig = getLocalizationConfig()
        val bongabdoDate = bongabdo.getBongabdoData(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        return bongabdoDate.getFullDate()
    }

    fun getDrikShiddhantaBongabdo(calendar: Calendar): String {
        val bongabdo = Bongabdo.getInstance(BongabdoMethod.INDIAN_DRIK_SIDDHANTA)
        bongabdo.localizationConfig = getLocalizationConfig()
        val bongabdoDate = bongabdo.getBongabdoData(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        return bongabdoDate.getFullDate()
    }

    /**
     * You can implement your own localisation config class for Hindi, Arabic or any other language.
     * Just create a subclass of [BongabdoLocalizationConfig]. Then return it from this method.
     * Example: [HindiLocalisationConfig]
     * */
    private fun getLocalizationConfig(): BongabdoLocalizationConfig {
        val locale = LocaleContextHolder.getLocale()
        return if (locale.language == "bn")
            BengaliLocalizationConfig()
        else EnglishLocalizationConfig()
    }

    fun getDateString(calendar: Calendar): String {
        val formatter = SimpleDateFormat(DD_MMM_YYYY, Locale.getDefault())
        return formatter.format(calendar.time)
    }

    fun getCalendarFromString(dateString: String): Calendar {
        val formatter = SimpleDateFormat(DD_MMM_YYYY, Locale.getDefault())
        val date = formatter.parse(dateString)
        return Calendar.getInstance().apply {
            time = date ?: throw IllegalArgumentException("Invalid date format: $dateString")
        }
    }

}