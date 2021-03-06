package com.productmanagment.product.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.productmanagment.product.model.CustomSequence;
import com.productmanagment.product.service.SequenceGenrationService;

@Service
public class SequenceGenrationServiceImpl implements SequenceGenrationService {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public Long generateProductSequence(String seqName) {
		Query query = new Query();
		CustomSequence counter = mongoOperations.findAndModify(query.addCriteria(Criteria.where("_id").is(seqName)),
				new Update().inc("proSeq", 1), FindAndModifyOptions.options().returnNew(true).upsert(true),
				CustomSequence.class);
		return !Objects.isNull(counter) ? counter.getProSeq() : 1;
	}

}
