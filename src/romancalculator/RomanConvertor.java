/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romancalculator;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DumitruDaniel
 */
public class RomanConvertor {

    static final HashMap<String, Integer> ROMAN_LIST = new HashMap() {
        {
            put("I", 1);
            put("V", 5);
            put("X", 10);
            put("L", 50);
            put("C", 100);
            put("D", 500);
            put("M", 1000);
        }
    };

    static int RomanToInt(String romanNumeral) {
        int result = 0;
        int current = 0;
        int next = 0;

        String[] elements = romanNumeral.split("");
        for (int i = 0; i < elements.length; i++) {
            current = ROMAN_LIST.get(elements[i]);
            if (i + 1 < elements.length) {
                next = ROMAN_LIST.get(elements[i + 1]);
            }
            if (current >= next) {
                result += current;
            } else {
                result -= current;
            }
            System.out.println("current:" + current + " next:" + next);
        }
        return result;
    }
}
