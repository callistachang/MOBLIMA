package serializers;

import models.Account;
import models.ISerializable;

public class AccountSerializer extends AbstractSerializer {

	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		int age = parseInteger(d, 1);
		
		return new Account(d[0], age, d[2], d[3], d[4], null);
	}

}

