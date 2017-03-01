package com.ignacio.validator.emailvalidator;

/**
* Simple application for Software Engineering Assignment 3.
* Provides logic for validating an email address.
*
* @author  Jonathan Ignacio
*/
public class Validate {
	
	static String[] domains = {"hotmail.com", "mail.com", "gmail.com"};
	/**
	* 
	* Method for testing if an email address is valid based on available rules. 
	* @param email - an email to test validity.
	* @return number of passing rules.
	* @author  Jonathan Ignacio
	*/
	public static int validate(String email){
		int rules = 0;
		if(validateDot(email))
			rules++;
		if(validateAt(email)){
			rules++;
			if(validateEmailDomain(email, domains)) //have already verfied that it contains a single '@'character
				rules++;
		}
		if(validateEmailLength(email))
			rules++;

		return rules;
		
	}
	
	/**
	 * Validates that an email address only has a single '@' character.
	 * 
	 * @param email - an email to check.
	 * @return True if there is a single '@' character.
	 */
	public static boolean validateAt(String email){
		char[] emailTest = email.toCharArray();
		int atNum = 0;
		for(int i=0; i < emailTest.length; i++){
			if(emailTest[i] == '@')
				atNum++;
			}
		if(atNum == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * Validates that an email address only has a single '@' character.
	 * 
	 * @param email - an email to check.
	 * @return True if the email contains one or more '.' characters.
	 */
	public static boolean validateDot(String email){
		if(email.contains("."))	
			return true;
		else
			return false;
	}
	
	/**
	 * Validates that an email address only has a single '@' character.
	 * 
	 * @param email - an email to check.
	 * @param domains - list of valid domain.
	 * @return True if the email's domain is contained within the list of valid domains.
	 */
	public static boolean validateEmailDomain(String email, String[] domains){
		boolean hasDomain = false;
		char[] check = email.toCharArray();
		for(int i = 0; i < check.length; i++)
			if(check[i] == '@'){
				if(i + 1 < check.length)
					hasDomain = true; //considers a domain any character that occurs after an '@' character
			}
		if(hasDomain){
			String[] parts = email.split("@");
			String domain = parts[1];
			for(String test : domains){
				if(test.equals(domain)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Validates that an email address has less than 50 characters.
	 * @param email - an email to check.
	 * @return True if the email's character count is less than 50 altogether
	 */
	public static boolean validateEmailLength(String email) {
		return email.length() <= 50;
	}
}
