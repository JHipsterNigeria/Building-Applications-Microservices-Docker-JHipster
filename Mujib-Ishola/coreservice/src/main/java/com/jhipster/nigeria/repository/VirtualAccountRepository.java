package com.jhipster.nigeria.repository;

import com.jhipster.nigeria.domain.VirtualAccount;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VirtualAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VirtualAccountRepository extends JpaRepository<VirtualAccount, Long>, JpaSpecificationExecutor<VirtualAccount> {
}
