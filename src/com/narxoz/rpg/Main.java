package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import java.util.List;

/**
 * Entry point for Homework 9 — Chronomancer's Vault: Visitor + Memento.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        Hero tanya = new Hero("Tanya the Bold", 80, 18, 6);
        Hero kael = new Hero("Kael Stormborn", 60, 22, 4);

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(List.of(tanya, kael));

        System.out.println();
        System.out.println("=== Final Vault Summary ===");
        System.out.println(result);
    }
}
