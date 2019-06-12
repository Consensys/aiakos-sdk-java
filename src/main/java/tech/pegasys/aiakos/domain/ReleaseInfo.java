package tech.pegasys.aiakos.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReleaseInfo {
  private String version;
  private String sha256Hash;
  private boolean initialized;
  private boolean approved;
}
