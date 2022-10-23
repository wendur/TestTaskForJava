package com.example.testtaskforjava.model;

import java.util.List;

public class Sequence {
    private List<Integer> numbers;
    private Integer maxValue;
    private Integer minValue;
    private Double median;
    private Double average;
    private List<List<Integer>> ascendingSequence;
    private List<List<Integer>> descendingSequence;

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public Double getMedian() {
        return median;
    }

    public Double getAverage() {
        return average;
    }

    public List<List<Integer>> getAscendingSequence() {
        return ascendingSequence;
    }

    public List<List<Integer>> getDescendingSequence() {
        return descendingSequence;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public void setMedian(Double median) {
        this.median = median;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public void setAscendingSequence(List<List<Integer>> ascendingSequence) {
        this.ascendingSequence = ascendingSequence;
    }

    public void setDescendingSequence(List<List<Integer>> descendingSequence) {
        this.descendingSequence = descendingSequence;
    }
}
