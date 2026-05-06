package com.narxoz.rpg.artifact;

public class EnchantmentScanner implements ArtifactVisitor {

    private int magicalItems;

    public int getMagicalItems() {
        return magicalItems;
    }

    @Override
    public void visit(Weapon weapon) {
        if (weapon.getAttackBonus() >= 5) {
            magicalItems++;
            System.out.println("  [Scan] Weapon  '" + weapon.getName() + "' hums with battle-magic (atk +" + weapon.getAttackBonus() + ")");
        } else {
            System.out.println("  [Scan] Weapon  '" + weapon.getName() + "' is mundane steel");
        }
    }

    @Override
    public void visit(Potion potion) {
        System.out.println("  [Scan] Potion  '" + potion.getName() + "' alchemy resonates (heals " + potion.getHealing() + ")");
    }

    @Override
    public void visit(Scroll scroll) {
        magicalItems++;
        System.out.println("  [Scan] Scroll  '" + scroll.getName() + "' sealed with the rune of '" + scroll.getSpellName() + "'");
    }

    @Override
    public void visit(Ring ring) {
        if (ring.getMagicBonus() > 0) {
            magicalItems++;
            System.out.println("  [Scan] Ring    '" + ring.getName() + "' radiates arcane light (magic +" + ring.getMagicBonus() + ")");
        } else {
            System.out.println("  [Scan] Ring    '" + ring.getName() + "' is dull and inert");
        }
    }

    @Override
    public void visit(Armor armor) {
        if (armor.getDefenseBonus() >= 6) {
            magicalItems++;
            System.out.println("  [Scan] Armor   '" + armor.getName() + "' is warded against blows (def +" + armor.getDefenseBonus() + ")");
        } else {
            System.out.println("  [Scan] Armor   '" + armor.getName() + "' is plain plating");
        }
    }
}
