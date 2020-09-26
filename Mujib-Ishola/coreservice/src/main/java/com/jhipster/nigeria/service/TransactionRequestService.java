package com.jhipster.nigeria.service;

import com.jhipster.nigeria.service.dto.TransactionRequestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.jhipster.nigeria.domain.TransactionRequest}.
 */
public interface TransactionRequestService {

    /**
     * Save a transactionRequest.
     *
     * @param transactionRequestDTO the entity to save.
     * @return the persisted entity.
     */
    TransactionRequestDTO save(TransactionRequestDTO transactionRequestDTO);

    /**
     * Get all the transactionRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TransactionRequestDTO> findAll(Pageable pageable);


    /**
     * Get the "id" transactionRequest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransactionRequestDTO> findOne(Long id);

    /**
     * Delete the "id" transactionRequest.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
