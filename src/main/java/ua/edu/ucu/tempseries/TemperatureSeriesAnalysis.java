package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int minTemp = -273;
    private double[] temp;
    private int capacity;
    private int size;

    public TemperatureSeriesAnalysis() {
        temp = new double[1];
        capacity = 1;
        size = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < minTemp) {
                throw new InputMismatchException();
            }
        }
        temp = temperatureSeries;
        size = temperatureSeries.length;
        capacity = size;
    }

    public double average() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double aver = 0;
        for (double i : temp) {
            aver += i;
        }
        return aver / size;
    }

    public double deviation() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (double elem : temp){
            sum += (elem - average()) * (elem - average());
        }
        return Math.sqrt(sum / size);
    }

    public double min() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double min = temp[0];
        for (double i: temp){
            if (i < min){
                min = i;
            }
        }
        return min;
    }

    public double max() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double max = temp[0];
        for (double i: temp){
            if (i > max){
                max = i;
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double closest = temp[0];
        double delta = temp[0] - tempValue;
        for (double elem : temp){
            if (Math.abs(elem - tempValue) < Math.abs(delta)){
                delta = elem - tempValue;
                closest = elem;
            }else if (Math.abs(elem - tempValue) == Math.abs(delta) && elem > 0){
                delta = elem - tempValue;
                closest = elem;
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double[] less = new double[size];
        int count = 0;
        for (double elem : temp){
            if (elem > tempValue){
                less[count] = elem;
                count += 1;
            }
        }
        return less;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        double[] greater = new double[size];
        int count = 0;
        for (double elem : temp){
            if (elem > tempValue){
                greater[count] = elem;
                count += 1;
            }
        }
        return greater;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        for (double elem : temps) {
            if (elem < minTemp) {
                throw new InputMismatchException();
            }
            if (capacity == size){
                capacity = capacity * 2;
                double[] temp2 = new double[capacity];
                System.arraycopy(temp, 0, temp2, 0, size);
                temp = temp2;
                temp[size] = elem;
            }else{
                temp[size] = elem;
            }
        }
        return size;
    }
}
