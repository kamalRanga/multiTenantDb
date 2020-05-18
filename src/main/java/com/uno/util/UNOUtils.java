package com.uno.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.uno.helper.ServiceMessage;

/**
 * This class contains the common properties for Application.
 * 
 * @author Kamal
 *
 */
public class UNOUtils {

	/**
	 * Instance of Logger {@link Logger}
	 */
	private static final Logger logger = LogManager.getLogger(UNOUtils.class);

	/**
	 * This method is used for generating the password salt.
	 * 
	 * @return {@link String}
	 * @throws NoSuchAlgorithmException
	 */
	public static String generatePasswordSalt() throws NoSuchAlgorithmException {
		int length = 48;
		byte[] bytes = new byte[length];
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.nextBytes(bytes);
		return Base64.getEncoder().encodeToString(bytes);
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Method to get lit of {@link ServiceMessage} from List of {@link ObjectError}
	 * 
	 * @param resultErrors
	 * @return
	 */
	public static List<ServiceMessage> getServiceMessage(MessageSource messageSource, List<ObjectError> resultErrors) {

		List<ServiceMessage> resultlist = new ArrayList<ServiceMessage>();
		for (ObjectError obj : resultErrors) {
			ServiceMessage code = null;
			if (obj instanceof FieldError) {
				FieldError fieldError = (FieldError) obj;
				code = new ServiceMessage(messageSource, obj.getCode(), obj.getArguments(), fieldError.getField(),
						obj.getObjectName(), fieldError.getRejectedValue(), fieldError.isBindingFailure());
			} else {
				code = new ServiceMessage(messageSource, obj.getCode(), obj.getArguments(), null, obj.getObjectName(),
						null, false);
			}

			resultlist.add(code);
		}

		return resultlist;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * This method is used for generating the random token with given length from
	 * given characters.
	 * 
	 * @param random
	 *            {@link Random}
	 * @param strName
	 *            {@link String}
	 * @param length
	 *            {@link Integer}
	 * @return String
	 */
	public static String generateRandomString(Random random, String strName, int length) {
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = strName.charAt(random.nextInt(strName.length()));
		}
		return new String(text);
	}


	// ------------------------------------------------------------------------------------------------------------
	/**
	 * This is used for converting date into milli timestamp;
	 * 
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static long getCurrentMilliTimestamp() throws ParseException {
		java.util.Date today = new java.util.Date();
		return today.getTime();
	}

	
}
