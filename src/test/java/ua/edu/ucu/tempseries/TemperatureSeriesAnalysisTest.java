package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis elems;

    @Before
    public void setUp() {
        elems = new TemperatureSeriesAnalysis(new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0});
    }

    @Test
    public void TestAverage() {
        double aver = elems.average();
        assertEquals(4.5, aver, 0.0001);
    }

    @Test
    public void TestDeviation() {
        double d = elems.deviation();
        assertEquals(2.2912878474779, d, 0.0001);
    }

    @Test
    public void TestMin() {
        double min = elems.min();
        assertEquals(1.0, min, 0.0001);
    }

    @Test
    public void TestMax() {
        double max = elems.max();
        assertEquals(8, max, 0.0001);
    }

    @Test
    public void TestFindTempClosestToZero() {
        double closest = elems.findTempClosestToZero();
        assertEquals(1.0, closest, 0.00001);
    }
    @Test
    public void TestSummaryStatics(){
        TempSummaryStatistics st = elems.summaryStatistics();
        assertEquals(4.5, st.getAvgTemp(), 0.0000001);
        assertEquals(2.2912878474779, st.getDevTemp(), 0.0000001);
        assertEquals(1.0, st.getMinTemp(), 0.0000001);
        assertEquals(8.0, st.getMaxTemp(), 0.0000001);
    }

    @Test
    public void TestFindTempClosestToValue() {
        double closest = elems.findTempClosestToValue(4.4);
        assertEquals(4, closest, 0.0001);
        closest = elems.findTempClosestToValue(0.05);
        assertEquals(1.0, closest, 0.001);
    }

    @Test
    public void TestFindTempsLessThen() {
        double[] less = elems.findTempsLessThen(6.0);
        assertArrayEquals(new double[]{1.0, 2.0, 3.0, 4.0, 5.0}, less, 0.0);
    }

    @Test
    public void TestFindTempsGreaterThen() {
        double[] greater = elems.findTempsGreaterThen(6.0);
        assertArrayEquals(new double[]{7.0, 8.0}, greater, 0.0);
    }


    @Test()
    public void TestAddTemps() {
        TemperatureSeriesAnalysis testAdd = new TemperatureSeriesAnalysis(new double[] {1, 2, 3});
        testAdd.addTemps(444444, 0.0004, 343);
        assertEquals(6, testAdd.getSize(), 0.0);
    }
}
