package kz.mn.partners.mnp.v1.biletter.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class Utils {

    public BigDecimal calculatePrice(int row, int seat) {
        int placeIndex = row * seat;

        if (placeIndex <= 10_000) {
            return BigDecimal.valueOf(40_000);
        } else if (placeIndex <= 25_000) {
            return BigDecimal.valueOf(80_000);
        } else if (placeIndex <= 45_000) {
            return BigDecimal.valueOf(120_000);
        } else if (placeIndex <= 70_000) {
            return BigDecimal.valueOf(160_000);
        } else {
            return BigDecimal.valueOf(200_000);
        }
    }

}
