package serializers;

import models.Account;
import models.ISerializable;
import models.Review;

public class ReviewSerializer extends AbstractSerializer {

	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		int id = parseInteger(d, 0);
		int rating = parseInteger(d, 1);
		Account account = null;
		
		return new Review(id, rating, account, d[3]);
	}
}
