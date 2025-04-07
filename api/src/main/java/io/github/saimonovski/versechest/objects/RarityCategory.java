package io.github.saimonovski.versechest.objects;

import java.util.List;

public interface RarityCategory {
    String name();
    List<DropItem> items();
}
