package fr.kizafox.aloneguy.game.entity.item;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.item.potion.HealPotion;
import fr.kizafox.aloneguy.game.entity.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager {

    protected final Game game;
    protected final Player player;

    protected final HealPotion healPotion;

    protected final Map<Item, Integer> items;

    public ItemManager(final Game game, final Player player) {
        this.game = game;
        this.player = player;

        this.healPotion = new HealPotion(this.player, 980, 160);

        this.items = new HashMap<>();
        this.items.put(this.healPotion, 0);
    }

    public void update(){
        final List<Item> itemsToRemove = new ArrayList<>();

        for(final Map.Entry<Item, Integer> entry : this.items.entrySet()){
            final Item item = entry.getKey();

            item.update();
            if(!item.isAvailable()) itemsToRemove.add(item);
        }

        itemsToRemove.forEach(this.items::remove);
    }

    public void render(final Graphics graphics){
        this.items.keySet().forEach(item -> item.render(graphics));
    }

    public void reset(){
        this.items.keySet().forEach(Item::reset);
    }

    public int getItemsCount() {
        int aliveCount = 0;

        for (Map.Entry<Item, Integer> entry : this.items.entrySet()) {
            if (entry.getKey().isAvailable()) aliveCount++;
        }

        return aliveCount;
    }

    public Game getGame() {
        return game;
    }

    public HealPotion getHealPotion() {
        return healPotion;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }
}
