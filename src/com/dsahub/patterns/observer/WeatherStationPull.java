package com.dsahub.patterns.observer;

import java.util.ArrayList;

interface Observer {
    void update();
}

interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

/*****************************************************************************/

interface DisplayElement {
    void display();
}

/*****************************************************************************/

class WeatherDataPull implements Subject {
    private float temperature;
    private float humidity;
    private float pressure;
    private ArrayList<Observer> observers;

    public WeatherDataPull() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (var observer : observers) {
            observer.update();
        }
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

class CurrentConditionsDisplayPull implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private WeatherDataPull weatherData;

    public CurrentConditionsDisplayPull(WeatherDataPull weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        display();
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}

class StatisticsDisplayPull implements Observer, DisplayElement {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum= 0.0f;
    private int numReadings;
    private WeatherDataPull weatherDataPull;

    public StatisticsDisplayPull(WeatherDataPull weatherDataPull) {
        this.weatherDataPull = weatherDataPull;
        weatherDataPull.registerObserver(this);
    }

    @Override
    public void update() {
        var temperature = weatherDataPull.getTemperature();
        tempSum += temperature;
        numReadings++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }

        if (temperature < minTemp) {
            minTemp = temperature;
        }

        display();
    }

    @Override
    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
    }
}

class ForecastDisplayPull implements Observer, DisplayElement {
    private float currentPressure = 29.92f;
    private float lastPressure;
    private WeatherDataPull weatherData;

    public ForecastDisplayPull(WeatherDataPull weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update() {
        lastPressure = currentPressure;
        currentPressure = weatherData.getPressure();
        display();
    }

    @Override
    public void display() {
        System.out.print("Forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same");
        } else {
            System.out.println("Watch out for cooler, rainy weather");
        }
    }
}

public class WeatherStationPull {
    public static void main(String[] args) {
        WeatherDataPull weatherData = new WeatherDataPull();
        CurrentConditionsDisplayPull currentDisplay = new CurrentConditionsDisplayPull(weatherData);
        StatisticsDisplayPull StatisticsDisplayPull = new StatisticsDisplayPull(weatherData);
        ForecastDisplayPull ForecastDisplayPull = new ForecastDisplayPull(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
