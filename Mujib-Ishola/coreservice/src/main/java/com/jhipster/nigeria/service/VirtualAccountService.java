package com.jhipster.nigeria.service;

import com.jhipster.nigeria.service.dto.VirtualAccountDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.jhipster.nigeria.domain.VirtualAccount}.
 */
public interface VirtualAccountService {

    /**
     * Save a virtualAccount.
     *
     * @param virtualAccountDTO the entity to save.
     * @return the persisted entity.
     */
    VirtualAccountDTO save(VirtualAccountDTO virtualAccountDTO);

    /**
     * Get all the virtualAccounts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VirtualAccountDTO> findAll(Pageable pageable);


    /**
     * Get the "id" virtualAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VirtualAccountDTO> findOne(Long id);

    /**
     * Delete the "id" virtualAccount.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
