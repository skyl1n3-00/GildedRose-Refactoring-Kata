package com.gildedrose;

class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
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
        switch (item.name) {
            case AGED_BRIE:
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
                break;
            case PASSES:
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
                break;
            case SULFURAS:
                break;
            default:
                if (item.quality > 0) {
                    item.quality = item.quality - 1;
                }
                break;
        }
        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }
        switch (item.name) {
            case AGED_BRIE:
                if (item.sellIn < 0) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
                break;
            case PASSES:
                if (item.sellIn < 0) {
                    item.quality = 0;

                }
                break;
            case SULFURAS:
                // keep it atm
                break;
            default:
                if (item.sellIn < 0) {
                    if (item.quality > 0) {
                        item.quality = item.quality - 1;
                    }
                }
                break;
        }
    }
}
