package tech.pegasys.aiakos.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParentCommand;
import tech.pegasys.aiakos.AiakosException;
import tech.pegasys.aiakos.roles.AiakosMaintainer;

@Command(name = "release", description = "Get release info.")
public class ReleaseCmd implements Runnable {
  private static final Logger log = LoggerFactory.getLogger(ReleaseCmd.class);

  @ParentCommand private AiakosCommand aiakosCommand;

  @Option(names = "--release-version", arity = "1")
  private String releaseVersion;

  @Option(names = "--release-path", arity = "1")
  private String releaseDistributionPath;

  @Override
  public void run() {
    try {
      log.info("Running release command.");
      final AiakosMaintainer maintainer = aiakosCommand.maintainerFromCli();
      log.info("Am i maintainer: {}", maintainer.amIMaintainer());
    } catch (AiakosException e) {
      log.error("Cannot run release command.", e);
    }
  }
}
