package com.truenorth.controller;

import com.truenorth.dto.NewRecordDTO;
import com.truenorth.entity.Record;
import com.truenorth.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {
    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping
    public List<Record> getRecordsByUser(@RequestParam(name = "userName") String userName) {
        return recordService.getRecords(userName);
    }

    @GetMapping("/{id}")
    public Record getRecordById(@PathVariable Long id) {
        return recordService.getRecordById(id);
    }

    @PostMapping("/newRecord")
    public ResponseEntity<Record> newRecord(@RequestBody NewRecordDTO newRecord) {
        try {
            Record recordCreated = recordService.createRecord(newRecord);
            return ResponseEntity.status(HttpStatus.CREATED).body(recordCreated);
        } catch (RuntimeException e) {
            if ("Operation cost is greater than user balance".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            throw e;
        }
    }

    @PutMapping("/{id}")
    public Record updateRecord(@PathVariable Long id, @RequestBody Record record) {
        return recordService.updateRecord(id, record);
    }

    @DeleteMapping("/{id}")
    public Record deleteRecord(@PathVariable Long id) {
        return recordService.deleteRecord(id);
    }
}
