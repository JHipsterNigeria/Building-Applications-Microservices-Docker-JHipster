package com.jhipster.nigeria.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionRequestMapperTest {

    private TransactionRequestMapper transactionRequestMapper;

    @BeforeEach
    public void setUp() {
        transactionRequestMapper = new TransactionRequestMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(transactionRequestMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(transactionRequestMapper.fromId(null)).isNull();
    }
}
