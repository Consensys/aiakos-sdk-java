package tech.pegasys.aiakos.roles;

import lombok.Getter;
import org.apache.commons.codec.binary.Hex;
import org.web3j.tuples.generated.Tuple4;
import tech.pegasys.aiakos.AiakosException;
import tech.pegasys.aiakos.config.AiakosConfiguration;
import tech.pegasys.aiakos.contract.Aiakos;
import tech.pegasys.aiakos.domain.Release;
import tech.pegasys.aiakos.domain.ReleaseInfo;

public class AiakosUser {
  @Getter private final Aiakos contract;
  @Getter private final AiakosConfiguration configuration;

  protected AiakosUser(final AiakosConfiguration configuration) {
    this(
        Aiakos.load(
            configuration.getAiakosContractAddress(),
            configuration.getWeb3j(),
            configuration.getCredentials(),
            configuration.getContractGasProvider()),
        configuration);
  }

  protected AiakosUser(final Aiakos contract, final AiakosConfiguration configuration) {
    this.contract = contract;
    this.configuration = configuration;
  }

  public ReleaseInfo getReleaseInfo(final Release release) throws AiakosException {
    try {
      Tuple4<String, byte[], Boolean, Boolean> tuple =
          contract.getReleaseInfo(release.getVersion()).send();
      return ReleaseInfo.builder()
          .version(tuple.getValue1())
          .sha256Hash(Hex.encodeHexString(tuple.getValue2()))
          .initialized(tuple.getValue3())
          .approved(tuple.getValue4())
          .build();
    } catch (Exception e) {
      throw new AiakosException("getReleaseInfo error.", e);
    }
  }

  public boolean amIMaintainer() throws AiakosException {
    try {
      return contract.amIMaintainer().send();
    } catch (Exception e) {
      throw new AiakosException("amIMaintainer error.", e);
    }
  }
}
