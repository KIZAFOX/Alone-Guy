package fr.kizafox.aloneguy.game.utils.github;

import fr.kizafox.aloneguy.game.client.window.Game;
import fr.kizafox.aloneguy.game.utils.Colors;
import org.kohsuke.github.*;

import java.io.IOException;

/**
 * Done by @KIZAFOX on {14/01/2024} at 16:17.
 **/
public class GitHubAPI {

    protected static GitHub gitHub;
    protected static GHRepository repository;
    protected static PagedIterable<GHCommit> commits;
    protected static GHCommit commit;

    public static void connect() {
        try {
            gitHub = new GitHubBuilder().withOAuthToken("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").build();
            repository = gitHub.getRepository("KIZAFOX/AloneGuy");
            commits = repository.listCommits();
            commit = commits.iterator().next();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        Game.log(Colors.PURPLE + "Connected to GitHub API. (repo_id: " + repository.getId() + ")");
    }

    public static GitHub getGitHub() {
        return gitHub;
    }

    public static GHRepository getRepository() {
        return repository;
    }

    public static PagedIterable<GHCommit> getCommits() {
        return commits;
    }

    public static GHCommit getCommit() {
        return commit;
    }
}
