package fr.kizafox.aloneguy.main;

import fr.kizafox.aloneguy.game.client.window.Game;

import javax.swing.*;

/**
 * Created at 19/12/2023 at 06:06
 * Made by @KIZAFOX (twitter)
 **/

public class AloneGuy {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::new);
    }
}
