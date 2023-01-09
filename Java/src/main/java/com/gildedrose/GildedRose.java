package com.gildedrose;

class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAX_QUALITY = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQualityForItem(item);
        }
    }

    private void updateQualityForItem(Item item) {
        item.sellIn = item.sellIn - 1;
        switch (item.name) {
            case AGED_BRIE:
                incrementQuality(item, 1);
                if (item.sellIn < 0) {
                    incrementQuality(item, 1);
                }
                break;
            case PASSES:
                if (item.sellIn < 0) {
                    item.quality = 0;
                } else if (item.sellIn < 5) {
                    incrementQuality(item, 3);
                } else if (item.sellIn < 10) {
                    incrementQuality(item, 2);
                } else {
                    incrementQuality(item, 1);
                }
                break;
            case SULFURAS:
                item.sellIn = item.sellIn + 1;
                // keep it atm
                break;
            default:
                decrementQuality(item);
                if (item.sellIn < 0) {
                    decrementQuality(item);
                }
                break;
        }
    }

    private void incrementQuality(Item item, int incrementValue) {
        item.quality = Math.min(item.quality + incrementValue, MAX_QUALITY);
    }

    private static void decrementQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }
}
