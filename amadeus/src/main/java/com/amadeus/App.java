package com.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.Location;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class App {
  public static void main(String[] args) throws ResponseException {
    Amadeus amadeus = Amadeus.builder("RcWc4ABGVGnbEod6qUCKUPI0O6x3IMo5", "ehS0OPSiPMUQN4YZ").build();

    Response response = amadeus.referenceData.locations
        .get(Params.with("keyword", "NAY").and("subType", Locations.ANY));
    int pageSize = 0;
    getResult(response, pageSize);
    while (response != null) {
      response = amadeus.next(response);
      pageSize++;
      getResult(response, pageSize);
    }
  }

  private static void getResult(Response response, int pageSize) {
    if (response != null) {
      if (response.getResult() != null) {
        JsonObject jsonObj = response.getResult();
        JsonArray jsonArr = jsonObj.getAsJsonArray("data");
        if (jsonArr != null && jsonArr.size() > 0) {
          for (int i = 0; i < jsonArr.size(); i++) {
            System.out.println(jsonArr.get(i).toString());
            System.out.println((pageSize*10+i)+"----------------------------------------------------------------------------------------------------");
          }
        }
      }
    }
  }
}
