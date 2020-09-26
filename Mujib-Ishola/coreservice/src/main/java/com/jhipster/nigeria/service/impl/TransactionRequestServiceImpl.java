package com.jhipster.nigeria.service.impl;

import com.jhipster.nigeria.service.TransactionRequestService;
import com.jhipster.nigeria.domain.TransactionRequest;
import com.jhipster.nigeria.repository.TransactionRequestRepository;
import com.jhipster.nigeria.service.dto.TransactionRequestDTO;
import com.jhipster.nigeria.service.mapper.TransactionRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TransactionRequest}.
 */
@Service
@Transactional
public class TransactionRequestServiceImpl implements TransactionRequestService {

    private final Logger log = LoggerFactory.getLogger(TransactionRequestServiceImpl.class);

    private final TransactionRequestRepository transactionRequestRepository;

    private final TransactionRequestMapper transactionRequestMapper;

    public TransactionRequestServiceImpl(TransactionRequestRepository transactionRequestRepository, TransactionRequestMapper transactionRequestMapper) {
        this.transactionRequestRepository = transactionRequestRepository;
        this.transactionRequestMapper = transactionRequestMapper;
    }

    @Override
    public TransactionRequestDTO save(TransactionRequestDTO transactionRequestDTO) {
        log.debug("Request to save TransactionRequest : {}", transactionRequestDTO);
        TransactionRequest transactionRequest = transactionRequestMapper.toEntity(transactionRequestDTO);
        transactionRequest = transactionRequestRepository.save(transactionRequest);
        return transactionRequestMapper.toDto(transactionRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TransactionRequests");
        return transactionRequestRepository.findAll(pageable)
            .map(transactionRequestMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionRequestDTO> findOne(Long id) {
        log.debug("Request to get TransactionRequest : {}", id);
        return transactionRequestRepository.findById(id)
            .map(transactionRequestMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransactionRequest : {}", id);
        transactionRequestRepository.deleteById(id);
    }
}
