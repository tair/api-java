/**
* Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade;

/**
 * An exception indicating that an API call that was supposed to retrieve at least one object found no objects
 * 
 * @author Robert J. Muller
 */
public class NoObjectFoundException extends ApiException {
  /** serial version UID */
  private static final long serialVersionUID = 1L;

  /** the default message if none is supplied */
  private static final String DEFAULT_MESSAGE = "Phoenix API Exception";

  /**
   * Create a NoObjectFoundException object with a default message.
   */
  public NoObjectFoundException() {
    super(DEFAULT_MESSAGE);
  }

  /**
   * Create a NoObjectFoundException object with an explicit message.
   * @param message the message
   */
  public NoObjectFoundException(String message) {
    super(message);
  }

  /**
   * Create a NoObjectFoundException object with a causing exception.
   * @param cause the causing exception
   */
  public NoObjectFoundException(Throwable cause) {
    super(DEFAULT_MESSAGE, cause);
  }

  /**
   * Create a NoObjectFoundException object with an explicit message and causing exception.
   * @param message the message
   * @param cause the causing exception
   */
  public NoObjectFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Create a NoObjectFoundException object with the full array of exception arguments.
   * @param message the message
   * @param cause the causing exception
   * @param enableSuppression flag to enable suppression
   * @param writableStackTrace flag to enable a stack trace
   */
  public NoObjectFoundException(String message,
                                Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
