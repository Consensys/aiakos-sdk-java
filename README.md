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