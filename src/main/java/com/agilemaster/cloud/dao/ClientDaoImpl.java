package com.agilemaster.cloud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agilemaster.cloud.entity.OauthTwoClient;
import com.agilemaster.cloud.repository.OauthTwoClientRepository;

/**
 * 
 * @author abel.lee
 * 2014年9月23日 下午5:56:09
 */
@Repository
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private OauthTwoClientRepository clientRepository;
    
    public OauthTwoClient createClient(final OauthTwoClient client) {
        return clientRepository.save(client);
    }

    public OauthTwoClient updateClient(OauthTwoClient client) {
    	return clientRepository.save(client);
    }

    public void deleteClient(Long clientId) {
    	clientRepository.delete(clientId);
    }

    @Override
    public OauthTwoClient findOne(Long clientId) {
    	return clientRepository.findOne(clientId);
    }

    @Override
    public List<OauthTwoClient> findAll() {
        return clientRepository.findAll();
    }


    @Override
    public OauthTwoClient findByClientId(String clientId) {
    	return clientRepository.findByClientId(clientId);
    }

    @Override
    public OauthTwoClient findByClientSecret(String clientSecret) {
    	return clientRepository.findByClientSecret(clientSecret);
    }
}
