package dto;

import java.io.Serializable;
import java.util.List;

public class MarketList implements Serializable {
          private List<MarketPlayer> marketList;

    public void setMarketList(List<MarketPlayer> marketList) {
        this.marketList = marketList;
    }

    public List<MarketPlayer> getMarketList() {
        return marketList;
    }
}
