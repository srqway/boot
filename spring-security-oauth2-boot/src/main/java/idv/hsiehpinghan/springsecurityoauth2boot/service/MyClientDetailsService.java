package idv.hsiehpinghan.springsecurityoauth2boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springsecurityoauth2boot.entity.ClientEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.utility.ConvertUtility;

//@Service
//@Transactional
public class MyClientDetailsService implements ClientDetailsService {
	@Autowired
	private ClientService clientService;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		ClientEntity clientEntity = clientService.findOne(clientId);
		BaseClientDetails baseClientDetails = ConvertUtility.convertToBaseClientDetails(clientEntity);
		return baseClientDetails;
	}

}
