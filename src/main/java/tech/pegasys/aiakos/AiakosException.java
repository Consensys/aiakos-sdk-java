package tech.pegasys.aiakos;

public class AiakosException extends Exception {
  public AiakosException(String message) {
    super(message);
  }

  public AiakosException(String message, Throwable cause) {
    super(message, cause);
  }
}
