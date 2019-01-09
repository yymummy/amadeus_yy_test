package com.amadeus;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Airlines;
import com.amadeus.referenceData.Location;
import com.amadeus.referenceData.Locations;
import com.amadeus.referenceData.locations.Airports;
import com.amadeus.referenceData.urls.CheckinLinks;
import com.amadeus.shopping.FlightDates;
import com.amadeus.shopping.FlightDestinations;
import com.amadeus.shopping.FlightOffers;
import com.amadeus.shopping.HotelOffers;
import com.amadeus.shopping.hotel.Offer;
import com.amadeus.travel.analytics.airTraffic.Booked;
import com.amadeus.travel.analytics.airTraffic.BusiestPeriod;
import com.amadeus.travel.analytics.airTraffic.Searched;
import com.amadeus.travel.analytics.airTraffic.SearchedByDestination;
import com.amadeus.travel.analytics.airTraffic.Traveled;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class NamespaceTest {

  private Amadeus client;
  private Params params;
  private Response singleResponse;
  private Response multiResponse;

  @Test
  public void testAllNamespacesExist() {
    Amadeus client = Amadeus.builder("id", "secret").build();
    TestCase.assertNotNull(client.referenceData.urls.checkinLinks);
    TestCase.assertNotNull(client.referenceData.locations.airports);
    TestCase.assertNotNull(client.referenceData.location("123"));
    TestCase.assertNotNull(client.referenceData.airlines);
    TestCase.assertNotNull(client.travel.analytics.airTraffic.traveled);
    TestCase.assertNotNull(client.travel.analytics.airTraffic.booked);
    TestCase.assertNotNull(client.travel.analytics.airTraffic.searched);
    TestCase.assertNotNull(client.travel.analytics.airTraffic.searchedByDestination);
    TestCase.assertNotNull(client.shopping.flightDates);
    TestCase.assertNotNull(client.shopping.flightDestinations);
    TestCase.assertNotNull(client.shopping.flightOffers);
    TestCase.assertNotNull(client.shopping.hotelOffers);
    TestCase.assertNotNull(client.shopping.hotel("123").hotelOffers);
    TestCase.assertNotNull(client.shopping.hotel("123").offer("234"));
  }

  @Before
  public void setup() {
    client = Mockito.mock(Amadeus.class);
    params = Params.with("airline", "1X");

    // Prepare a plural response
    JsonArray jsonArray = new JsonArray();
    jsonArray.add(new JsonObject());
    jsonArray.add(new JsonObject());
    multiResponse = Mockito.mock(Response.class);
    Mockito.when(multiResponse.isParsed()).thenReturn(true);
    Mockito.when(multiResponse.getData()).thenReturn(jsonArray);

    // Prepare a single response
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("foo", "bar");
    singleResponse = Mockito.mock(Response.class);
    Mockito.when(singleResponse.isParsed()).thenReturn(true);
    Mockito.when(singleResponse.getData()).thenReturn(jsonObject);
  }

  @Test
  public void testGetMethods() throws ResponseException {
    // Testing CheckinLinks
    Mockito.when(client.get("/v2/reference-data/urls/checkin-links", null))
        .thenReturn(multiResponse);
    Mockito.when(client.get("/v2/reference-data/urls/checkin-links", params))
        .thenReturn(multiResponse);
    CheckinLinks checkinLinks = new CheckinLinks(client);

    TestCase.assertNotNull(checkinLinks.get());
    TestCase.assertNotNull(checkinLinks.get(params));
    TestCase.assertEquals(checkinLinks.get().length, 2);

    // Testing location search
    Mockito.when(client.get("/v1/reference-data/locations", null))
        .thenReturn(multiResponse);
    Mockito.when(client.get("/v1/reference-data/locations", params))
        .thenReturn(multiResponse);
    Locations locations = new Locations(client);
    TestCase.assertNotNull(locations.get());
    TestCase.assertNotNull(locations.get(params));
    TestCase.assertEquals(locations.get().length, 2);

    // Testing airport search
    Mockito.when(client.get("/v1/reference-data/locations/airports", null))
        .thenReturn(multiResponse);
    Mockito.when(client.get("/v1/reference-data/locations/airports", params))
        .thenReturn(multiResponse);
    Airports airports = new Airports(client);
    TestCase.assertNotNull(airports.get());
    TestCase.assertNotNull(airports.get(params));
    TestCase.assertEquals(airports.get().length, 2);

    // Testing fetching a single location
    Mockito.when(client.get("/v1/reference-data/locations/ALHR", null))
        .thenReturn(singleResponse);
    Mockito.when(client.get("/v1/reference-data/locations/ALHR", params))
        .thenReturn(singleResponse);
    Location location = new Location(client, "ALHR");
    TestCase.assertNotNull(location.get());
    TestCase.assertNotNull(location.get(params));

    // Testing airlines search
    Mockito.when(client.get("/v1/reference-data/airlines", null))
        .thenReturn(multiResponse);
    Mockito.when(client.get("/v1/reference-data/airlines", params))
        .thenReturn(multiResponse);
    Airlines airlines = new Airlines(client);
    TestCase.assertNotNull(airlines.get());
    TestCase.assertNotNull(airlines.get(params));
    TestCase.assertEquals(airlines.get().length, 2);

    // Testing traveled stats
    Mockito.when(client.get("/v1/travel/analytics/air-traffic/traveled", null))
        .thenReturn(multiResponse);
    Mockito.when(client.get("/v1/travel/analytics/air-traffic/traveled", params))
        .thenReturn(multiResponse);
    Traveled traveled = new Traveled(client);
    TestCase.assertNotNull(traveled.get());
    TestCase.assertNotNull(traveled.get(params));
    TestCase.assertEquals(traveled.get().length, 2);

    // Testing booked stats
    Mockito.when(client.get("/v1/travel/analytics/air-traffic/booked", null))
        .thenReturn(multiResponse);
    Mockito.when(client.get("/v1/travel/analytics/air-traffic/booked", params))
        .thenReturn(multiResponse);
    Booked booked = new Booked(client);
    TestCase.assertNotNull(booked.get());
    TestCase.assertNotNull(booked.get(params));
    TestCase.assertEquals(booked.get().length, 2);

    // Testing busiest traveling period
    Mockito.when(client.get("/v1/travel/analytics/air-traffic/busiest-period", null))
        .thenReturn(multiResponse);
    Mockito.when(client.get("/v1/travel/analytics/air-traffic/busiest-period", params))
        .thenReturn(multiResponse);
    BusiestPeriod busiestPeriod = new BusiestPeriod(client);
    TestCase.assertNotNull(busiestPeriod.get());
    TestCase.assertNotNull(busiestPeriod.get(params));
    TestCase.assertEquals(busiestPeriod.get().length, 2);

    // Testing most searched destinations
    Mockito.when(client.get("/v1/travel/analytics/air-traffic/searched", null))
        .thenReturn(multiResponse);
    Mockito.when(client.get("/v1/travel/analytics/air-traffic/searched", params))
        .thenReturn(multiResponse);
    Searched searches = new Searched(client);
    TestCase.assertNotNull(searches.get());
    TestCase.assertNotNull(searches.get(params));
    TestCase.assertEquals(searches.get().length, 2);

    // Testing searched stats
    Mockito.when(client.get("/v1/travel/analytics/air-traffic/searched/by-destination", null))
        .thenReturn(singleResponse);
    Mockito.when(client.get("/v1/travel/analytics/air-traffic/searched/by-destination", params))
        .thenReturn(singleResponse);
    SearchedByDestination searchesByDestination = new SearchedByDestination(client);
    TestCase.assertNotNull(searchesByDestination.get());
    TestCase.assertNotNull(searchesByDestination.get(params));

    // Testing flight date search
    Mockito.when(client.get("/v1/shopping/flight-dates", null))
        .thenReturn(multiResponse);
    Mockito.when(client.get("/v1/shopping/flight-dates", params))
        .thenReturn(multiResponse);
    FlightDates flightDates = new FlightDates(client);
    TestCase.assertNotNull(flightDates.get());
    TestCase.assertNotNull(flightDates.get(params));
    TestCase.assertEquals(flightDates.get().length, 2);

    // Testing flight destination search
    Mockito.when(client.get("/v1/shopping/flight-destinations", null))
        .thenReturn(multiResponse);
    Mockito.when(client.get("/v1/shopping/flight-destinations", params))
        .thenReturn(multiResponse);
    FlightDestinations flightDestinations = new FlightDestinations(client);
    TestCase.assertNotNull(flightDestinations.get());
    TestCase.assertNotNull(flightDestinations.get(params));
    TestCase.assertEquals(flightDestinations.get().length, 2);

    // Testing flight offer search
    Mockito.when(client.get("/v1/shopping/flight-offers", null))
        .thenReturn(multiResponse);
    Mockito.when(client.get("/v1/shopping/flight-offers", params))
        .thenReturn(multiResponse);
    FlightOffers flightOffers = new FlightOffers(client);
    TestCase.assertNotNull(flightOffers.get());
    TestCase.assertNotNull(flightOffers.get(params));
    TestCase.assertEquals(flightOffers.get().length, 2);

    // Testing hotel offer search
    Mockito.when(client.get("/v1/shopping/hotel-offers", null))
        .thenReturn(multiResponse);
    Mockito.when(client.get("/v1/shopping/hotel-offers", params))
        .thenReturn(multiResponse);
    HotelOffers hotelOffers = new HotelOffers(client);
    TestCase.assertNotNull(hotelOffers.get());
    TestCase.assertNotNull(hotelOffers.get(params));
    TestCase.assertEquals(hotelOffers.get().length, 2);

    // Testing hotel offer search for a hotel
    Mockito.when(client.get("/v1/shopping/hotels/123/hotel-offers", null))
        .thenReturn(singleResponse);
    Mockito.when(client.get("/v1/shopping/hotels/123/hotel-offers", params))
        .thenReturn(singleResponse);
    com.amadeus.shopping.hotel.HotelOffers hotelOffers2
        = new com.amadeus.shopping.hotel.HotelOffers(client, "123");
    TestCase.assertNotNull(hotelOffers2.get());
    TestCase.assertNotNull(hotelOffers2.get(params));

    // Test fetching a specific offer
    Mockito.when(client.get("/v1/shopping/hotels/123/offers/234", null))
        .thenReturn(singleResponse);
    Mockito.when(client.get("/v1/shopping/hotels/123/offers/234", params))
        .thenReturn(singleResponse);
    Offer offer = new Offer(client, "123", "234");
    TestCase.assertNotNull(offer.get());
    TestCase.assertNotNull(offer.get(params));
  }
}
