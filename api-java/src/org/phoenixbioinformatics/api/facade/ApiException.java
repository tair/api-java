/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade;

/**
 * A generic checked exception for the Phoenix API; base class for concrete
 * checked exceptions
 * 
 * @author Robert J. Muller
 */
public class ApiException extends Exception {
  /** serial version UID */
  private static final long serialVersionUID = 1L;

  /** the default message if none is supplied */
  private static final String DEFAULT_MESSAGE = "Phoenix API Exception";

  /**
   * Create an ApiException object with a default message.
   */
  public ApiException() {
    super(DEFAULT_MESSAGE);
  }

  /**
   * Create an ApiException object with an explicit message.
   * 
   * @param message the message
   */
  public ApiException(String message) {
    super(message);
  }

  /**
   * Create an ApiException object with a default message and a causing
   * exception.
   * 
   * @param cause the causing exception
   */
  public ApiException(Throwable cause) {
    super(DEFAULT_MESSAGE, cause);
  }

  /**
   * Create an ApiException object with an explicit message and causing
   * exception.
   * 
   * @param message the message
   * @param cause the causing exception
   */
  public ApiException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Create an ApiException object with the full range of attributes.
   * 
   * @param message the message
   * @param cause the causing exception
   * @param enableSuppression flag to enable suppression
   * @param writableStackTrace flag to enable a stack trace
   */
  public ApiException(String message,
                      Throwable cause,
                      boolean enableSuppression,
                      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    // TODO Auto-generated constructor stub
  }

}
