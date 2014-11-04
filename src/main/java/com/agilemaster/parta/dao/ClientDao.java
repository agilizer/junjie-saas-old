package com.agilemaster.parta.dao;

import java.util.List;

import com.agilemaster.parta.entity.OauthTwoClient;
/**
 * 
 * @author abel.lee
 * 2014年9月23日 下午5:56:02
 */
public interface ClientDao {

    public OauthTwoClient createClient(OauthTwoClient client);
    public OauthTwoClient updateClient(OauthTwoClient client);
    public void deleteClient(Long clientId);

    OauthTwoClient findOne(Long clientId);

    List<OauthTwoClient> findAll();

    OauthTwoClient findByClientId(String clientId);
    OauthTwoClient findByClientSecret(String clientSecret);

}
