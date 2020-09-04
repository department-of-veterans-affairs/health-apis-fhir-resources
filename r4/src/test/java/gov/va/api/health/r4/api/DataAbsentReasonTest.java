package gov.va.api.health.r4.api;

import static org.assertj.core.api.Assertions.assertThat;

import gov.va.api.health.r4.api.DataAbsentReason.Reason;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class DataAbsentReasonTest {
  @Test
  @SneakyThrows
  public void reasonText() {
    assertThat(DataAbsentReason.of(Reason.not_asked).extension().size()).isEqualTo(1);
    assertThat(DataAbsentReason.of(Reason.not_asked).extension().get(0).valueCode())
        .isEqualTo("not-asked");
    assertThat(DataAbsentReason.of(Reason.unsupported).extension().get(0).valueCode())
        .isEqualTo("unsupported");
  }
}
