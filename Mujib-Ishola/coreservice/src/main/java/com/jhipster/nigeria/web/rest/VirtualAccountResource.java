package com.jhipster.nigeria.web.rest;

import com.jhipster.nigeria.service.VirtualAccountService;
import com.jhipster.nigeria.web.rest.errors.BadRequestAlertException;
import com.jhipster.nigeria.service.dto.VirtualAccountDTO;
import com.jhipster.nigeria.service.dto.VirtualAccountCriteria;
import com.jhipster.nigeria.service.VirtualAccountQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.jhipster.nigeria.domain.VirtualAccount}.
 */
@RestController
@RequestMapping("/api")
public class VirtualAccountResource {

    private final Logger log = LoggerFactory.getLogger(VirtualAccountResource.class);

    private static final String ENTITY_NAME = "coreserviceVirtualAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VirtualAccountService virtualAccountService;

    private final VirtualAccountQueryService virtualAccountQueryService;

    public VirtualAccountResource(VirtualAccountService virtualAccountService, VirtualAccountQueryService virtualAccountQueryService) {
        this.virtualAccountService = virtualAccountService;
        this.virtualAccountQueryService = virtualAccountQueryService;
    }

    /**
     * {@code POST  /virtual-accounts} : Create a new virtualAccount.
     *
     * @param virtualAccountDTO the virtualAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new virtualAccountDTO, or with status {@code 400 (Bad Request)} if the virtualAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/virtual-accounts")
    public ResponseEntity<VirtualAccountDTO> createVirtualAccount(@Valid @RequestBody VirtualAccountDTO virtualAccountDTO) throws URISyntaxException {
        log.debug("REST request to save VirtualAccount : {}", virtualAccountDTO);
        if (virtualAccountDTO.getId() != null) {
            throw new BadRequestAlertException("A new virtualAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VirtualAccountDTO result = virtualAccountService.save(virtualAccountDTO);
        return ResponseEntity.created(new URI("/api/virtual-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /virtual-accounts} : Updates an existing virtualAccount.
     *
     * @param virtualAccountDTO the virtualAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated virtualAccountDTO,
     * or with status {@code 400 (Bad Request)} if the virtualAccountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the virtualAccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/virtual-accounts")
    public ResponseEntity<VirtualAccountDTO> updateVirtualAccount(@Valid @RequestBody VirtualAccountDTO virtualAccountDTO) throws URISyntaxException {
        log.debug("REST request to update VirtualAccount : {}", virtualAccountDTO);
        if (virtualAccountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VirtualAccountDTO result = virtualAccountService.save(virtualAccountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, virtualAccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /virtual-accounts} : get all the virtualAccounts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of virtualAccounts in body.
     */
    @GetMapping("/virtual-accounts")
    public ResponseEntity<List<VirtualAccountDTO>> getAllVirtualAccounts(VirtualAccountCriteria criteria, Pageable pageable) {
        log.debug("REST request to get VirtualAccounts by criteria: {}", criteria);
        Page<VirtualAccountDTO> page = virtualAccountQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /virtual-accounts/count} : count all the virtualAccounts.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/virtual-accounts/count")
    public ResponseEntity<Long> countVirtualAccounts(VirtualAccountCriteria criteria) {
        log.debug("REST request to count VirtualAccounts by criteria: {}", criteria);
        return ResponseEntity.ok().body(virtualAccountQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /virtual-accounts/:id} : get the "id" virtualAccount.
     *
     * @param id the id of the virtualAccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the virtualAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/virtual-accounts/{id}")
    public ResponseEntity<VirtualAccountDTO> getVirtualAccount(@PathVariable Long id) {
        log.debug("REST request to get VirtualAccount : {}", id);
        Optional<VirtualAccountDTO> virtualAccountDTO = virtualAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(virtualAccountDTO);
    }

    /**
     * {@code DELETE  /virtual-accounts/:id} : delete the "id" virtualAccount.
     *
     * @param id the id of the virtualAccountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/virtual-accounts/{id}")
    public ResponseEntity<Void> deleteVirtualAccount(@PathVariable Long id) {
        log.debug("REST request to delete VirtualAccount : {}", id);
        virtualAccountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
