package uz.pdp.anicinema.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;

@UtilityClass
public class DateUtils {

    private static final ZoneId asiaTashkent = ZoneId.of("Asia/Tashkent");

    public static Long toMillis(LocalDateTime localDateTime) {
        return localDateTime.atZone(asiaTashkent).toInstant().toEpochMilli();
    }

}
