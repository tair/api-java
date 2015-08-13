package org.phoenixbioinformatics.api.rs.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;


public class AbstractWebServiceTest {
  /** logger for this class */
  static Logger logger = Logger.getLogger(PartnerServiceTest.class);

  public AbstractWebServiceTest() {
    super();
  }

  /**
   * Call the API web service as a GET operation and return any JSON.
   *
   * @param uri the URI constituting the web service call
   * @param code the HTTP return code that the call should return
   */
  protected String doGet(URL uri, int code) {
    String output = null;
    HttpURLConnection connection = null;
    try {
      connection = (HttpURLConnection)uri.openConnection();
      output = getResponseString(connection, uri.toString(), code);
    } catch (Exception e) {
      logger.error("Error", e);
      fail("Error: " + e.getMessage());
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
    return output;
  }

  /**
   * Do a POST web service call, returning the string output from the call.
   *
   * @param uri the URI to post
   * @param formData the url-encoded data containing form fields, like "id=" +
   *          UrlEncoder.encode("2013")
   * @param code the expected return code
   * @return the output string
   */
  protected String doPost(URL uri, String formData, int code) {
    String output = null;
    HttpURLConnection connection = null;

    // Require some actual form data.
    if (formData == null) {
      throw new RuntimeException("No form data to POST");
    }

    try {
      // Set up the connection.
      connection = (HttpURLConnection)uri.openConnection();
      connection.setRequestMethod("POST");

      // Set the form parameters from the uuencoded data.
      setFormParams(formData, connection);

      // Get the response.
      output = getResponseString(connection, uri.toString(), code);
    } catch (Exception e) {
      logger.error("Error", e);
      fail("Error: " + e.getMessage());
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
    return output;
  }

  /**
   * <p>
   * Do a PUT web service call, returning the string output from the call.
   * </p>
   * <p>
   * There is a bug with the resteasy implementation of PUT that disables form
   * parameters if there are any query parameters. You must therefore choose one
   * or the other but not both. It's probably better to use the form parameters
   * by building the entity from all the required data, so that's what I'm
   * requiring here. See https://issues.jboss.org/browse/RESTEASY-1141
   * </p>
   *
   * @param uri the URI to put
   * @param formData the url-encoded data containing form fields, like "id=" +
   *          UrlEncoder.encode("2013")
   * @param code the expected return code
   * @return the output string
   */
  protected String doPut(URL uri, String formData, int code) {
    String output = null;
    HttpURLConnection connection = null;

    // Require some actual form data.
    if (formData == null) {
      throw new RuntimeException("No form data to PUT");
    }

    if (uri.getQuery() != null) {
      throw new RuntimeException("Resteasy does not support combination of form and query string parameters for PUT.");
    }

    try {
      // Set up the connection.
      connection = (HttpURLConnection)uri.openConnection();
      connection.setRequestMethod("PUT");

      // Set the form parameters from the uuencoded data.
      setFormParams(formData, connection);

      // Get the response.
      output = getResponseString(connection, uri.toString(), code);
    } catch (Exception e) {
      logger.error("Error", e);
      fail("Error: " + e.getMessage());
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
    return output;
  }

  /**
   * Call the API web service as a DELETE operation.
   *
   * @param uri the URI constituting the web service call and the partnerId to
   *          delete
   * @param code the HTTP return code that the call should return
   */
  protected void doDelete(URL uri, int code) {
    HttpURLConnection connection = null;
    try {
      connection = (HttpURLConnection)uri.openConnection();
      connection.getInputStream();
      assertEquals(code, connection.getResponseCode());
    } catch (Exception e) {
      logger.error("Error", e);
      fail("Error: " + e.getMessage());
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }

  /**
   * Get a response string from an open HttpURLConnection and reader
   *
   * @param connection the HTTP connection
   * @param uri the URI for the connection
   * @param code the expected return code
   * @return the output string if any
   * @throws IOException when there is an I/O problem reading the input stream
   */
  private String getResponseString(HttpURLConnection connection, String uri,
                                   int code) throws IOException {
    String output = null;
    BufferedReader reader = null;
    try {
      reader =
        new BufferedReader(new InputStreamReader(connection.getInputStream()));

      String line = reader.readLine();
      while (line != null) {
        output = line;
        logger.info("JSON ouptut: " + line);
        line = reader.readLine();
      }
      assertEquals(code, connection.getResponseCode());
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          logger.error("Error", e);
          fail("IO error on connection " + uri + ": " + e.getMessage());
        }
      }
    }
    return output;
  }

  /**
   * Set form parameters from a uuencoded string into an open HttpURLConnection.
   *
   * @param formData the uuencoded form data
   * @param connection the HTTP connection
   * @throws IOException when there is a problem writing the output stream
   */
  private void setFormParams(String formData, HttpURLConnection connection)
      throws IOException {
    DataOutputStream outputStream = null;
    try {
      // Set the various connection features required for entity transfer.
      connection.setRequestProperty("Content-Type",
                                    "application/x-www-form-urlencoded");
      connection.setUseCaches(false);
      connection.setDoInput(true);
      connection.setDoOutput(true);

      // Write the form data.
      outputStream = new DataOutputStream(connection.getOutputStream());
      outputStream.writeBytes(formData);
    } finally {
      if (outputStream != null) {
        outputStream.flush();
        outputStream.close();
      }
    }
  }
}