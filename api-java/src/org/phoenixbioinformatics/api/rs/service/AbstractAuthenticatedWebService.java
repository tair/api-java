/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.rs.service;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.phoenixbioinformatics.api.facade.ApiException;
import org.phoenixbioinformatics.api.facade.authentication.ApiKey;
import org.phoenixbioinformatics.api.facade.authentication.IAuthenticationFacade;


/**
 * 
 * @author Robert J. Muller
 */
public class AbstractAuthenticatedWebService extends AbstractWebService {
  /** serial version UID for serializable class */
  private static final long serialVersionUID = 1L;
  /** logger for this class */
  private static Logger logger =
    Logger.getLogger(AbstractAuthenticatedWebService.class);

  // error messages
  private static final String AUTH_FAILURE_ERROR =
    "Authentication failure due to error";

  /**
   * Create a AbstractAuthenticatedWebService object.
   * 
   * @param authApi the API facade to use for authentication
   */
  public AbstractAuthenticatedWebService(IAuthenticationFacade authApi) {
    super();
    this.authApi = authApi;
  }

  private final IAuthenticationFacade authApi;

  /**
   * Authenticate the caller using a specified key. The method compares the key
   * to all the keys in the database and if it finds a match returns true,
   * otherwise returns false.
   *
   * @param inputKey the key to match
   * @return true if the key matches one in the database, false otherwise
   */
  protected boolean authenticate(String inputKey) {
    boolean authenticated = false;
    // Use the API to get the API keys from the database, then iterate through
    // them; if one matches the input key, return success.

    // TODO replace with custom query on key itself as optimization

    try {
      for (ApiKey key : authApi.getApiKeys()) {
        if (key.getApiKey().equalsIgnoreCase(inputKey)) {
          authenticated = true;
          break; // only need to match one
        }
      }
    } catch (ApiException e) {
      logger.error(AUTH_FAILURE_ERROR, e);
      throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }
    return authenticated;
  }
}
