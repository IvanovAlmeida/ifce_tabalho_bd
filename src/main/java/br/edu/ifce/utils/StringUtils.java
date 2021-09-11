package br.edu.ifce.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class StringUtils {
    public static String FormatForMoney(BigDecimal value) {
        Locale ptBr = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(ptBr).format(value == null ? 0 : value);
    }
}
