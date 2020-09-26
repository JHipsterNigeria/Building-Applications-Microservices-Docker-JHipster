package com.jhipster.nigeria.extended.service;

import com.jhipster.nigeria.domain.enumeration.AccountStatus;
import com.jhipster.nigeria.extended.dto.AccountDto;
import com.jhipster.nigeria.service.VirtualAccountService;
import com.jhipster.nigeria.service.dto.ProfileDTO;
import com.jhipster.nigeria.service.dto.VirtualAccountDTO;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountService {

    @Value("${default.account.minbalance:0}")
    private BigDecimal minimumBalance;

    private VirtualAccountService virtualAccountService;

    public AccountService(VirtualAccountService virtualAccountService) {
        this.virtualAccountService=virtualAccountService;
    }


    public VirtualAccountDTO createAccount(ProfileDTO profile) {
        VirtualAccountDTO virtualAccountDTO = new VirtualAccountDTO();
        virtualAccountDTO.setCustomerId(profile.getPhone());
      //  virtualAccountDTO.setAccountType(profile.getProfileType());
        virtualAccountDTO.setAvailableBalance(BigDecimal.ZERO);
        virtualAccountDTO.setMinimumBalance(minimumBalance);
        virtualAccountDTO.setAccountNumber(profile.getPhone());
        virtualAccountDTO.setStatus(AccountStatus.NEW);
        virtualAccountDTO.setStatus(AccountStatus.INACTIVE);
        virtualAccountDTO.setAccountHolderId(profile.getId());
        return virtualAccountService.save(virtualAccountDTO);
    }

    public VirtualAccountDTO findAccount(String phoneNumber) {
        return new VirtualAccountDTO();
    }


}
