package tech.pegasys.aiakos.roles;

import lombok.Getter;
import tech.pegasys.aiakos.AiakosException;
import tech.pegasys.aiakos.config.AiakosConfiguration;
import tech.pegasys.aiakos.contract.Aiakos;

import java.math.BigInteger;

public class AiakosOwner extends AiakosUser {
  @Getter private String deployedContractAddress;

  private AiakosOwner(final Aiakos contract, final AiakosConfiguration configuration) {
    super(contract, configuration);
  }

  public static AiakosOwner of(
      final AiakosConfiguration configuration, final int requiredNumberOfMaintainers)
      throws Exception {
    final Aiakos contract =
        Aiakos.deploy(
                configuration.getWeb3j(),
                configuration.getCredentials(),
                configuration.getContractGasProvider(),
                new BigInteger(String.valueOf(requiredNumberOfMaintainers), 10))
            .send();
    final AiakosOwner owner = new AiakosOwner(contract, configuration);
    owner.deployedContractAddress = contract.getContractAddress();
    return owner;
  }

  public void addMaintainer(final String maintainerAddress) throws AiakosException {
    try {
      getContract().addMaintainer(maintainerAddress, getConfiguration().getWeiAmount()).send();
    } catch (Exception e) {
      throw new AiakosException("addMaintainer error.", e);
    }
  }
}
