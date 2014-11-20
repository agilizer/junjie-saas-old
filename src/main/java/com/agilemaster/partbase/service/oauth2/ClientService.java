package com.agilemaster.partbase.service.oauth2;

import java.util.List;

import com.agilemaster.partbase.entity.OauthTwoClient;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface ClientService {

    public OauthTwoClient createClient(OauthTwoClient client);
    public OauthTwoClient updateClient(OauthTwoClient client);
    public void deleteClient(Long clientId);

    OauthTwoClient findOne(Long clientId);

    List<OauthTwoClient> findAll();

    OauthTwoClient findByClientId(String clientId);
    OauthTwoClient findByClientSecret(String clientSecret);

}
