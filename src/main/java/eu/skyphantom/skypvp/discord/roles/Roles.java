package eu.skyphantom.skypvp.discord.roles;

public enum Roles {

    OWNER(1038437906404085860L, "Owner × "),
    ADMIN(1038437907268116510L, "Admin × "),
    DEVELOPER(1038438487969517658L, "Dev × "),
    SRMODERATOR(1038437909860192368L, "SrMod × "),
    MODERATOR(1038437910799724554L, "Mod × "),
    SRBUILDER(1038437911579861093L, "SrBuild × "),
    BUILDER(1038437913043677275L, "Build × "),
    CONTENT(1038437913907707944L, "Content × "),
    SUPPORTER(1038437914796896286L, "Sup × ");


    final long id;
    final String toRename;

    Roles(long id, String toRename) {
        this.id = id;
        this.toRename = toRename;
    }

    public long getId() {
        return id;
    }

    public String getToRename() {
        return toRename;
    }
}
