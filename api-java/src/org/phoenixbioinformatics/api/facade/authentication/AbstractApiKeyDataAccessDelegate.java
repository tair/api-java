package org.phoenixbioinformatics.api.facade.authentication;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.phoenixbioinformatics.api.bs.api.BsApiKey;
import org.phoenixbioinformatics.api.db.api.ApiFactory;

import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.db.pk.SequencePrimaryKey;


/**
 * An abstract implementation of the ApiKey data access delegate interface that
 * provides production data access methods to the ApiKey facade. Subclasses must
 * implement the actual delegate, permitting different kinds of delegate.
 * 
 * @author Robert J. Muller
 */
public abstract class AbstractApiKeyDataAccessDelegate implements
    IApiKeyDataAccessDelegate {

  public AbstractApiKeyDataAccessDelegate() {
    super();
  }

  @Override
  public List<BsApiKey> getAllObjects(int i) throws DelegateException {
    return getDelegate().getAllObjects(i);
  }

  @Override
  public List<BsApiKey> getApiKeyById(BigInteger id) throws DelegateException {
    List<BsApiKey> list = new ArrayList<BsApiKey>(1);
    IPrimaryKey key = ApiFactory.getApiKeyPrimaryKey(id);
    list.add(getDelegate().getObject((SequencePrimaryKey)key));
    return list;
  }

  @Override
  public void process(ApiKey ApiKey) throws DelegateException {
    List<BsApiKey> list = new ArrayList<BsApiKey>(1);
    BsApiKey businessObject =
      getDelegate().createApiKey(null, ApiKey.getApiKey());
    list.add(businessObject);
    getDelegate().process(list);
  }

  @Override
  public void process(List<ApiKey> list) throws DelegateException {
    List<BsApiKey> bsList = new ArrayList<BsApiKey>(list.size());
    if (bsList != null) {
      for (ApiKey ApiKey : list) {
        BsApiKey businessObject =
          getDelegate().createApiKey(null, ApiKey.getApiKey());
        bsList.add(businessObject);
      }
    }
    getDelegate().process(bsList);
  }

}