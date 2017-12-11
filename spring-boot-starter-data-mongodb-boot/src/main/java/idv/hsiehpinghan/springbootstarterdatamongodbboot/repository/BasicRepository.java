package idv.hsiehpinghan.springbootstarterdatamongodbboot.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import idv.hsiehpinghan.springbootstarterdatamongodbboot.document.BasicDocument;

public interface BasicRepository extends MongoRepository<BasicDocument, ObjectId>, BasicCustomerizedRepository {
	BasicDocument findOneByIntValue(int intValue);

	@Query("{'stringValue' : { '$in' : [ '?0' ] }}")
	List<BasicDocument> queryByStringValue(String stringValue);

}
