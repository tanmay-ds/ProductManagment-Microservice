package com.productmanagment.userinfo.service;

import org.springframework.stereotype.Service;

@Service
public interface SequenceGenrationService {
	
	Long generateUsersSequence(String seqName);
	
}
