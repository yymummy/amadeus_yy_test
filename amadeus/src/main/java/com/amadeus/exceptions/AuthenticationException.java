package com.amadeus.exceptions;

import com.amadeus.Response;

/**
 * This error occurs when the client did not provide the right credentials.
 */
public class AuthenticationException extends ResponseException {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @hides as only used internally
   */
  public AuthenticationException(Response response) {
    super(response);
  }
}
