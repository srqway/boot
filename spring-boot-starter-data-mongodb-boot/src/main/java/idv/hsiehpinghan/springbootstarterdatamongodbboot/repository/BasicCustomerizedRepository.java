package idv.hsiehpinghan.springbootstarterdatamongodbboot.repository;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;

public interface BasicCustomerizedRepository {
	WriteResult updateFirst(Query query, Update update);
}
