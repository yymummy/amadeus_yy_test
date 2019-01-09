package com.amadeus;

/**
 * Hello world!
 *
 */
import com.amadeus.Amadeus;
import com.amadeus.Params;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;

public class App {
  public static void main(String[] args) throws ResponseException {
    Amadeus amadeus = Amadeus
            .builder("RcWc4ABGVGnbEod6qUCKUPI0O6x3IMo5", "ehS0OPSiPMUQN4YZ")
            .build();

    Location[] locations = amadeus.referenceData.locations.get(Params
      .with("keyword", "LON")
      .and("subType", Locations.ANY));
    if (locations.length > 0) {
      for (int i = 0; i < locations.length; i++) {
        System.out.println(locations[i].toString());
        System.out.println("----------------------------------------------------------------------------------------------------");
      }
    }
  }
}
