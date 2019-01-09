package com.amadeus.exceptions;

import com.amadeus.Response;

/**
 * This error occurs when the path could not be found.
 */
public class NotFoundException extends ResponseException {
  private static final long serialVersionUID = 1L;

  public NotFoundException(Response response) {
    super(response);
  }
}
