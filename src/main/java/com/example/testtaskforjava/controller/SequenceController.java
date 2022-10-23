package com.example.testtaskforjava.controller;

import com.example.testtaskforjava.model.FileString;
import com.example.testtaskforjava.model.OperString;
import com.example.testtaskforjava.service.SequenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "Контроллер для работы с последовательность чисел.")
public class SequenceController {
    private final SequenceService sequenceService;

    @Autowired
    public SequenceController(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    @PostMapping(value = "/sequence")
    @ApiOperation("Передача файла с числами.")
    public ResponseEntity<?> create(@RequestBody FileString file_path) {
        sequenceService.create(file_path);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/operation")
    @ApiOperation("POST-запросы на операции по вычислению чисел.")
    public ResponseEntity<?> operation(@RequestBody OperString str) {
        return switch (str.getOperation()) {
            case "max_value" -> readMax();
            case "min_value" -> readMin();
            case "median" -> readMedian();
            case "average" -> readAverage();
            case "ascseq" -> readAscSeq();
            case "descseq" -> readDescSeq();
            default -> new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        };
    }

    @GetMapping(value = "/max_value")
    @ApiOperation("Получение максимального значения.")
    public ResponseEntity<Integer> readMax() {
        final Integer num = sequenceService.readMaxValue();
        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    @GetMapping(value = "/min_value")
    @ApiOperation("Получение минимального значения.")
    public ResponseEntity<Integer> readMin() {
        final Integer num = sequenceService.readMinValue();
        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    @GetMapping(value = "/median")
    @ApiOperation("Получение медианы.")
    public ResponseEntity<Double> readMedian() {
        final Double num = sequenceService.readMedian();
        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    @GetMapping(value = "/average")
    @ApiOperation("Получение арифметического среднего значения.")
    public ResponseEntity<Double> readAverage() {
        final Double num = sequenceService.readAverage();
        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    @GetMapping(value = "/ascseq")
    @ApiOperation("Получение максимальной последовательности возрастающих чисел.")
    public ResponseEntity<List<List<Integer>>> readAscSeq() {
        final List<List<Integer>> nums = sequenceService.readAscSeq();
        return new ResponseEntity<>(nums, HttpStatus.OK);
    }

    @GetMapping(value = "/descseq")
    @ApiOperation("Получение максимальной последовательности убывающих чисел.")
    public ResponseEntity<List<List<Integer>>> readDescSeq() {
        final List<List<Integer>> nums = sequenceService.readDescSeq();
        return new ResponseEntity<>(nums, HttpStatus.OK);
    }
}
