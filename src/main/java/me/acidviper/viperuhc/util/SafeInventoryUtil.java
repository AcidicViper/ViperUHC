package me.acidviper.viperuhc.util;

public class SafeInventoryUtil {
    public static int safestInventorySize(int i) {
        if (i % 9 == 0) return i;
        else {
            int b = 1;
            while (true) {
                if ((i + b) % 9 == 0) return i + b;
                b++;
            }
        }
    }
}
