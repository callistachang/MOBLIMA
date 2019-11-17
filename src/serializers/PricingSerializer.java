package serializers;

import managers.Formatter;
import models.ISerializable;
import models.Pricing;

public class PricingSerializer extends AbstractSerializer {
	
	@Override
	protected ISerializable deserialize(String data) {
		String[] d = splitByAttribute(data);
		double basePrice = Formatter.getDoubleFromString(d[0]);
		double weekendPremium = Formatter.getDoubleFromString(d[1]);
		double studentDiscount = Formatter.getDoubleFromString(d[2]);
		double seniorCitizenDiscount = Formatter.getDoubleFromString(d[3]);
		double movieTypePremium = Formatter.getDoubleFromString(d[4]);
		double platinumCinemaPremium = Formatter.getDoubleFromString(d[5]);
		double goldCinemaPremium = Formatter.getDoubleFromString(d[6]);

		return new Pricing(basePrice, weekendPremium, studentDiscount, seniorCitizenDiscount, movieTypePremium, platinumCinemaPremium, goldCinemaPremium);

	}
}
