package com.truenorth.service;

import com.truenorth.entity.Record;
import com.truenorth.repository.RecordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class RecordServiceTest {

    @Mock
    private RecordRepository recordRepository;

    private RecordService recordService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recordService = new RecordService(recordRepository);
    }

    @Test
    public void testGetRecordById() {
        Record record = new Record();
        record.setId(1L);
        record.setAmount(10);

        when(recordRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(record));

        Record result = recordService.getRecordById(1L);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals(10, result.getAmount());

        verify(recordRepository, times(1)).findByIdAndDeletedFalse(1L);
    }

    @Test
    public void testGetAllRecords() {
        List<Record> records = new ArrayList<>();
        Record rec1 = new Record();
        rec1.setId(1L);
        rec1.setAmount(10);
        records.add(rec1);
        Record rec2 = new Record(); rec2.setId(2L); rec2.setAmount(20);
        records.add(rec2);
        Page<Record> pages = new PageImpl<>(records);

        when(recordRepository.findByUserAndOperation(any(), any(), any())).thenReturn(pages);

        Page<Record> result = recordService.getRecords(1L, 1L, null);

        Assertions.assertEquals(2, result.getTotalElements());
        Assertions.assertEquals(pages, result);

        verify(recordRepository, times(1)).findByUserAndOperation(any(), any(), any());
    }
}