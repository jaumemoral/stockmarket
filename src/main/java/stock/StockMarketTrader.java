package stock;

import java.util.List;

import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StockMarketTrader {

	static final int TICKS_AFTER_BUYING=2;

	List<Float> prices;

	StockMarketTrader(String pricesString) {
		prices=Stream.of(pricesString.split(" "))
			.map(Float::parseFloat)
			.collect(Collectors.toList());
	}


	public String bestTrade() {
		float bestBuyPrice=0, bestSellPrice=0;
		for (int buyTick=0; buyTick<prices.size()-TICKS_AFTER_BUYING; buyTick++) {
			for (int sellTick=buyTick+TICKS_AFTER_BUYING; sellTick<prices.size(); sellTick++) {
				float buyPrice = prices.get(buyTick);
				float sellPrice = prices.get(sellTick);
				if (sellPrice-buyPrice > bestSellPrice-bestBuyPrice) {
					bestBuyPrice=buyPrice;
					bestSellPrice=sellPrice;
				}
			}
		}
		return String.format(Locale.ROOT,"%.2f %.2f",bestBuyPrice,bestSellPrice);
	}


}
