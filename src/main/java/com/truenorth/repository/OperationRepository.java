package com.truenorth.repository;

import com.truenorth.entity.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {
    Optional<Operation> findByType(Operation.Type type);
}
