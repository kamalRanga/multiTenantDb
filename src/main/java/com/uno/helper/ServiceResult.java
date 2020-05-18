package com.uno.helper;

import java.util.ArrayList;
import java.util.List;


public class ServiceResult {

	/**
	 * Variable to store status code.
	 */
	private int code;
	/**
	 * List containing the {@link ResultMessageObject}
	 */
	private List<ServiceMessage> resultmsg;
	/**
	 * Object containing service response object.
	 */
	private Object result;

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 
	 * @param code
	 * @param resultmsg
	 * @param result
	 */
	public ServiceResult(StatusCode code, List<ServiceMessage> resultmsg, Object result) {
		this.code = code.statusCode;
		this.resultmsg = resultmsg;
		this.result = result;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 
	 * @param code
	 * @param resultmsg
	 * @param result
	 */
	public ServiceResult(StatusCode code, ServiceMessage resultmsg, Object result) {
		this.code = code.statusCode;
		this.resultmsg = new ArrayList<ServiceMessage>();
		this.resultmsg.add(resultmsg);
		this.result = result;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 
	 * @param code
	 * @param result
	 */
	public ServiceResult(StatusCode code, Object result) {
		this.code = code.statusCode;
		this.result = result;
	}

	// ------------------------------------------------------------------------------------------------------------------
	public int getCode() {
		return code;
	}

	public List<ServiceMessage> getResultmsg() {
		return resultmsg;
	}

	public Object getResult() {
		return result;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Enumeration for Status Codes.
	 * 
	 * @author Kamal
	 *
	 */
	public enum StatusCode {

		SUCCESS(200), INCORRECT_PASSWORD(203), ORG_NOT_FOUND(204), INTERNAL_SERVER_ERROR(500), WARNING(501),
		BAD_REQUEST(400), UNAUTHORIZED(401), EmailIdExist(205), EXCEPTION(666), RESOURCE_NOT_FOUND(404), NoDetails(300), INVALID_DATA(402);

		private int statusCode;

		private StatusCode(int code) {
			this.statusCode = code;
		}

		public int getStatusCode() {
			return statusCode;
		}
	}

	public enum UserReturnStatus {

		EmailIdEXIST(1), OrgNotFound(2), IncorrectPassword(3), SUCCESS(4);

		private int statusCode;

		private UserReturnStatus(int code) {
			this.statusCode = code;
		}

		public int getStatusCode() {
			return statusCode;
		}
	}

	public enum ContactReturnStatus {

		EmailIdEXIST(1), OrgNotFound(2), IncorrectPassword(3), SUCCESS(4), EXCEPTION(5), isNotExist(6), isExist(7);

		private int statusCode;

		private ContactReturnStatus(int code) {
			this.statusCode = code;
		}

		public int getStatusCode() {
			return statusCode;
		}
	}

	public enum ResponseStatus {

		EmailIdEXIST(1), OrgNotFound(2), IncorrectPassword(3), SUCCESS(4), Fail(5);

		private int statusCode;

		private ResponseStatus(int code) {
			this.statusCode = code;
		}

		public int getStatusCode() {
			return statusCode;
		}
	}

	

	

}

