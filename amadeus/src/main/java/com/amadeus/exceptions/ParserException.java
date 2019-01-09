package com.amadeus.exceptions;

import com.amadeus.Response;

/**
 * This error occurs when the response type was JSON but could not be parsed.
 */
public class ParserException extends ResponseException {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @hides as only used internally
   */
  public ParserException(Response response) {
    super(response);
  }
}
