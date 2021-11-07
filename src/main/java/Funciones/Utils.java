package Funciones;

import java.time.LocalDate;

public class Utils {

    public static String localDateToString(LocalDate d) {
        LocalDate next = d.plusDays(1);
        return "" + next.getDayOfMonth() + " de " + meses[next.getMonth().getValue()-1] + " del " + next.getYear();
    }

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

}
