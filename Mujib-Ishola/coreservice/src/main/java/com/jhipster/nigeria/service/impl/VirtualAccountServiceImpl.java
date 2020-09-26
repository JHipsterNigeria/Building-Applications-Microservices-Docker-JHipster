package com.jhipster.nigeria.service.impl;

import com.jhipster.nigeria.service.VirtualAccountService;
import com.jhipster.nigeria.domain.VirtualAccount;
import com.jhipster.nigeria.repository.VirtualAccountRepository;
import com.jhipster.nigeria.service.dto.VirtualAccountDTO;
import com.jhipster.nigeria.service.mapper.VirtualAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link VirtualAccount}.
 */
@Service
@Transactional
public class VirtualAccountServiceImpl implements VirtualAccountService {

    private final Logger log = LoggerFactory.getLogger(VirtualAccountServiceImpl.class);

    private final VirtualAccountRepository virtualAccountRepository;

    private final VirtualAccountMapper virtualAccountMapper;

    public VirtualAccountServiceImpl(VirtualAccountRepository virtualAccountRepository, VirtualAccountMapper virtualAccountMapper) {
        this.virtualAccountRepository = virtualAccountRepository;
        this.virtualAccountMapper = virtualAccountMapper;
    }

    @Override
    public VirtualAccountDTO save(VirtualAccountDTO virtualAccountDTO) {
        log.debug("Request to save VirtualAccount : {}", virtualAccountDTO);
        VirtualAccount virtualAccount = virtualAccountMapper.toEntity(virtualAccountDTO);
        virtualAccount = virtualAccountRepository.save(virtualAccount);
        return virtualAccountMapper.toDto(virtualAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VirtualAccountDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VirtualAccounts");
        return virtualAccountRepository.findAll(pageable)
            .map(virtualAccountMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<VirtualAccountDTO> findOne(Long id) {
        log.debug("Request to get VirtualAccount : {}", id);
        return virtualAccountRepository.findById(id)
            .map(virtualAccountMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete VirtualAccount : {}", id);
        virtualAccountRepository.deleteById(id);
    }
}
