package tech.pegasys.aiakos.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParentCommand;
import tech.pegasys.aiakos.AiakosException;
import tech.pegasys.aiakos.domain.Release;
import tech.pegasys.aiakos.domain.ReleaseInfo;
import tech.pegasys.aiakos.roles.AiakosMaintainer;

@Command(name = "release-info", description = "Grants approval for a release.")
public class ReleaseInfoCmd implements Runnable {
  private static final Logger log = LoggerFactory.getLogger(ReleaseInfoCmd.class);

  @ParentCommand private AiakosCommand aiakosCommand;

  @Option(names = "--release-version", arity = "1")
  private String releaseVersion;

  @Override
  public void run() {
    try {
      log.info("Running release info command.");
      final AiakosMaintainer maintainer = aiakosCommand.maintainerFromCli();
      final ReleaseInfo releaseInfo =
          maintainer.getReleaseInfo(Release.builder().version(releaseVersion).build());
      System.out.println(releaseInfo.json());
    } catch (AiakosException e) {
      log.error("Cannot run release command.", e);
    }
  }
}
