package tech.pegasys.aiakos.domain;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.codec.binary.Hex;

import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

@Builder
public class Release {
  @Getter private String version;
  @Getter private byte[] sha256Hash;
  private Path releaseDistributionPath;
  private final AtomicBoolean integrityHashComputed = new AtomicBoolean(false);

  public Release computeIntegrityHash() {
    if (integrityHashComputed.compareAndSet(false, true)) {
      // TODO implement actual integrity hash
      final byte[] releaseHash = new byte[32];
      ThreadLocalRandom.current().nextBytes(releaseHash);
      this.sha256Hash = releaseHash;
    }
    return this;
  }

  public String sha256HashString() {
    return Hex.encodeHexString(sha256Hash);
  }
}
