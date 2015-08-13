/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.rs.service;


import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.phoenixbioinformatics.api.RandomString;


/**
 * 
 * @author Robert J. Muller
 */
public class PartnerServiceTest extends AbstractWebServiceTest {
  /** logger for this class */
  static Logger logger = Logger.getLogger(PartnerServiceTest.class);

  static final String PARTNERS_URI = "http://localhost:8080/java-api/partners";

  static final String FAKE_QUERY_STRING = "?fake=true";

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.rs.service.PartnerService#getAllPartnersJson()}
   * .
   * 
   * @throws MalformedURLException when the URI has a format problem
   */
  @Test
  public void testGetAllPartnersJson() throws MalformedURLException {
    URL uri = new URL(PARTNERS_URI + FAKE_QUERY_STRING);
    String json = doGet(uri, HttpURLConnection.HTTP_OK);
    assertTrue("JSON array is null", json != null);
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.rs.service.PartnerService#insertPartnerJson(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
   * .
   * 
   * @throws MalformedURLException when there is a problem with the URI
   * @throws UnsupportedEncodingException when there is a problem uu-encoding
   *           the form parameters
   */
  @Test
  public void testInsertPartnerJson() throws MalformedURLException,
      UnsupportedEncodingException {
    StringBuilder builder = new StringBuilder();
    String partnerId = RandomString.getString(30);
    String name = RandomString.getString(30);
    String logo = RandomString.getString(50);
    String termOfService = RandomString.getString(50);

    builder.append("partnerId=");
    builder.append(URLEncoder.encode(partnerId, "UTF-8"));
    builder.append("&");

    builder.append("name=");
    builder.append(URLEncoder.encode(name, "UTF-8"));
    builder.append("&");

    builder.append("logoUri=");
    builder.append(URLEncoder.encode(logo, "UTF-8"));
    builder.append("&");

    builder.append("termOfServiceUri=");
    builder.append(URLEncoder.encode(termOfService, "UTF-8"));

    URL uri = new URL(PARTNERS_URI + FAKE_QUERY_STRING);

    String json =
      doPost(uri, builder.toString(), HttpURLConnection.HTTP_CREATED);
    assertTrue("JSON array is null", json != null);
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.rs.service.PartnerService#updatePartnerJson(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
   * .
   * 
   * @throws UnsupportedEncodingException when there is a problem uu-encoding
   *           the form parameters
   * @throws MalformedURLException when the URI has a format problem
   */
  @Test
  public void testUpdatePartnerJson() throws UnsupportedEncodingException,
      MalformedURLException {
    StringBuilder builder = new StringBuilder();

    builder.append("partnerId=");
    builder.append(URLEncoder.encode("tair", "UTF-8"));
    builder.append("&");

    builder.append("name=");
    builder.append(URLEncoder.encode("TAIR", "UTF-8"));
    builder.append("&");

    builder.append("logoUri=");
    builder.append(URLEncoder.encode("updated", "UTF-8"));
    builder.append("&");

    builder.append("termOfServiceUri=");
    builder.append(URLEncoder.encode("updated", "UTF-8"));
    builder.append("&");

    builder.append("fake=");
    builder.append(URLEncoder.encode("true", "UTF-8"));

    URL uri = new URL(PARTNERS_URI); // no query string here please

    String json = doPut(uri, builder.toString(), HttpURLConnection.HTTP_OK);
    assertTrue("JSON array is null", json != null);
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.rs.service.PartnerService#deletePartnerJson(java.lang.String)}
   * .
   * 
   * @throws MalformedURLException when there is a problem with the URI
   * @throws UnsupportedEncodingException when there is a problem uu-encoding
   *           the form parameters
   */
  @Test
  public void testDeletePartnerJson() throws UnsupportedEncodingException,
      MalformedURLException {
    URL uri = new URL(PARTNERS_URI + FAKE_QUERY_STRING + "&partnerId=tair");

    doDelete(uri, HttpURLConnection.HTTP_OK);
  }

}
