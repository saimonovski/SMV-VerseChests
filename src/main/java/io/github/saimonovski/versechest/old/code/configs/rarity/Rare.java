package io.github.saimonovski.versechest.old.code.configs.rarity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rare  implements RarityConfigurer{

    private int chance;

    private int maxItems;

    private int minItems;



}
