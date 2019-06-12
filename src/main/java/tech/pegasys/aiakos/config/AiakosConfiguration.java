package tech.pegasys.aiakos.config;

import lombok.Builder;
import lombok.Getter;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.web3j.utils.Convert.Unit.ETHER;
import static org.web3j.utils.Convert.toWei;

@Builder
public class AiakosConfiguration {
  @Getter @Builder.Default private String rpcUrl = "http://localhost:8545/";
  @Getter private String accountAddress;
  @Getter private String privateKey;
  @Getter private String aiakosContractAddress;
  @Getter private Web3j web3j;
  @Getter private Credentials credentials;
  @Getter private final ContractGasProvider contractGasProvider = new DefaultGasProvider();
  @Getter private final BigInteger weiAmount = toWei(BigDecimal.valueOf(0.1), ETHER).toBigInteger();

  public static AiakosConfigurationBuilder builder() {
    return new AiakosConfigurationCustomBuilder();
  }

  private static class AiakosConfigurationCustomBuilder extends AiakosConfigurationBuilder {
    @Override
    public AiakosConfiguration build() {
      AiakosConfiguration configuration = super.build();
      this.configureWeb3j(configuration).configureCredentials(configuration);
      return configuration;
    }

    private AiakosConfigurationCustomBuilder configureWeb3j(
        final AiakosConfiguration configuration) {
      if (configuration.web3j == null) {
        checkNotNull(configuration.rpcUrl);
        configuration.web3j = Web3j.build(new HttpService(configuration.rpcUrl));
      }
      return this;
    }

    private AiakosConfigurationCustomBuilder configureCredentials(
        final AiakosConfiguration configuration) {
      if (configuration.credentials == null) {
        checkNotNull(configuration.privateKey);
        configuration.credentials = Credentials.create(configuration.privateKey);
      }
      return this;
    }
  }
}
