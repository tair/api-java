/**
* Copyright Phoenix Bioinformatics Corporation 2015. All rights reserved.
 */
package org.phoenixbioinformatics.api.rs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.phoenixbioinformatics.api.rs.service.PartnerService;

/**
 * The main Application class for the Phoenix API, providing the root URI.
 * 
 * @author Robert J. Muller
 */
@ApplicationPath("/")
public class PhoenixApiApplication extends Application {
  /** the set of service object singletons */
  private Set<Object> singletons = new HashSet<Object>();

  /**
   * Create a PhoenixApiApplication object.
   */
  public PhoenixApiApplication() {
     singletons.add(new PartnerService());
  }

  @Override
  public Set<Object> getSingletons() {
     return singletons;
  }
}
