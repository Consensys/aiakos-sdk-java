package tech.pegasys.aiakos.roles;

import tech.pegasys.aiakos.AiakosException;
import tech.pegasys.aiakos.domain.Release;
import tech.pegasys.aiakos.config.AiakosConfiguration;

public class AiakosMaintainer extends AiakosUser {

  private AiakosMaintainer(final AiakosConfiguration configuration) {
    super(configuration);
  }

  public static AiakosMaintainer of(final AiakosConfiguration configuration) {
    return new AiakosMaintainer(configuration);
  }

  public void deployRelease(final Release release) throws AiakosException {
    release.computeIntegrityHash();
    try {
      getContract()
          .deployRelease(
              release.getVersion(), release.getSha256Hash(), getConfiguration().getWeiAmount())
          .send();
    } catch (Exception e) {
      throw new AiakosException("deployRelease error.", e);
    }
  }
}
