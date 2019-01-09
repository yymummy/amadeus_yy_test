package com.amadeus.exceptions;

import com.amadeus.Response;

/**
 * This error occurs when there is an error on the server.
 */
public class ServerException extends ResponseException {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @hides as only used internally
   */
  public ServerException(Response response) {
    super(response);
  }
}
