package com.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class App {
  public static void main(String[] args) throws ResponseException {
    Amadeus amadeus = Amadeus.builder("RcWc4ABGVGnbEod6qUCKUPI0O6x3IMo5", "ehS0OPSiPMUQN4YZ").build();
    Response response = null;
    int pageSize = 0;


    // // 航空公司&城市查询
    // response = amadeus.referenceData.locations
    //     .get(Params.with("keyword", "LON")
    //     .and("subType", Locations.ANY));
    // getResultArray(response, pageSize);
    // while (response != null) {
    //   response = amadeus.next(response);
    //   pageSize++;
    //   getResultArray(response, pageSize);
    // }


    // // 预定信息查询？？？
    // response = amadeus.travel.analytics.airTraffic.booked.get(Params
    //     .with("originCityCode", "MAD")
    //     .and("period", "2018-01"));
    // getResultArray(response, pageSize);
    // while (response != null) {
    //   response = amadeus.next(response);
    //   pageSize++;
    //   getResultArray(response, pageSize);
    // }


    // response = amadeus.travel.analytics.airTraffic.searched.get(Params
    //     .with("originCityCode", "MAD")
    //     .and("searchPeriod", "2018-03")
    //     .and("marketCountryCode", "ES"));
    // getResultArray(response, pageSize);
    

    // response = amadeus.travel.analytics.airTraffic.searchedByDestination.get(Params
    //     .with("originCityCode", "MAD")
    //     .and("destinationCityCode", "LON")
    //     .and("searchPeriod", "2018-01")
    //     .and("marketCountryCode", "ES"));
    // getResultData(response);


    // // 航线信息查询
    // response = amadeus.referenceData.airlines.get(); // Params.with("airlineCodes", "ZH")
    // getResultArray(response, pageSize);

    // 飞行灵感搜索
    response = amadeus.shopping.flightDestinations.get(Params.with("origin", "LON"));
    getResultArray(response, pageSize);
    while (response != null) {
      response = amadeus.next(response);
      pageSize++;
      getResultArray(response, pageSize);
    }
  }

  private static void getResultArray(Response response, int pageSize) {
    if (response != null) {
      if (response.getResult() != null) {
        JsonObject jsonResult = response.getResult();
        JsonArray jsonArr = jsonResult.getAsJsonArray("data");
        if (jsonArr != null && jsonArr.size() > 0) {
          for (int i = 0; i < jsonArr.size(); i++) {
            System.out.println(jsonArr.get(i).toString());
            System.out.println((pageSize*10+i)+"----------------------------------------------------------------------------------------------------");
          }
        }
      }
    }
  }

  private static void getResultData(Response response) {
    if (response != null) {
      if (response.getResult() != null) {
        JsonObject jsonResult = response.getResult();
        JsonObject jsonData = jsonResult.getAsJsonObject("data");
        System.out.println(jsonData.toString());
        System.out.println("----------------------------------------------------------------------------------------------------");
      }
    }
  }
}
