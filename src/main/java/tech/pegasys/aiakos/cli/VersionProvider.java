package tech.pegasys.aiakos.cli;

import picocli.CommandLine;

import static java.lang.String.format;
import static tech.pegasys.aiakos.util.PlatformDetector.getOS;
import static tech.pegasys.aiakos.util.PlatformDetector.getVM;

public class VersionProvider implements CommandLine.IVersionProvider {
  @Override
  public String[] getVersion() {
    return new String[] {
      format(
          "aiakos/v%s/%s/%s",
          this.getClass().getPackage().getImplementationVersion(), getOS(), getVM())
    };
  }
}
