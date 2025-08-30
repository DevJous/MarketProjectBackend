package com.market.project.common.utils;

public final class StringUtils {

    private StringUtils() {
    }

    public static String Empty() {
        return "";
    }

    public static String GetSpFormat(String spName, int parametersCount) {
        StringBuilder parameters = new StringBuilder();

        if (parametersCount > 0) {
            parameters.append("(");
            for (int i = 0; i < parametersCount; i++) {
                parameters.append("?");
                if (i < parametersCount - 1) {
                    parameters.append(", ");
                }
            }
            parameters.append(")");
        }

        return String.format("{%s%s}", spName, parameters.toString());
    }
}
