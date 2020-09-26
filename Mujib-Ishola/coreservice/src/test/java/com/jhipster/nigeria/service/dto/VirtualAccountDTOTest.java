package com.jhipster.nigeria.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.jhipster.nigeria.web.rest.TestUtil;

public class VirtualAccountDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VirtualAccountDTO.class);
        VirtualAccountDTO virtualAccountDTO1 = new VirtualAccountDTO();
        virtualAccountDTO1.setId(1L);
        VirtualAccountDTO virtualAccountDTO2 = new VirtualAccountDTO();
        assertThat(virtualAccountDTO1).isNotEqualTo(virtualAccountDTO2);
        virtualAccountDTO2.setId(virtualAccountDTO1.getId());
        assertThat(virtualAccountDTO1).isEqualTo(virtualAccountDTO2);
        virtualAccountDTO2.setId(2L);
        assertThat(virtualAccountDTO1).isNotEqualTo(virtualAccountDTO2);
        virtualAccountDTO1.setId(null);
        assertThat(virtualAccountDTO1).isNotEqualTo(virtualAccountDTO2);
    }
}
