package uz.pdp.anicinema.utils;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@UtilityClass
public class DateUtils {

    private static final ZoneId asiaTashkent = ZoneId.of("Asia/Tashkent");

    public static Long toMillis(LocalDateTime localDateTime) {
        return localDateTime.atZone(asiaTashkent).toInstant().toEpochMilli();
    }

    public static LocalDateTime toLocalDateTime(Long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), asiaTashkent);
    }

}
