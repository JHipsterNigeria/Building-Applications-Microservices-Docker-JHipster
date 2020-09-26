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

import com.jhipster.nigeria.domain.VirtualAccount;
import com.jhipster.nigeria.domain.*; // for static metamodels
import com.jhipster.nigeria.repository.VirtualAccountRepository;
import com.jhipster.nigeria.service.dto.VirtualAccountCriteria;
import com.jhipster.nigeria.service.dto.VirtualAccountDTO;
import com.jhipster.nigeria.service.mapper.VirtualAccountMapper;

/**
 * Service for executing complex queries for {@link VirtualAccount} entities in the database.
 * The main input is a {@link VirtualAccountCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VirtualAccountDTO} or a {@link Page} of {@link VirtualAccountDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VirtualAccountQueryService extends QueryService<VirtualAccount> {

    private final Logger log = LoggerFactory.getLogger(VirtualAccountQueryService.class);

    private final VirtualAccountRepository virtualAccountRepository;

    private final VirtualAccountMapper virtualAccountMapper;

    public VirtualAccountQueryService(VirtualAccountRepository virtualAccountRepository, VirtualAccountMapper virtualAccountMapper) {
        this.virtualAccountRepository = virtualAccountRepository;
        this.virtualAccountMapper = virtualAccountMapper;
    }

    /**
     * Return a {@link List} of {@link VirtualAccountDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VirtualAccountDTO> findByCriteria(VirtualAccountCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<VirtualAccount> specification = createSpecification(criteria);
        return virtualAccountMapper.toDto(virtualAccountRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VirtualAccountDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VirtualAccountDTO> findByCriteria(VirtualAccountCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<VirtualAccount> specification = createSpecification(criteria);
        return virtualAccountRepository.findAll(specification, page)
            .map(virtualAccountMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VirtualAccountCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<VirtualAccount> specification = createSpecification(criteria);
        return virtualAccountRepository.count(specification);
    }

    /**
     * Function to convert {@link VirtualAccountCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<VirtualAccount> createSpecification(VirtualAccountCriteria criteria) {
        Specification<VirtualAccount> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), VirtualAccount_.id));
            }
            if (criteria.getCustomerId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCustomerId(), VirtualAccount_.customerId));
            }
            if (criteria.getExtCustomerId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExtCustomerId(), VirtualAccount_.extCustomerId));
            }
            if (criteria.getCurrency() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCurrency(), VirtualAccount_.currency));
            }
            if (criteria.getAccountNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountNumber(), VirtualAccount_.accountNumber));
            }
            if (criteria.getAvailableBalance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAvailableBalance(), VirtualAccount_.availableBalance));
            }
            if (criteria.getHoldBalance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHoldBalance(), VirtualAccount_.holdBalance));
            }
            if (criteria.getMinimumBalance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMinimumBalance(), VirtualAccount_.minimumBalance));
            }
            if (criteria.getAccountType() != null) {
                specification = specification.and(buildSpecification(criteria.getAccountType(), VirtualAccount_.accountType));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), VirtualAccount_.status));
            }
            if (criteria.getAccountHolderId() != null) {
                specification = specification.and(buildSpecification(criteria.getAccountHolderId(),
                    root -> root.join(VirtualAccount_.accountHolder, JoinType.LEFT).get(Profile_.id)));
            }
            if (criteria.getTransactionRequestId() != null) {
                specification = specification.and(buildSpecification(criteria.getTransactionRequestId(),
                    root -> root.join(VirtualAccount_.transactionRequest, JoinType.LEFT).get(TransactionRequest_.id)));
            }
        }
        return specification;
    }
}
