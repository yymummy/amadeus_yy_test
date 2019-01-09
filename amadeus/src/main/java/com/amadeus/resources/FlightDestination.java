package com.amadeus.resources;

import java.util.Date;
import lombok.Getter;
import lombok.ToString;

/**
 * An FlightDestination object as returned by the FlightDestinations API.
 * @see com.amadeus.shopping.FlightDestinations#get()
 */
@ToString
public class FlightDestination extends Resource {
  protected FlightDestination() {}

  private @Getter String type;
  private @Getter String origin;
  private @Getter String destination;
  private @Getter Date departureDate;
  private @Getter Date returnDate;
  private @Getter Price price;

  /**
   * An FlightDestination-related object as returned by the FlightDestinations API.
   * @see com.amadeus.shopping.FlightDestinations#get()
   */
  @ToString
  public class Price {
    protected Price() {}

    private @Getter double total;
  }
}
