package Funciones;

import java.time.LocalDate;

public class StringDate {
    private static String[] meses = {
            "enero",
            "febrero",
            "marzo",
            "abril",
            "mayo",
            "junio",
            "julio",
            "agosto",
            "septiembre",
            "octubre",
            "noviembre",
            "diciembre"
    };

    public static LocalDate stringToLocalDate(String s) {
        int year = Integer.valueOf(s.substring(0, 4));
        int month = Integer.valueOf(s.substring(5, 7));
        int day = Integer.valueOf(s.substring(8, 10));
        return LocalDate.of(year, month, day);
    }

    public static String localDateToString(LocalDate d) {
        LocalDate next = d.plusDays(1);
        return "" + next.getDayOfMonth() + " de " + meses[next.getMonth().getValue()-1] + " del " + next.getYear();
    }
}
