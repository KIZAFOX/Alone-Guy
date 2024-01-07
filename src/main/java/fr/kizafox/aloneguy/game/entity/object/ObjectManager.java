package fr.kizafox.aloneguy.game.entity.object;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.object.item.potion.HealPotion;
import fr.kizafox.aloneguy.game.entity.object.item.trap.Landmine;
import fr.kizafox.aloneguy.game.utils.Colors;

import java.awt.*;

public class ObjectManager {

    protected final Game game;

    private final ObjectHandler[] objectHandler;

    public ObjectManager(final Game game) {
        this.game = game;

        this.objectHandler = new ObjectHandler[10];
        this.objectHandler[0] = new HealPotion(this.game);
        this.objectHandler[1] = new Landmine(this.game);
    }

    public void render(Graphics graphics) {
        for(final ObjectHandler handler : this.objectHandler) {
            if (handler != null) {
                handler.render(graphics);
            }
        }
    }

    public void pickUp(final int index, final Entity entity){
        if(index != 999){
            final String objectName = this.objectHandler[index].getName();

            switch (objectName){
                case "HealPotion":
                    this.objectHandler[0].applyEffect(entity);
                    this.objectHandler[index] = null;

                    Game.log(Colors.YELLOW + entity.getEntityType() + " picked up a HealPotion.");
                    break;
                case "Landmine":
                    this.objectHandler[1].applyEffect(entity);
                    this.objectHandler[index] = null;

                    Game.log(Colors.YELLOW + entity.getEntityType() + " picked up a Landmine.");
                    break;
            }
        }
    }

    public ObjectHandler[] getObjectHandler() {
        return objectHandler;
    }
}
