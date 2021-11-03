package Funciones;

import java.time.LocalDate;

public class StringDate {
    public static LocalDate stringToLocalDate(String s) {
        int year = Integer.valueOf(s.substring(0, 4));
        int month = Integer.valueOf(s.substring(5, 7));
        int day = Integer.valueOf(s.substring(8, 10));
        return LocalDate.of(year, month, day);
    }

    public static String localDateToString(LocalDate d) {
        LocalDate next = d.plusDays(1);
        return "" + next.getDayOfMonth() + "/" + next.getMonth().getValue() + "/" + next.getYear();
    }
}
