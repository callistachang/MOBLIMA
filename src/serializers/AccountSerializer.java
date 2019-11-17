package serializers;

import managers.Formatter;
import models.Account;
import models.ISerializable;

/**
 * Handles the deserialization for Account class.
 * @version 1.0
 * @since 2019-11-17
 * @author balad
 *
 */
public class AccountSerializer extends AbstractSerializer {

	/**
	 * Converts data of string type to Account type.
	 */
	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		int age = Formatter.getIntFromString(d[1]);
		
		return new Account(d[0], age, d[2], d[3], d[4], null);
	}

}

