package com.jhipster.nigeria.service.mapper;


import com.jhipster.nigeria.domain.*;
import com.jhipster.nigeria.service.dto.VirtualAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link VirtualAccount} and its DTO {@link VirtualAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProfileMapper.class, TransactionRequestMapper.class})
public interface VirtualAccountMapper extends EntityMapper<VirtualAccountDTO, VirtualAccount> {

    @Mapping(source = "accountHolder.id", target = "accountHolderId")
    @Mapping(source = "accountHolder.email", target = "accountHolderEmail")
    @Mapping(source = "transactionRequest.id", target = "transactionRequestId")
    VirtualAccountDTO toDto(VirtualAccount virtualAccount);

    @Mapping(source = "accountHolderId", target = "accountHolder")
    @Mapping(source = "transactionRequestId", target = "transactionRequest")
    VirtualAccount toEntity(VirtualAccountDTO virtualAccountDTO);

    default VirtualAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        VirtualAccount virtualAccount = new VirtualAccount();
        virtualAccount.setId(id);
        return virtualAccount;
    }
}
