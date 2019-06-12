# aiakos-sdk-java

## Requirements

Put ABI and bytecode in aiakos.abi and aiakos.bin.

Run ganache with fixed mnemonic.

```bash
ganache-cli --host "0.0.0.0" --accounts 4 --deterministic -v --mnemonic "unfold bachelor enact fiber later donate uncover once torch figure engine keep"
```

Available Accounts
==================
0xfd12370ac2210964b92695f08ad7a84516c77eeb
0xa00b1a651e6123d55a59d043c63f720414fd1d08
0xf88858b8246b4c41223354db2caca84109824f86
0x6aac4573eab4adde553ea004b36e0a7594323fef


## Generate smart contract wrapper
```bash
web3j solidity generate -a ./src/main/resources/solidity/aiakos/aiakos.abi \
 -b ./src/main/resources/solidity/aiakos/aiakos.bin \
 -o ./src/main/java \
 -p  tech.pegasys.aiakos.contract
```

## Sample

```java
  public static void main(String[] args) throws Exception {
    final int requiredNumberOfMaintainers = 2;
    // Declare the owner of the Aiakos contract.
    final AiakosOwner owner =
        AiakosOwner.of(
            AiakosConfiguration.builder()
                .accountAddress("0xfd12370ac2210964b92695f08ad7a84516c77eeb")
                .privateKey("0x5f32b2d51813403414c89b0f582d5e1eab560f67803201c993c16af1e462eaae")
                .build(),
            requiredNumberOfMaintainers);
    final String pantheonVersion = "1.0.0";
    final Path pantheonReleasePath = Paths.get("/opt/bin/pantheon/pantheon-1.0.0.tar.gz");
    // Build release, compute integrity hash.
    final Release pantheonRelease =
        Release.builder()
            .version(pantheonVersion)
            .releaseDistributionPath(pantheonReleasePath)
            .build();
    // Declare maintainers users.
    final AiakosMaintainer maintainer1 =
        AiakosMaintainer.of(
            AiakosConfiguration.builder()
                .accountAddress("0xa00b1a651e6123d55a59d043c63f720414fd1d08")
                .privateKey("0xefa263a31bd0c9420caafe4be3feb9e0c663584e6edb02bf0f723e7830a77a6f")
                .aiakosContractAddress(owner.getDeployedContractAddress())
                .build());
    final AiakosMaintainer maintainer2 =
        AiakosMaintainer.of(
            AiakosConfiguration.builder()
                .accountAddress("0xf88858b8246b4c41223354db2caca84109824f86")
                .privateKey("0x927a407efb0aa2c48dc9f152f64db22b2e7fe50235471d0745f34850ab8059ff")
                .aiakosContractAddress(owner.getDeployedContractAddress())
                .build());

    // Owner: add maintainers.
    owner.addMaintainer(maintainer1.getConfiguration().getAccountAddress());
    owner.addMaintainer(maintainer2.getConfiguration().getAccountAddress());
    // Maintainers: grant approvals.
    maintainer1.deployRelease(pantheonRelease);
    // Get release info.
    final ReleaseInfo releaseInfoAfterFirstApproval = owner.getReleaseInfo(pantheonRelease);
    log.info("Release info: {}", releaseInfoAfterFirstApproval);
    maintainer2.deployRelease(pantheonRelease);
    // Get release info.
    final ReleaseInfo releaseInfoAfterSecondApproval = owner.getReleaseInfo(pantheonRelease);
    log.info("Release info: {}", releaseInfoAfterSecondApproval);
  }
```