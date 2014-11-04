package com.agilemaster.cloud.service.oauth2;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agilemaster.cloud.dao.ClientDao;
import com.agilemaster.cloud.entity.OauthTwoClient;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-17
 * <p>Version: 1.0
 */
@Transactional
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientDao clientDao;

    @Override
    public OauthTwoClient createClient(OauthTwoClient client) {

        client.setClientId(UUID.randomUUID().toString());
        client.setClientSecret(UUID.randomUUID().toString());
        return clientDao.createClient(client);
    }

    @Override
    public OauthTwoClient updateClient(OauthTwoClient client) {
        return clientDao.updateClient(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientDao.deleteClient(clientId);
    }

    @Override
    public OauthTwoClient findOne(Long clientId) {
        return clientDao.findOne(clientId);
    }

    @Override
    public List<OauthTwoClient> findAll() {
        return clientDao.findAll();
    }

    @Override
    public OauthTwoClient findByClientId(String clientId) {
        return clientDao.findByClientId(clientId);
    }

    @Override
    public OauthTwoClient findByClientSecret(String clientSecret) {
        return clientDao.findByClientSecret(clientSecret);
    }
}
