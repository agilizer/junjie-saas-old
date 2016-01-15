package com.agilemaster.partbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilemaster.partbase.entity.OauthTwoClient;

public interface OauthTwoClientRepository extends JpaRepository<OauthTwoClient, Long> {

	public OauthTwoClient findByClientId(String clientId);

	OauthTwoClient findByClientSecret(String clientSecret);
}
