package com.truenorth.service;

import com.truenorth.dto.NewRecordDTO;
import com.truenorth.entity.Operation;
import com.truenorth.entity.Record;
import com.truenorth.entity.User;
import com.truenorth.repository.OperationRepository;
import com.truenorth.repository.RecordRepository;
import com.truenorth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {
    private final RecordRepository recordRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private RandomStringService randomStringService;

    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public Record getRecordById(Long id) {
        return recordRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));
    }

    //@Transactional
    public Record createRecord(NewRecordDTO newRecordDTO) {
        Record record = new Record();
        record.setDeleted(false);
        Double amount = newRecordDTO.getAmount();
        String userName = newRecordDTO.getUserName();
        User user = userRepository.findByUsername(userName).get();
        Operation operation = operationRepository.findByType(Operation.Type.valueOf(newRecordDTO.getOperationType())).get();
        if (operation.getCost() <= user.getBalance()) {
            switch (operation.getType()) {
                case ADDITION:
                    record.setOperationResponse("Result: " + (user.getBalance() + amount));
                    break;
                case SUBTRACTION:
                    record.setOperationResponse("Result: " + (user.getBalance() - amount));
                    break;
                case MULTIPLICATION:
                    record.setOperationResponse("Result: " + (user.getBalance() * amount));
                    break;
                case DIVISION:
                    record.setOperationResponse("Result: " + (user.getBalance() / amount));
                    break;
                case SQUARE_ROOT:
                    record.setOperationResponse("Result: " + Math.sqrt(amount));
                    break;
                case RANDOM_STRING:
                    try {
                        record.setOperationResponse("Result: " + randomStringService.randomNumberBaseTenInt(
                                5, 1, 100).get(0));
                    } catch (Exception e) {
                        throw new RuntimeException("Error calling random numbers: " + e.getMessage());
                    }
            }
        }
        else {
            throw new RuntimeException("Operation cost is greater than user balance");
        }
        // Updates User balance a saves a new record
        double newBalance = user.getBalance() - operation.getCost();
        user.setBalance(newBalance);
        userRepository.save(user);
        record.setUserBalance(newBalance);
        record.setOperation(operation);
        record.setUser(user);
        if(newRecordDTO.getAmount() != null) {
            record.setAmount(amount);
        }
        return recordRepository.save(record);
    }

    public Record updateRecord(Long id, Record record) {
        Record existingRecord = getRecordById(id);
        existingRecord.setOperation(record.getOperation());
        existingRecord.setUser(record.getUser());
        existingRecord.setAmount(record.getAmount());
        existingRecord.setUserBalance(record.getUserBalance());
        existingRecord.setOperationResponse(record.getOperationResponse());
        existingRecord.setDate(record.getDate());
        return recordRepository.save(existingRecord);
    }

    public Record deleteRecord(Long id) {
        Record existingRecord = getRecordById(id);
        existingRecord.setDeleted(true); // Soft-delete the record
        return recordRepository.save(existingRecord);
    }

    public List<Record> getRecords(String userName) {
        Optional<User> userOptional = userRepository.findByUsername(userName);
        if(userOptional.isPresent()) {
            return recordRepository.findByUserAndDeletedFalse(userOptional.get());
        }
        return Collections.emptyList();
    }

    public List<Record> getAllRecords() {
        return (List<Record>) recordRepository.findAll();
    }

    public Page<Record> getRecords(long userId, long operationId, Pageable pageable) {
        return recordRepository.findByUserAndOperation(userId, operationId, pageable);
    }
}
