package eu.skyphantom.skypvp.test;

import eu.skyphantom.skypvp.leagues.League;
import eu.skyphantom.skypvp.provider.StatsProvider;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand extends Command {

    public TestCommand() {
        super("test");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            StatsProvider playerConfig = new StatsProvider(player.getUniqueId());
            playerConfig.setLeague(League.valueOf(args[0]));
        }
        return false;
    }
}
