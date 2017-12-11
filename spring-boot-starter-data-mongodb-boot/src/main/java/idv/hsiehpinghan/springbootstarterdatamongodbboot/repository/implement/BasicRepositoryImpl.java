package idv.hsiehpinghan.springbootstarterdatamongodbboot.repository.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;

import idv.hsiehpinghan.springbootstarterdatamongodbboot.document.BasicDocument;
import idv.hsiehpinghan.springbootstarterdatamongodbboot.repository.BasicCustomerizedRepository;

public class BasicRepositoryImpl implements BasicCustomerizedRepository {
	private final Class<BasicDocument> ENTITY_CLASS = BasicDocument.class;
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public WriteResult updateFirst(Query query, Update update) {
		return mongoTemplate.updateFirst(query, update, ENTITY_CLASS);
	}
}
