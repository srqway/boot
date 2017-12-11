package idv.hsiehpinghan.springbootstarterdatamongodbboot.repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.WriteResult;

import idv.hsiehpinghan.springbootstarterdatamongodbboot.document.BasicDocument;
import idv.hsiehpinghan.springbootstarterdatamongodbboot.document.BasicDocument.SubDocument;
import idv.hsiehpinghan.springbootstarterdatamongodbboot.enumeration.Enumeration;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasicRepositoryTest {
	private static final ObjectId ID = new ObjectId();
	private static final double DOUBLE = 1.1; // double
	private static final String STRING = "string"; // string
	// Object 3 “object”
	private static final List<String> ARRAY = Arrays.asList("繁體中文字串測試_0", "其他不相關字串_1", "array_2"); // array
	private static final byte[] BIN_DATA = getBinData(); // binData
	// Undefined 6 “undefined” Deprecated.
	private static final ObjectId OBJECT_ID = new ObjectId(); // objectId
	private static final boolean BOOL = true; // bool
	private static final Date DATE = new Date(); // date
	private static final Object NULL = null; // null
	// Regular Expression 11 “regex”
	// DBPointer 12 “dbPointer” Deprecated.
	// JavaScript 13 “javascript”
	// Symbol 14 “symbol” Deprecated.
	// JavaScript (with scope) 15 “javascriptWithScope”
	private static final int INT = Integer.MAX_VALUE; // int
	// Timestamp 17 “timestamp”
	private static final Long LONG = 18L; // long
	// Min key -1 “minKey”
	// Max key 127 “maxKey”
	private static final Enumeration ENUMERATION = Enumeration.ENUM_2;
	private static final String STRING_0 = "string_0";
	private static final String STRING_1 = "string_1";
	private static final String STRING_2 = "string_2";
	private static BasicDocument basicDocument = generateBasicDocument();

	@Autowired
	private BasicRepository repository;

	@Test
	public void test00_save() throws Exception {
		repository.save(basicDocument);
	}

	@Test
	public void test01_findOne() throws Exception {
		testSpringDataQuery();
		testCustomSpringDataQuery();
		testQuery();
	}

	@Test
	public void test02_updateFirst() throws Exception {
		final int NEW_INT = 100;
		Query query = new Query(Criteria.where("_id").is(ID));
		Update update = Update.update("intValue", Integer.valueOf(NEW_INT));
		WriteResult writeResult = repository.updateFirst(query, update);
		int modifiedCount = writeResult.getN();
		Assert.assertEquals(modifiedCount, 1);
		BasicDocument basicDocument = repository.findOne(ID);
		assertEquals(basicDocument, NEW_INT);
	}

	@Test
	public void test03_remove() throws Exception {
		repository.delete(ID);
		BasicDocument returnBasicDocument = repository.findOne(ID);
		Assert.assertNull(returnBasicDocument);
	}

	private void testSpringDataQuery() {
		BasicDocument basicDocument = repository.findOne(ID);
		assertEquals(basicDocument, INT);
	}

	private void testCustomSpringDataQuery() {
		BasicDocument basicDocument = repository.findOneByIntValue(INT);
		assertEquals(basicDocument, INT);
	}

	private void testQuery() {
		List<BasicDocument> basicDocuments = repository.queryByStringValue(STRING);
		Assert.assertTrue(basicDocuments.size() > 0);
		for (BasicDocument basicDocument : basicDocuments) {
			assertEquals(basicDocument, INT);
		}
	}

	private void assertEquals(BasicDocument basicDocument, int i) {
		Assert.assertEquals(String.valueOf(basicDocument.getDoubleValue()), String.valueOf(DOUBLE));
		Assert.assertEquals(basicDocument.getStringValue(), STRING);
		Assert.assertEquals(basicDocument.getArrayValue(), ARRAY);
		assertEquals(basicDocument.getBinDataValue(), BIN_DATA);
		Assert.assertEquals(basicDocument.getObjectIdValue(), OBJECT_ID);
		Assert.assertEquals(basicDocument.isBoolValue(), BOOL);
		Assert.assertEquals(basicDocument.getDateValue(), DATE);
		Assert.assertEquals(basicDocument.getNullValue(), NULL);
		Assert.assertEquals(basicDocument.getIntValue(), i);
		Assert.assertEquals(basicDocument.getLongValue(), LONG);
		Assert.assertEquals(basicDocument.getEnumerationValue(), ENUMERATION);
		assertSubDocumentEquals(basicDocument.getSubDocument());
	}

	private void assertSubDocumentEquals(SubDocument subDocument) {
		Assert.assertEquals(subDocument.getString_0(), STRING_0);
		Assert.assertEquals(subDocument.getString_1(), STRING_1);
		Assert.assertEquals(subDocument.getString_2(), STRING_2);
	}

	private static BasicDocument generateBasicDocument() {
		return new BasicDocument(ID, DOUBLE, STRING, ARRAY, BIN_DATA, OBJECT_ID, BOOL, DATE, NULL, INT, LONG,
				ENUMERATION, STRING_0, STRING_1, STRING_2);
	}

	private static byte[] getBinData() {
		return new byte[] { 0x1, 0x2, 0x3 };
	}

	private void assertEquals(byte[] bytes_0, byte[] bytes_1) {
		int length_0 = bytes_0.length;
		int length_1 = bytes_1.length;
		if (length_0 != length_1) {
			throw new RuntimeException("length_0(" + length_0 + ") not equals to length_1(" + length_1 + ") !!!");
		}
		for (int i = 0; i < length_0; ++i) {
			Assert.assertEquals(bytes_0[i], bytes_1[i]);
		}
	}
}
