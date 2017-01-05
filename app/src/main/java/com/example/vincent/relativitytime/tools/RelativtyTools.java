package com.example.vincent.relativitytime.tools;

public class RelativtyTools {

    public static final double SPEED_OF_LIGHT = 299792458; // m/s
    public static final double LIGHT_YEAR = 9.4607 * Math.pow(10, 15); // m

    enum SpeedMetric {
        KMH,
        MPH,
        SI,
        LIGHT_NORMALIZED
    }

    public static double convertSpeedFromSI(double speed, SpeedMetric outputMetric) throws ArithmeticException {

        if (speed < 0) {
            throw new ArithmeticException("Speed value can't be negative");
        }

        double convertedSpeed = -1;

        switch (outputMetric) {
            case KMH:
                convertedSpeed = 1000 / 3600 * speed;
                break;
            case MPH:
                convertedSpeed = 1609.34 / 3600 * speed;
                break;
            case SI:
                convertedSpeed = speed;
                break;
            case LIGHT_NORMALIZED:
                convertedSpeed = SPEED_OF_LIGHT * speed;
                break;
        }
        return convertedSpeed;
    }

    public static double convertSpeedToSI(double speed, SpeedMetric outputMetric) throws ArithmeticException {

        if (speed < 0) {
            throw new ArithmeticException("Speed value can't be negative");
        }

        double convertedSpeed = -1;

        switch (outputMetric) {
            case KMH:
                convertedSpeed = 3600 / 1000 * speed;
                break;
            case MPH:
                convertedSpeed = 3600 / 1609.34 * speed;
                break;
            case SI:
                convertedSpeed = speed;
                break;
            case LIGHT_NORMALIZED:
                convertedSpeed = 1 / SPEED_OF_LIGHT * speed;
                break;
        }
        return  convertedSpeed;
    }

    public static double convertSpeed(double speed, SpeedMetric inputMetric, SpeedMetric outputMetric) throws ArithmeticException {
        return convertSpeedFromSI(convertSpeedToSI(speed, inputMetric), outputMetric);
    }

    public static double gamma(double speed, SpeedMetric metric) throws ArithmeticException {
        if (speed < 0 && convertSpeedToSI(speed, metric) >= SPEED_OF_LIGHT) {
            throw new ArithmeticException("Speed value can't be negative");
        }
        return 1 / Math.sqrt(1 - Math.pow(speed, 2)/ Math.pow(convertSpeedFromSI(SPEED_OF_LIGHT, metric), 2));
    }
    public static double gamma(double speed) throws ArithmeticException {
        return gamma(speed, SpeedMetric.SI);
    }

    public static double getProperTime(double speed, double time) {
        return time / gamma(speed);
    }

    public static double getTime(double speed, double properTime) {
        return properTime * gamma(speed);
    }
}
