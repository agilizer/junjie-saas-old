package com.agilemaster.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilemaster.cloud.entity.OauthTwoClient;

public interface OauthTwoClientRepository extends JpaRepository<OauthTwoClient, Long> {

	public OauthTwoClient findByClientId(String clientId);

	OauthTwoClient findByClientSecret(String clientSecret);
}
