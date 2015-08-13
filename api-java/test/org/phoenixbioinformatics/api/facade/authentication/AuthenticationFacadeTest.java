/**
 * Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.facade.authentication;


import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.phoenixbioinformatics.api.facade.ApiException;


/**
 * Test the ApiKey API facade class (AuthenticationFacade).
 * 
 * @author Robert J. Muller
 */
public class AuthenticationFacadeTest {
  private static Logger logger =
    Logger.getLogger(AuthenticationFacadeTest.class);

  // Basic success tests

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.authentication.AuthenticationFacade#getApiKeys()}
   * .
   */
  @Test
  public void testGetApiKeys() {
    AuthenticationFacade api =
      new AuthenticationFacade(new ApiKeySuccessFakeDataAccessDelegate());
    try {
      List<ApiKey> apiKeys = api.getApiKeys();
      assertTrue("No ApiKeys list created", apiKeys != null);
      assertTrue("Wrong number of ApiKeys: " + apiKeys.size(),
                 apiKeys.size() == 2);
      for (ApiKey key : apiKeys) {
        assertTrue("No API key in object", key.getApiKey() != null);
      }
    } catch (ApiException e) {
      logger.error("Failed on getting all ApiKey objects", e);
      fail("Failed on getting all ApiKey objects");
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.ApiKey.AuthenticationFacade#getApiKeysById(java.math.BigInteger)}
   * .
   */
  @Test
  public void testGetApiKeysById() {
    AuthenticationFacade api =
      new AuthenticationFacade(new ApiKeySuccessFakeDataAccessDelegate());
    try {
      List<ApiKey> apiKeys = api.getApiKeysById(BigInteger.ONE);
      assertTrue("No ApiKeys list created", apiKeys != null);
      assertTrue("Wrong number of ApiKeys: " + apiKeys.size(),
                 apiKeys.size() == 1);
      for (ApiKey apiKey : apiKeys) {
        assertTrue("No key in object", apiKey.getApiKey() != null);
      }
    } catch (ApiException e) {
      logger.error("Failed on getting ApiKey by id", e);
      fail("Failed on getting ApiKey by id");
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.ApiKey.AuthenticationFacade#createApiKey(org.phoenixbioinformatics.api.dto.ApiKey)}
   * .
   */
  @Test
  public void testCreateApiKey() {
    AuthenticationFacade api =
      new AuthenticationFacade(new ApiKeySuccessFakeDataAccessDelegate());
    try {
      api.createApiKey(getApiKey());
    } catch (ApiException e) {
      logger.error("Failed on creating ApiKey", e);
      fail("Failed on creating ApiKey");
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.ApiKey.AuthenticationFacade#updateApiKey(org.phoenixbioinformatics.api.dto.ApiKey)}
   * .
   */
  @Test
  public void testUpdateApiKey() {
    AuthenticationFacade api =
      new AuthenticationFacade(new ApiKeySuccessFakeDataAccessDelegate());
    try {
      api.updateApiKey(getApiKey());
    } catch (ApiException e) {
      logger.error("Failed on updating ApiKey", e);
      fail("Failed on updating ApiKey");
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.ApiKey.AuthenticationFacade#deleteApiKey(org.phoenixbioinformatics.api.dto.ApiKey)}
   * .
   */
  @Test
  public void testDeleteApiKey() {
    AuthenticationFacade api =
      new AuthenticationFacade(new ApiKeySuccessFakeDataAccessDelegate());
    try {
      api.deleteApiKey(getApiKey());
    } catch (ApiException e) {
      logger.error("Failed on deleting ApiKey", e);
      fail("Failed on deleting ApiKey");
    }
  }

  /**
   * Get an ApiKey object suitable for testing
   *
   * @return
   */
  private ApiKey getApiKey() {
    return new ApiKey("Test Key");
  }

  // Basic failure tests

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.ApiKey.AuthenticationFacade#getApiKeys()}
   * .
   */
  @Test
  public void testGetApiKeysFailure() {
    AuthenticationFacade api =
      new AuthenticationFacade(new ApiKeyFailureFakeDataAccessDelegate());
    try {
      api.getApiKeys();
      fail("No exception raised by API on getApiKeys()");
    } catch (ApiException e) {
      // successful test
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.ApiKey.AuthenticationFacade#getApiKeysById(java.math.BigInteger)}
   * .
   */
  @Test
  public void testGetApiKeysByIdFailure() {
    AuthenticationFacade api =
      new AuthenticationFacade(new ApiKeyFailureFakeDataAccessDelegate());
    try {
      api.getApiKeysById(BigInteger.ONE);
      fail("No exception raised by API on getApiKeysById()");
    } catch (ApiException e) {
      // successful test
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.ApiKey.AuthenticationFacade#createApiKey(org.phoenixbioinformatics.api.dto.ApiKey)}
   * .
   */
  @Test
  public void testCreateApiKeyFailure() {
    AuthenticationFacade api =
      new AuthenticationFacade(new ApiKeyFailureFakeDataAccessDelegate());
    try {
      api.createApiKey(getApiKey());
      fail("No exception raised by API on createApiKey()");
    } catch (ApiException e) {
      // successful test
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.ApiKey.AuthenticationFacade#updateApiKey(org.phoenixbioinformatics.api.dto.ApiKey)}
   * .
   */
  @Test
  public void testUpdateApiKeyFailure() {
    AuthenticationFacade api =
      new AuthenticationFacade(new ApiKeyFailureFakeDataAccessDelegate());
    try {
      api.updateApiKey(getApiKey());
      fail("No exception raised by API on updateApiKey()");
    } catch (ApiException e) {
      // successful test
    }
  }

  /**
   * Test method for
   * {@link org.phoenixbioinformatics.api.facade.ApiKey.AuthenticationFacade#deleteApiKey(org.phoenixbioinformatics.api.dto.ApiKey)}
   * .
   */
  @Test
  public void testDeleteApiKeyFailure() {
    AuthenticationFacade api =
      new AuthenticationFacade(new ApiKeyFailureFakeDataAccessDelegate());
    try {
      api.deleteApiKey(getApiKey());
      fail("No exception raised by API on deleteApiKey()");
    } catch (ApiException e) {
      // successful test
    }
  }
}
