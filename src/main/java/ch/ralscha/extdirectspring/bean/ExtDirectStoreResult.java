/**
 * Copyright 2010-2013 Ralph Schaer <ralphschaer@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.ralscha.extdirectspring.bean;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Class representing the result of STORE_READ and STORE_MODIFY methods.
 * 
 * @param <T> Type of the entry inside the collection
 */
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(value = { "metaData", "success", "total", "records", "message" })
public class ExtDirectStoreResult<T> extends JsonViewHint {

	private Long total;

	private Collection<T> records;

	private Boolean success;

	private MetaData metaData;

	private String message;

	public ExtDirectStoreResult() {
		// default constructor
	}

	@SuppressWarnings("unchecked")
	public ExtDirectStoreResult(T record) {
		this((Long) null, Arrays.asList(record), true, null);
	}

	public ExtDirectStoreResult(T[] record) {
		this((Long) null, Arrays.asList(record), true, null);
	}

	public ExtDirectStoreResult(Collection<T> records) {
		this((Long) null, records, true, null);
	}

	public ExtDirectStoreResult(Integer total, Collection<T> records) {
		this(total, records, true);
	}

	public ExtDirectStoreResult(Integer total, Collection<T> records, Boolean success) {
		this(total != null ? Long.valueOf(total) : null, records, success, null);
	}

	public ExtDirectStoreResult(Long total, Collection<T> records) {
		this(total, records, true, null);
	}

	public ExtDirectStoreResult(Long total, Collection<T> records, Class<?> jsonView) {
		this(total, records, true, jsonView);
	}

	public ExtDirectStoreResult(Long total, Collection<T> records, Boolean success, Class<?> jsonView) {
		this.total = total;
		this.records = records;
		this.success = success;
		setJsonView(jsonView);
	}

	public Long getTotal() {
		return total;
	}

	public Collection<T> getRecords() {
		return records;
	}

	public Boolean isSuccess() {
		return success;
	}

	public ExtDirectStoreResult<T> setTotal(Long total) {
		this.total = total;
		return this;
	}

	public ExtDirectStoreResult<T> setRecords(Collection<T> records) {
		this.records = records;
		return this;
	}

	public ExtDirectStoreResult<T> setSuccess(Boolean success) {
		this.success = success;
		return this;
	}

	public Map<String, Object> getMetaData() {
		if (metaData != null) {
			return metaData.getMetaData();
		}
		return null;
	}

	public ExtDirectStoreResult<T> setMetaData(MetaData metaData) {
		this.metaData = metaData;
		return this;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Beware, for this message to be accessible in your callback operation, <br>
	 * you <strong>must</strong> add in your model definition the
	 * messageProperty value to message
	 * {@link ch.ralscha.extdirectspring.generator.Model#messageProperty()}
	 * 
	 * @param message the message to set
	 * @return
	 */
	public ExtDirectStoreResult<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	@Override
	public String toString() {
		return "ExtDirectStoreResult [total=" + total + ", records=" + records + ", success=" + success + ", metaData="
				+ metaData + ", message=" + message + "]";
	}

}
