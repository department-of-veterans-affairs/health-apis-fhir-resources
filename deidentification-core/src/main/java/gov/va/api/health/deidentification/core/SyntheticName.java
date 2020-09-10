package gov.va.api.health.deidentification.core;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SyntheticName {
  String first;
  String last;
}
