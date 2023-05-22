package com.truenorth.repository;

import com.truenorth.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends CrudRepository<Record, Long> {
    Page<Record> findByUserAndOperation(Long userId, Long operationId, Pageable pageable);

    Optional<Record> findByIdAndDeletedFalse(Long id);

    List<Record> findAllByDeletedFalse();
}
