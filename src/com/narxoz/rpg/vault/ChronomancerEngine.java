package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.CurseDetector;
import com.narxoz.rpg.artifact.EnchantmentScanner;
import com.narxoz.rpg.artifact.GoldAppraiser;
import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import java.util.List;

/**
 * Orchestrates the Chronomancer's Vault demo run.
 *
 * For each hero in the party the engine:
 *   1. builds a mixed vault inventory,
 *   2. appraises / scans / curse-checks it through visitors,
 *   3. snapshots the hero,
 *   4. springs a trap that mauls the hero,
 *   5. rewinds the hero from the snapshot.
 */
public class ChronomancerEngine {

    /**
     * Runs the vault sequence for the supplied party.
     *
     * @param party the heroes entering the vault
     * @return the aggregate vault summary
     */
    public VaultRunResult runVault(List<Hero> party) {
        if (party == null || party.isEmpty()) {
            return new VaultRunResult(0, 0, 0);
        }

        Inventory vaultLoot = buildVaultLoot();
        Caretaker caretaker = new Caretaker();

        int artifactsAppraised = 0;
        int mementosCreated = 0;
        int restoredCount = 0;

        for (Hero hero : party) {
            System.out.println();
            System.out.println("--- Vault run for " + hero.getName() + " ---");
            System.out.println("State on entry: " + hero);

            System.out.println("[Appraisal] cataloguing vault loot...");
            GoldAppraiser appraiser = new GoldAppraiser();
            vaultLoot.accept(appraiser);
            artifactsAppraised += appraiser.getItemsAppraised();
            System.out.println("[Appraisal] complete: " + appraiser.getTotalGold()
                    + "g across " + appraiser.getItemsAppraised() + " items");

            System.out.println("[Scanner] checking for enchantments...");
            EnchantmentScanner scanner = new EnchantmentScanner();
            vaultLoot.accept(scanner);
            System.out.println("[Scanner] complete: " + scanner.getMagicalItems() + " magical items");

            System.out.println("[CurseCheck] inspecting for curses...");
            CurseDetector curseDetector = new CurseDetector();
            vaultLoot.accept(curseDetector);
            System.out.println("[CurseCheck] complete: " + curseDetector.getCursedCount() + " cursed items");

            hero.setInventory(vaultLoot.copy());
            hero.addGold(appraiser.getTotalGold() / 4);

            System.out.println(">> [Memento] taking snapshot of " + hero.getName() + " before trap");
            HeroMemento snapshot = hero.createMemento();
            caretaker.save(snapshot);
            mementosCreated++;
            System.out.println(">> [Memento] saved (history size = " + caretaker.size() + ")");

            System.out.println("!! [Trap] glyphs flare — the hero takes 9999 damage and loses all gold");
            hero.takeDamage(9999);
            hero.spendGold(hero.getGold());
            System.out.println("!! [Trap] post-trap state: " + hero);

            System.out.println("<< [Rewind] Chronomancer reverses time...");
            HeroMemento restored = caretaker.undo();
            hero.restoreFromMemento(restored);
            if (restored != null) {
                restoredCount++;
            }
            System.out.println("<< [Rewind] post-rewind state: " + hero);
        }

        return new VaultRunResult(artifactsAppraised, mementosCreated, restoredCount);
    }

    private Inventory buildVaultLoot() {
        Inventory loot = new Inventory();
        loot.addArtifact(new Weapon("Sunsteel Blade", 120, 8, 7));
        loot.addArtifact(new Weapon("Cursed Reaver", 90, 9, 6));
        loot.addArtifact(new Potion("Greater Healing", 40, 1, 30));
        loot.addArtifact(new Scroll("Shadow Pact", 60, 1, "Doombind"));
        loot.addArtifact(new Ring("Ring of Arcana", 200, 1, 4));
        loot.addArtifact(new Armor("Hexed Plate", 150, 18, 5));
        return loot;
    }
}
