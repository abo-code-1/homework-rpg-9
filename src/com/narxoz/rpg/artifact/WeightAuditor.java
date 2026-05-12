package com.narxoz.rpg.artifact;

/**
 * Open/closed proof visitor: added after the original three without touching
 * any existing artifact class, the visitor interface, or the other visitors.
 *
 * Tallies total carry weight and flags the single heaviest item.
 */
public class WeightAuditor implements ArtifactVisitor {

    private int totalWeight;
    private int itemsCounted;
    private String heaviestName = "(none)";
    private int heaviestWeight = -1;

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getItemsCounted() {
        return itemsCounted;
    }

    public String getHeaviestName() {
        return heaviestName;
    }

    public int getHeaviestWeight() {
        return heaviestWeight;
    }

    private void record(String label, String name, int weight) {
        totalWeight += weight;
        itemsCounted++;
        if (weight > heaviestWeight) {
            heaviestWeight = weight;
            heaviestName = name;
        }
        System.out.println("  [Weigh] " + label + " '" + name + "' -> " + weight + "kg");
    }

    @Override
    public void visit(Weapon weapon) {
        record("Weapon ", weapon.getName(), weapon.getWeight());
    }

    @Override
    public void visit(Potion potion) {
        record("Potion ", potion.getName(), potion.getWeight());
    }

    @Override
    public void visit(Scroll scroll) {
        record("Scroll ", scroll.getName(), scroll.getWeight());
    }

    @Override
    public void visit(Ring ring) {
        record("Ring   ", ring.getName(), ring.getWeight());
    }

    @Override
    public void visit(Armor armor) {
        record("Armor  ", armor.getName(), armor.getWeight());
    }
}
