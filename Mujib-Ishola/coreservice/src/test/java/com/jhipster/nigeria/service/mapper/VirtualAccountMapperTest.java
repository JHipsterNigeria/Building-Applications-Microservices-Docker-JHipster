package com.jhipster.nigeria.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VirtualAccountMapperTest {

    private VirtualAccountMapper virtualAccountMapper;

    @BeforeEach
    public void setUp() {
        virtualAccountMapper = new VirtualAccountMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(virtualAccountMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(virtualAccountMapper.fromId(null)).isNull();
    }
}
