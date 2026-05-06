package com.narxoz.rpg.artifact;

public class GoldAppraiser implements ArtifactVisitor {

    private int totalGold;
    private int itemsAppraised;

    public int getTotalGold() {
        return totalGold;
    }

    public int getItemsAppraised() {
        return itemsAppraised;
    }

    @Override
    public void visit(Weapon weapon) {
        int price = weapon.getValue() + weapon.getAttackBonus() * 5;
        totalGold += price;
        itemsAppraised++;
        System.out.println("  [Gold] Weapon  '" + weapon.getName() + "' -> " + price + "g (atk +" + weapon.getAttackBonus() + ")");
    }

    @Override
    public void visit(Potion potion) {
        int price = potion.getValue() + potion.getHealing() * 2;
        totalGold += price;
        itemsAppraised++;
        System.out.println("  [Gold] Potion  '" + potion.getName() + "' -> " + price + "g (heal " + potion.getHealing() + ")");
    }

    @Override
    public void visit(Scroll scroll) {
        int price = scroll.getValue() * 3;
        totalGold += price;
        itemsAppraised++;
        System.out.println("  [Gold] Scroll  '" + scroll.getName() + "' -> " + price + "g (spell '" + scroll.getSpellName() + "')");
    }

    @Override
    public void visit(Ring ring) {
        int price = ring.getValue() + ring.getMagicBonus() * 12;
        totalGold += price;
        itemsAppraised++;
        System.out.println("  [Gold] Ring    '" + ring.getName() + "' -> " + price + "g (magic +" + ring.getMagicBonus() + ")");
    }

    @Override
    public void visit(Armor armor) {
        int price = armor.getValue() + armor.getDefenseBonus() * 4;
        totalGold += price;
        itemsAppraised++;
        System.out.println("  [Gold] Armor   '" + armor.getName() + "' -> " + price + "g (def +" + armor.getDefenseBonus() + ")");
    }
}
