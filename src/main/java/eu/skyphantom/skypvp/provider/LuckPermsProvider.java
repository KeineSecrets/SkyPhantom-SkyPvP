package eu.skyphantom.skypvp.provider;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class LuckPermsProvider {

    private static LuckPerms api;

    public static void registerProvider() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            api = provider.getProvider();
        }
    }

    public static LuckPerms get() {
        if (api == null) throw new RuntimeException("LuckPerms not found");
        return api;
    }

    public static boolean isPlayerInGroup(UUID uuid, String group) {
        User user = getLuckPermsUser(uuid);
        Collection<Group> inheritedGroups = user.getInheritedGroups(user.getQueryOptions());
        return inheritedGroups.stream().anyMatch(g -> g.getName().equals(group));
    }

    public static String getGroupName(UUID uuid) {
        return Objects.requireNonNull(get().getUserManager().getUser(uuid)).getPrimaryGroup();
    }

    public static String getGroupNameOffline(UUID uuid) {
        AtomicReference<String> group = new AtomicReference<>();
        UserManager userManager = get().getUserManager();
        CompletableFuture<User> userFuture = userManager.loadUser(uuid);

        userFuture.thenAccept(user -> {
            group.set(user.getPrimaryGroup());
        });
        return group.get();
    }

    public static User getLuckPermsUser(UUID uuid) {
        UserManager userManager = get().getUserManager();
        CompletableFuture<User> userFuture = userManager.loadUser(uuid);

        return userFuture.join();
    }

    public static Group getGroup(String group) {
        return get().getGroupManager().getGroup(group);
    }

    public static Group getGroup(UUID uuid) {
        return get().getGroupManager().getGroup(getGroupName(uuid));
    }

    public static String getGroupDisplayname(UUID uuid) {
        return getGroup(getLuckPermsUser(uuid).getPrimaryGroup()).getDisplayName().replace('&', '§');
    }

    public static void addPermission(UUID uuid, String permission) {
        get().getUserManager().modifyUser(uuid, user -> {
            user.data().add(Node.builder(permission).build());
        });
    }

    public static void addPermissions(UUID uuid, String... permissions) {
        get().getUserManager().modifyUser(uuid, user -> {
            for (String permission : permissions) {
                user.data().add(Node.builder(permission).build());
            }
        });
    }

}
