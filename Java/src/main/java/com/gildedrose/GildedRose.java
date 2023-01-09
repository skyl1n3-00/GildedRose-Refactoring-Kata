package com.gildedrose;

import static com.gildedrose.GildedRose.Name.AGED_BRIE;

class GildedRose {

    public static final int MAX_QUALITY = 50;
    Item[] items;

    public enum Name {
        AGED_BRIE ("Aged Brie"),
        PASSES ("Backstage passes to a TAFKAL80ETC concert"),
        SULFURAS ("Sulfuras, Hand of Ragnaros"),
        DEFAULT ("DEFAULT");

        public final String name;

        Name(String name) {
            this.name = name;
        }
        public final String getName () {
            return name ;
        }

    }

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
        if (Name.AGED_BRIE.getName().equals(item.name)) {
            incrementQuality(item, 1);
            if (item.sellIn < 0) {
                incrementQuality(item, 1);
            }
        } else if (Name.PASSES.getName().equals(item.name)) {
            if (item.sellIn < 0) {
                item.quality = 0;
            } else if (item.sellIn < 5) {
                incrementQuality(item, 3);
            } else if (item.sellIn < 10) {
                incrementQuality(item, 2);
            } else {
                incrementQuality(item, 1);
            }
        } else if (Name.SULFURAS.getName().equals(item.name)) {
            item.sellIn = item.sellIn + 1;
            // keep it atm
        } else {
            decrementQuality(item);
            if (item.sellIn < 0) {
                decrementQuality(item);
            }
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
