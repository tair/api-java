/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.rs.service;


import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * Base class for all web services; contains the Jackson JSON mapping code used
 * to produce JSON objects for return by the web services
 * 
 * @author Robert J. Muller
 */
public class AbstractWebService extends HttpServlet {

  /** serial version UID for serializable class */
  private static final long serialVersionUID = 1L;

  /** JSON object/string mapper */
  private static ObjectMapper mapper = new ObjectMapper();

  /** JSON generator factory */
  private static JsonFactory factory = new JsonFactory();

  /** list of messages for return as a JSON array */
  private final List<String> messages = new ArrayList<String>(1);

  /**
   * Generate a JSON string from a Java Object.
   * 
   * @param object the object with which to generate JSON
   * @param logger the local logger for logging errors
   * @return the JSON string or a JSON string with an error message
   * 
   */
  protected String generateJson(Object object, Logger logger) {
    String json = null;
    StringWriter sw = new StringWriter();
    try {
      JsonGenerator jg = factory.createJsonGenerator(sw);
      mapper.writeValue(jg, object);
      json = sw.toString();
    } catch (JsonGenerationException e) {
      json = "{\"ERROR: JSON generation problem: " + e.getMessage() + "\"}";
      logger.error("JSON generation problem: " + e.getMessage(), e);
    } catch (JsonMappingException e) {
      json = "{\"ERROR: JSON mapping problem: " + e.getMessage() + "\"}";
      logger.error("JSON mapping problem: " + e.getMessage(), e);
    } catch (IOException e) {
      json = "{\"ERROR: IO problem: " + e.getMessage() + "\"}";
      logger.error("IO problem: " + e.getMessage(), e);
    }
    return json;
  }

  /**
   * Add a message to the message list being built
   *
   * @param message the message to add
   */
  protected void addMessage(String message) {
    messages.add(message);
  }

  /**
   * Get a JSON array containing the messages from the message list.
   *
   * @return a JSON array
   */
  protected String getMessagesAsJson(Logger logger) {
    return generateJson(messages, logger);
  }
}
