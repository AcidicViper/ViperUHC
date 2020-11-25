package me.acidviper.viperuhc.util;

public class StringUtil {
    public static String capitalize(String str) { return ((str == null || str.isEmpty()) ? str : str.substring(0, 1).toUpperCase() + str.substring(1)); }
}
