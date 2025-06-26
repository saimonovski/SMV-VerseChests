package io.github.saimonovski.versechests.object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Category {
    private final Random random = new Random();
    private final String categoryId;
    private final int minItemsInChest, maxItemsInCHest;
    private final double chanceToDrop;

    private final List<ItemInChest> items = new ArrayList<>();

    public Category(String categoryId, int minItemsInChest, int maxItemsInCHest, double chanceToDrop) {
        this.categoryId = categoryId;
        this.minItemsInChest = minItemsInChest;
        this.maxItemsInCHest = maxItemsInCHest;
        this.chanceToDrop = chanceToDrop;
    }

    public List<ItemInChest> getRandomizedItems(){
        List<ItemInChest> newItems = new ArrayList<>();
        int itemCount = Math.abs(random.nextInt(maxItemsInCHest));
       final int count = Math.max(itemCount, minItemsInChest);

        while (newItems.size() != count){
            Collections.shuffle(items);
            newItems.addAll(
            items.stream().filter(item -> items.size() != count && Math.abs(random.nextInt(100)) <= chanceToDrop).collect(Collectors.toList()));
        }
    return newItems;
    }
    public void addItem(ItemInChest item){
        this.items.add(item);
    }
    public void clear(){
        this.items.clear();
    }
    public List<ItemInChest> items(){
        return new ArrayList<>(items);
    }
}
