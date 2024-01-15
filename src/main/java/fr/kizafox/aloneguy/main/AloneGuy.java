package fr.kizafox.aloneguy.main;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.utils.github.GitHubAPI;

import javax.swing.*;

import static fr.kizafox.aloneguy.game.utils.Colors.*;
import static fr.kizafox.aloneguy.game.utils.GameSettings.*;

/**
 * Created at 19/12/2023 at 06:06
 * Made by @KIZAFOX (twitter)
 **/

public class AloneGuy {

    public static void main(String[] args) {
        GitHubAPI.connect();
        System.out.println();
        System.out.println(RED_BRIGHT +
                "   ___   __              _____         \n" +
                "  / _ | / /__  ___  ___ / ___/_ ____ __\n" +
                " / __ |/ / _ \\/ _ \\/ -_) (_ / // / // /\n" +
                "/_/ |_/_/\\___/_//_/\\__/\\___/\\_,_/\\_, /\n" +
                "                                 /___/ \n"
        );

        System.out.println(BLUE + "@Author: " + GREEN_BOLD + AUTHORS[0]);
        System.out.println(BLUE + "@Description: " + GREEN_BOLD + DESCRIPTION);
        System.out.println();
        System.out.println(BLUE + NAME + " (" + GREEN_BOLD + "v" + VERSION + BLUE + ")     Repository: " + RED_BOLD + URL);
        System.out.println();

        SwingUtilities.invokeLater(Game::new);
    }
}
