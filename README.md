# aiakos-sdk-java

## Requirements

Put ABI and bytecode in aiakos.abi and aiakos.bin.

Run ganache with fixed mnemonic.

```bash
ganache-cli --host "0.0.0.0" --accounts 4 --deterministic -v --mnemonic "unfold bachelor enact fiber later donate uncover once torch figure engine keep"
```

Available Accounts
==================
(0) 0xfd12370ac2210964b92695f08ad7a84516c77eeb (~100 ETH)
(1) 0xa00b1a651e6123d55a59d043c63f720414fd1d08 (~100 ETH)
(2) 0xf88858b8246b4c41223354db2caca84109824f86 (~100 ETH)
(3) 0x6aac4573eab4adde553ea004b36e0a7594323fef (~100 ETH)

Private Keys
==================
(0) 0x5f32b2d51813403414c89b0f582d5e1eab560f67803201c993c16af1e462eaae
(1) 0xefa263a31bd0c9420caafe4be3feb9e0c663584e6edb02bf0f723e7830a77a6f
(2) 0x927a407efb0aa2c48dc9f152f64db22b2e7fe50235471d0745f34850ab8059ff
(3) 0xc460413e54c701314acc1acb6d98f206611c93d34c071a56ff86d50067f77586

## Generate smart contract wrapper
```bash
web3j solidity generate -a ./src/main/resources/solidity/aiakos/aiakos.abi \
 -b ./src/main/resources/solidity/aiakos/aiakos.bin \
 -o ./src/main/java \
 -p  tech.pegasys.aiakos.contract
```