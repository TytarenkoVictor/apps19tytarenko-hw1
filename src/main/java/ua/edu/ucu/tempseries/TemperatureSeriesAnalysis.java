package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    public int size;
    private static final int minTemp = -273;
    private double[] tempArr;
    private int capacity;
    public TemperatureSeriesAnalysis() {
        tempArr = new double[1];
        capacity = 1;
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
        tempArr = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        capacity = tempArr.length * 2;
    }

    public double average() {
        if (tempArr.length == 0) {
            throw new IllegalArgumentException();
        }
        double aver = 0;
        for (double i : tempArr) {
            aver += i;
        }
        return aver / tempArr.length;
    }

    public double deviation() {
        if (tempArr.length == 0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (double elem : tempArr){
            sum += (double)Math.pow((elem - average()), 2);
        }
        return Math.sqrt(sum / tempArr.length);
    }

    public double min() {
        if (tempArr.length == 0) {
            throw new IllegalArgumentException();
        }
        double min = tempArr[0];
        for (double i: tempArr){
            if (i < min){
                min = i;
            }
        }
        return min;
    }

    public double max() {
        if (tempArr.length == 0) {
            throw new IllegalArgumentException();
        }
        double max = tempArr[0];
        for (double i: tempArr){
            if (i > max){
                max = i;
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        if (tempArr.length == 0) {
            throw new IllegalArgumentException();
        }
        double delta = tempArr[0];
        for (double elem : tempArr){
            if (Math.abs(elem) < Math.abs(delta)){
                delta = elem;
            }else if (Math.abs(elem) == Math.abs(delta) && elem > 0){
                delta = elem;
            }
        }
        return delta;
    }

    public double findTempClosestToValue(double tempValue) {
        if (tempArr.length == 0) {
            throw new IllegalArgumentException();
        }
        double delta = tempArr[0] - tempValue;
        for (double elem : tempArr){
            if (Math.abs(elem - tempValue) < Math.abs(delta)){
                delta = elem - tempValue;
            }else if (Math.abs(elem - tempValue) == Math.abs(delta) && elem > 0){
                delta = elem - tempValue;
            }
        }
        return delta - tempValue;
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] less = new double[tempArr.length];
        int count = 0;
        for (double elem : tempArr){
            if (elem < tempValue){
                less[count] = elem;
                count += 1;
            }
        }
        return less;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] greater = new double[tempArr.length];
        int count = 0;
        for (double elem : tempArr){
            if (elem > tempValue){
                greater[count] = elem;
                count += 1;
            }
        }
        return greater;
    }

    public TempSummaryStatistics summaryStatistics(double average, double deviation, double min, double max) {

        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double elem : temps) {
            if (elem < minTemp) {
                throw new InputMismatchException();
            }
            if (capacity == tempArr.length){
                capacity = capacity * 2;
                double[] tempArr2 = new double[capacity];
                System.arraycopy(tempArr2, 0, tempArr, tempArr.length, tempArr2.length);
                tempArr[tempArr.length] = elem;
            }else{
                tempArr[tempArr.length] = elem;
            }
        }
        return tempArr.length;
    }
}

