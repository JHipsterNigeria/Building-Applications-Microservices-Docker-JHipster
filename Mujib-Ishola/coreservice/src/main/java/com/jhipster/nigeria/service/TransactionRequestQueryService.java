package com.jhipster.nigeria.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.jhipster.nigeria.domain.TransactionRequest;
import com.jhipster.nigeria.domain.*; // for static metamodels
import com.jhipster.nigeria.repository.TransactionRequestRepository;
import com.jhipster.nigeria.service.dto.TransactionRequestCriteria;
import com.jhipster.nigeria.service.dto.TransactionRequestDTO;
import com.jhipster.nigeria.service.mapper.TransactionRequestMapper;

/**
 * Service for executing complex queries for {@link TransactionRequest} entities in the database.
 * The main input is a {@link TransactionRequestCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TransactionRequestDTO} or a {@link Page} of {@link TransactionRequestDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TransactionRequestQueryService extends QueryService<TransactionRequest> {

    private final Logger log = LoggerFactory.getLogger(TransactionRequestQueryService.class);

    private final TransactionRequestRepository transactionRequestRepository;

    private final TransactionRequestMapper transactionRequestMapper;

    public TransactionRequestQueryService(TransactionRequestRepository transactionRequestRepository, TransactionRequestMapper transactionRequestMapper) {
        this.transactionRequestRepository = transactionRequestRepository;
        this.transactionRequestMapper = transactionRequestMapper;
    }

    /**
     * Return a {@link List} of {@link TransactionRequestDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TransactionRequestDTO> findByCriteria(TransactionRequestCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TransactionRequest> specification = createSpecification(criteria);
        return transactionRequestMapper.toDto(transactionRequestRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TransactionRequestDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TransactionRequestDTO> findByCriteria(TransactionRequestCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TransactionRequest> specification = createSpecification(criteria);
        return transactionRequestRepository.findAll(specification, page)
            .map(transactionRequestMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TransactionRequestCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TransactionRequest> specification = createSpecification(criteria);
        return transactionRequestRepository.count(specification);
    }

    /**
     * Function to convert {@link TransactionRequestCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TransactionRequest> createSpecification(TransactionRequestCriteria criteria) {
        Specification<TransactionRequest> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TransactionRequest_.id));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), TransactionRequest_.amount));
            }
            if (criteria.getChannel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChannel(), TransactionRequest_.channel));
            }
            if (criteria.getCurrency() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCurrency(), TransactionRequest_.currency));
            }
            if (criteria.getSourceAccount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSourceAccount(), TransactionRequest_.sourceAccount));
            }
            if (criteria.getSourceAccountBankCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSourceAccountBankCode(), TransactionRequest_.sourceAccountBankCode));
            }
            if (criteria.getSourceAccountName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSourceAccountName(), TransactionRequest_.sourceAccountName));
            }
            if (criteria.getSourceNarration() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSourceNarration(), TransactionRequest_.sourceNarration));
            }
            if (criteria.getDestinationAccount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDestinationAccount(), TransactionRequest_.destinationAccount));
            }
            if (criteria.getDestinationAccountBankCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDestinationAccountBankCode(), TransactionRequest_.destinationAccountBankCode));
            }
            if (criteria.getDestinationAccountName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDestinationAccountName(), TransactionRequest_.destinationAccountName));
            }
            if (criteria.getDestinationNarration() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDestinationNarration(), TransactionRequest_.destinationNarration));
            }
            if (criteria.getStatusWebHook() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatusWebHook(), TransactionRequest_.statusWebHook));
            }
            if (criteria.getTransactionRef() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTransactionRef(), TransactionRequest_.transactionRef));
            }
            if (criteria.getResponseCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResponseCode(), TransactionRequest_.responseCode));
            }
            if (criteria.getResponseMessage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResponseMessage(), TransactionRequest_.responseMessage));
            }
            if (criteria.getTransactionType() != null) {
                specification = specification.and(buildSpecification(criteria.getTransactionType(), TransactionRequest_.transactionType));
            }
            if (criteria.getSchemeOwnerId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSchemeOwnerId(), TransactionRequest_.schemeOwnerId));
            }
            if (criteria.getAccountId() != null) {
                specification = specification.and(buildSpecification(criteria.getAccountId(),
                    root -> root.join(TransactionRequest_.accounts, JoinType.LEFT).get(VirtualAccount_.id)));
            }
        }
        return specification;
    }
}
