package com.jhipster.nigeria.repository;

import com.jhipster.nigeria.domain.TransactionRequest;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TransactionRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionRequestRepository extends JpaRepository<TransactionRequest, Long>, JpaSpecificationExecutor<TransactionRequest> {
}
