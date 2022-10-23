package com.example.testtaskforjava.service;

import com.example.testtaskforjava.model.FileString;
import com.example.testtaskforjava.model.Sequence;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class SequenceService {
    private static final Sequence SEQUENCE = new Sequence();

    public void create(FileString filePath) {
        List<Integer> numbers = new ArrayList<>();
        try {
            FileReader fr = new FileReader(filePath.getFile_path());
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                numbers.add(Integer.valueOf(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SEQUENCE.setNumbers(numbers);
        calculate();
    }

    public void calculate() {
        List<Integer> numbers = SEQUENCE.getNumbers();
        int size = numbers.size();

        List<List<Integer>> asc = new ArrayList<>();
        List<List<Integer>> desc = new ArrayList<>();

        Integer sum = numbers.get(0);

        List<Integer> tmpAsc = new ArrayList<>();
        tmpAsc.add(numbers.get(0));
        List<Integer> tmpDesc = new ArrayList<>();
        tmpDesc.add(numbers.get(0));

        int maxLengthAsc = 1;
        int maxLengthDesc = 1;

        for (int i = 1; i < size; i++) {
            if (numbers.get(i) > numbers.get(i-1)) {
                tmpAsc.add(numbers.get(i));
                if (tmpDesc.size() > maxLengthDesc) {
                    desc = new ArrayList<>();
                    maxLengthDesc = tmpDesc.size();
                    desc.add(tmpDesc);
                } else if (tmpDesc.size() == maxLengthDesc) {
                    desc.add(tmpDesc);
                }
                tmpDesc = new ArrayList<>();
                tmpDesc.add(numbers.get(i));
            } else if (numbers.get(i) < numbers.get(i - 1)) {
                tmpDesc.add(numbers.get(i));
                if (tmpAsc.size() > maxLengthAsc) {
                    asc = new ArrayList<>();
                    maxLengthAsc = tmpAsc.size();
                    asc.add(tmpAsc);
                } else if (tmpAsc.size() == maxLengthAsc) {
                    asc.add(tmpAsc);
                }
                tmpAsc = new ArrayList<>();
                tmpAsc.add(numbers.get(i));
            }
            sum += numbers.get(i);
        }

        if (tmpDesc.size() > maxLengthDesc) {
            desc = new ArrayList<>();
            desc.add(tmpDesc);
        } else if (tmpDesc.size() == maxLengthDesc) {
            desc.add(tmpDesc);
        }

        if (tmpAsc.size() > maxLengthAsc) {
            asc = new ArrayList<>();
            asc.add(tmpAsc);
        } else if (tmpAsc.size() == maxLengthAsc) {
            asc.add(tmpAsc);
        }


        Collections.sort(numbers);
        SEQUENCE.setMinValue(numbers.get(0));
        SEQUENCE.setMaxValue(numbers.get(size-1));
        Double median;
        if (size % 2 == 0)
           median = (double) ((numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2);
        else
            median = Double.valueOf(numbers.get(size / 2));
        SEQUENCE.setMedian(median);
        SEQUENCE.setAverage((double) (sum / size));
        SEQUENCE.setAscendingSequence(asc);
        SEQUENCE.setDescendingSequence(desc);
    }

    public Integer readMaxValue() {
        return SEQUENCE.getMaxValue();
    }

    public Integer readMinValue() {
        return SEQUENCE.getMinValue();
    }

    public Double readMedian() {
        return SEQUENCE.getMedian();
    }

    public Double readAverage() {
        return SEQUENCE.getAverage();
    }

    public List<List<Integer>> readAscSeq() {
        return SEQUENCE.getAscendingSequence();
    }

    public List<List<Integer>> readDescSeq() {
        return SEQUENCE.getDescendingSequence();
    }
}
