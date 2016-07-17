package stock;

import java.util.List;

import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StockMarketTrader {

	List<Float> prices;

	StockMarketTrader(String pricesString) {
		prices=Stream.of(pricesString.split(" "))
			.map(Float::parseFloat)
			.collect(Collectors.toList());
	}

	public String bestTrades() {
		int i=0;
		int indexMin=0;
		int indexMax=2; // one tick after
		float min=0;
		float max=0;
		for (float price: prices) {
			min=prices.get(indexMin);
			max=prices.get(indexMax);
			if (price<min) indexMin=i;
			if ((price>max) && (indexMin+1<i)) indexMax=i;
			i++;
		}
		return String.format(Locale.ROOT,"%.2f %.2f",min,max);
	}

}
