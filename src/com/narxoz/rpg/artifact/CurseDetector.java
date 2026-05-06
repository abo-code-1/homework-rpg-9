package com.narxoz.rpg.artifact;

import java.util.Locale;

public class CurseDetector implements ArtifactVisitor {

    private int cursedCount;

    public int getCursedCount() {
        return cursedCount;
    }

    private boolean nameLooksCursed(String name) {
        String lower = name.toLowerCase(Locale.ROOT);
        return lower.contains("cursed")
                || lower.contains("hex")
                || lower.contains("doom")
                || lower.contains("shadow");
    }

    @Override
    public void visit(Weapon weapon) {
        if (nameLooksCursed(weapon.getName())) {
            cursedCount++;
            System.out.println("  [Curse] Weapon  '" + weapon.getName() + "' DRIPS with malice. DO NOT WIELD.");
        } else {
            System.out.println("  [Curse] Weapon  '" + weapon.getName() + "' is clean");
        }
    }

    @Override
    public void visit(Potion potion) {
        if (potion.getHealing() < 0) {
            cursedCount++;
            System.out.println("  [Curse] Potion  '" + potion.getName() + "' is POISON disguised as cure");
        } else {
            System.out.println("  [Curse] Potion  '" + potion.getName() + "' tests safe");
        }
    }

    @Override
    public void visit(Scroll scroll) {
        if (nameLooksCursed(scroll.getName()) || nameLooksCursed(scroll.getSpellName())) {
            cursedCount++;
            System.out.println("  [Curse] Scroll  '" + scroll.getName() + "' carries forbidden words ('" + scroll.getSpellName() + "')");
        } else {
            System.out.println("  [Curse] Scroll  '" + scroll.getName() + "' is benign");
        }
    }

    @Override
    public void visit(Ring ring) {
        if (nameLooksCursed(ring.getName()) || ring.getMagicBonus() < 0) {
            cursedCount++;
            System.out.println("  [Curse] Ring    '" + ring.getName() + "' BINDS the wearer to dark fate");
        } else {
            System.out.println("  [Curse] Ring    '" + ring.getName() + "' is unbound");
        }
    }

    @Override
    public void visit(Armor armor) {
        if (nameLooksCursed(armor.getName())) {
            cursedCount++;
            System.out.println("  [Curse] Armor   '" + armor.getName() + "' carries a HEX in its plating");
        } else {
            System.out.println("  [Curse] Armor   '" + armor.getName() + "' is uncursed");
        }
    }
}
