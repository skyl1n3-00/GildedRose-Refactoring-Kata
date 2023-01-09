package com.gildedrose;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class GildedRose {

  public static final int MAX_QUALITY = 50;
  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  private static void decrementQuality(Item item) {
    if (item.quality > 0) {
      item.quality = item.quality - 1;
    }
  }

  private static Name getStrategy(Item item) {
    Map<String, Name> strategyForName =
        Arrays.stream(Name.values()).collect(Collectors.toMap(Name::getName, Function.identity()));
    return strategyForName.getOrDefault(item.name, Name.DEFAULT);
  }

  public void updateQuality() {
    for (Item item : items) {
      updateQualityForItem(item);
    }
  }

  private void updateQualityForItem(Item item) {
    item.sellIn = item.sellIn - 1;
    Name strategy = getStrategy(item);
    strategy.updateQuality(item);
  }

  public enum Name {
    AGED_BRIE("Aged Brie") {
      public void updateQuality(Item item) {
        incrementQuality(item, 1);
        if (item.sellIn < 0) {
          incrementQuality(item, 1);
        }
      }
    },
    PASSES("Backstage passes to a TAFKAL80ETC concert") {
      public void updateQuality(Item item) {
        if (item.sellIn < 0) {
          item.quality = 0;
        } else if (item.sellIn < 5) {
          incrementQuality(item, 3);
        } else if (item.sellIn < 10) {
          incrementQuality(item, 2);
        } else {
          incrementQuality(item, 1);
        }
      }
    },
    SULFURAS("Sulfuras, Hand of Ragnaros") {
      public void updateQuality(Item item) {
        item.sellIn = item.sellIn + 1;
      }
    },
    DEFAULT("Default") {
      public void updateQuality(Item item) {
        decrementQuality(item);
        if (item.sellIn < 0) {
          decrementQuality(item);
        }
      }
    };

    public final String qualityName;

    Name(String name) {
      this.qualityName = name;
    }

    public final String getName() {
      return qualityName;
    }

    public abstract void updateQuality(Item item);

    protected void incrementQuality(Item item, int incrementValue) {
      item.quality = Math.min(item.quality + incrementValue, MAX_QUALITY);
    }
  }
}
