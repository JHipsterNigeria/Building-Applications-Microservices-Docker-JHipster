package com.jhipster.nigeria.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jhipster.nigeria.web.rest.TestUtil;

public class TransactionRequestDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransactionRequestDTO.class);
        TransactionRequestDTO transactionRequestDTO1 = new TransactionRequestDTO();
        transactionRequestDTO1.setId(1L);
        TransactionRequestDTO transactionRequestDTO2 = new TransactionRequestDTO();
        assertThat(transactionRequestDTO1).isNotEqualTo(transactionRequestDTO2);
        transactionRequestDTO2.setId(transactionRequestDTO1.getId());
        assertThat(transactionRequestDTO1).isEqualTo(transactionRequestDTO2);
        transactionRequestDTO2.setId(2L);
        assertThat(transactionRequestDTO1).isNotEqualTo(transactionRequestDTO2);
        transactionRequestDTO1.setId(null);
        assertThat(transactionRequestDTO1).isNotEqualTo(transactionRequestDTO2);
    }
}
