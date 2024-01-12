package fr.kizafox.aloneguy.game.entity.enemy.zombie;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.entity.enemy.EnemyHandler;
import fr.kizafox.aloneguy.game.entity.player.Player;
import fr.kizafox.aloneguy.game.utils.ImageRenderer;

public class Zombie extends EnemyHandler {

    public Zombie(Game game) {
        super(game, EnemyType.ZOMBIE, ImageRenderer.load(ImageRenderer.PLAYER_SHEET), 50, 20, 0, 0.5F, 14, 10);

        this.loadAnimations();
    }

    @Override
    public void update() {
        if(this.isDead()){
            this.game.getPlayMenu().getPlayer().gainExperience(this.getExperienceGain());
            this.game.getPlayMenu().getEnemyManager().removeEnemy(this.game.getPlayMenu().getEnemyManager().getEnemies().indexOf(this));
        }

        this.updatePosition();
    }

    @Override
    public void enemyAttack(Player player) {
        player.applyDamage(0);
    }
}
