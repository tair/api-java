/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.rs.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * Unit/Integration tests for the partners API services
 * 
 * @author Robert J. Muller
 */
public class TempPartnerServiceTest {
  private static final String PARTNERS_URI =
    "http://localhost:8080/java-api/partners";
  /** logger for this class */
  private static final Logger logger =
    Logger.getLogger(TempPartnerServiceTest.class);

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.rs.service.PartnerService#getAllPartnersJson()}
   * .
   * 
   * @throws IOException
   */
  @Test
  public void testGetAllPartnersJson() {
    HttpURLConnection connection = null;
    BufferedReader reader = null;
    try {
      URL uri = new URL(PARTNERS_URI);
      connection = (HttpURLConnection)uri.openConnection();
      reader =
        new BufferedReader(new InputStreamReader(connection.getInputStream()));

      String line = reader.readLine();
      while (line != null) {
        logger.info("JSON ouptut: " + line);
        line = reader.readLine();
      }
      assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
    } catch (Exception e) {
      logger.error("Error", e);
      fail("Error: " + e.getMessage());
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          logger.error("Error", e);
          fail("IO error on connection " + PARTNERS_URI + e.getMessage());
        }
      }
    }
  }

}
