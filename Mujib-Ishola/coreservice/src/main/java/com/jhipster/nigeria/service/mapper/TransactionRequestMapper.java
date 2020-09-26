package com.jhipster.nigeria.service.mapper;


import com.jhipster.nigeria.domain.*;
import com.jhipster.nigeria.service.dto.TransactionRequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TransactionRequest} and its DTO {@link TransactionRequestDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TransactionRequestMapper extends EntityMapper<TransactionRequestDTO, TransactionRequest> {


    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "removeAccount", ignore = true)
    TransactionRequest toEntity(TransactionRequestDTO transactionRequestDTO);

    default TransactionRequest fromId(Long id) {
        if (id == null) {
            return null;
        }
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setId(id);
        return transactionRequest;
    }
}
