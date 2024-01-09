package fr.kizafox.aloneguy.game.entity.enemy;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.Entity;
import fr.kizafox.aloneguy.game.entity.enemy.zombie.Zombie;
import fr.kizafox.aloneguy.game.entity.player.Player;

import java.awt.*;

public class EnemyManager {

    protected final Game game;

    private final EnemyHandler[] enemies;

    public EnemyManager(final Game game) {
        this.game = game;

        this.enemies = new EnemyHandler[100];
        this.enemies[0] = new Zombie(this.game);
    }

    public void update(){
        for(final EnemyHandler enemy : this.enemies) {
            if (enemy != null) {
                enemy.update();
            }
        }
    }

    public void render(Graphics graphics) {
        for(final EnemyHandler enemy : this.enemies) {
            if (enemy != null) {
                enemy.render(graphics);
            }
        }
    }

    public void attackPlayer(final int index, final Entity entity){
        if(index != 999){
            if(this.enemies[index].getHealth() == 0){
                entity.gainExperience(150);
                this.enemies[index] = null;
                return;
            }

            this.enemies[0].onEnemyTakeDamage();
            this.enemies[0].onPlayerTakeDamage();

            Game.log("Player attacked by " + this.enemies[index].getEnemyType() + " at index " + index + ". (Damage: " + entity.getDamage() + ")");
            Game.log(this.enemies[index].getEnemyType() + " attack by Player. (Damage: " + entity.getDamage() + ")");
        }
    }

    public EnemyHandler[] getEnemies() {
        return enemies;
    }
}
