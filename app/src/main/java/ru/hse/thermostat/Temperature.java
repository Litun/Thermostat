package ru.hse.thermostat;

/**
 * Created by Litun on 31.05.2015.
 */
public class Temperature {
    Float celsius = 0f,
            fahrenheit = 0f;

    public void setFahrenheit(Float f) {
        fahrenheit = f;
        celsius = (f - 32) * 5 / 9;
    }

    public void setCelsius(Float c) {
//        [°C] = ([°F] - 32) ? 5/9
//        [°F] = [°C] ? 9/5 + 32
        if (c < 5)
            c = 5f;
        if (c > 30)
            c = 30f;

        celsius = c;
        fahrenheit = c * 9 / 5 + 32;
    }

    public Float getCelsius() {
        return celsius;
    }

    public Float getFahrenheit() {
        return fahrenheit;
    }

    public void setOnPersentage(int percentage) {
        //5 - 30
        setCelsius(((float) percentage) / 4 + 5f);
    }
}
