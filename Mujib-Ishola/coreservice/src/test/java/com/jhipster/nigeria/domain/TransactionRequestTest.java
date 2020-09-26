package com.jhipster.nigeria.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jhipster.nigeria.web.rest.TestUtil;

public class TransactionRequestTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransactionRequest.class);
        TransactionRequest transactionRequest1 = new TransactionRequest();
        transactionRequest1.setId(1L);
        TransactionRequest transactionRequest2 = new TransactionRequest();
        transactionRequest2.setId(transactionRequest1.getId());
        assertThat(transactionRequest1).isEqualTo(transactionRequest2);
        transactionRequest2.setId(2L);
        assertThat(transactionRequest1).isNotEqualTo(transactionRequest2);
        transactionRequest1.setId(null);
        assertThat(transactionRequest1).isNotEqualTo(transactionRequest2);
    }
}
