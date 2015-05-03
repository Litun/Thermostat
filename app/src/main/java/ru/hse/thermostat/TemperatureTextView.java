package ru.hse.thermostat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Litun on 04.05.2015.
 */
public class TemperatureTextView extends TextView {
    private float temperature = 0f;
    private boolean fahrenheit;
    private final String CELSIUS = "°C",
            FAHREBHEIT = "°F";

    public TemperatureTextView(Context context) {
        super(context);
    }

    public TemperatureTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TemperatureTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TemperatureTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public boolean isFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(boolean f) {
        fahrenheit = f;
    }

    public void setTemperature(float t) {
        temperature = t;
        setText(String.format("%.1f", temperature) +
                (fahrenheit ? FAHREBHEIT : CELSIUS));
    }

    public float getTemperature() {
        return temperature;
    }


    public void setTemperature(TemperatureTextView t) {
//        [°C] = ([°F] - 32) × 5/9
//        [°F] = [°C] × 9/5 + 32

        if (fahrenheit) {
            if (t.isFahrenheit()) {
                setText(t.getText());
                temperature = t.getTemperature();
            } else {
                float fahrenheitT = t.getTemperature() * 9 / 5 + 32f;
                setTemperature(fahrenheitT);
            }
        } else {
            if (t.isFahrenheit()) {
                float celsiusT = (t.getTemperature() - 32f) * 5 / 9;
                setTemperature(celsiusT);
            } else {
                setText(t.getText());
                temperature = t.getTemperature();
            }
        }
    }
}
