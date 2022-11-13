package eu.skyphantom.skypvp.utils.time;
/*
 * TickUnit.java | 21:05
 * (c) KeineSecrets (2022)
 */

public enum TickUnit {

    HOUR {
        public int toHours(int duration) {
            return duration;
        }

        public int toMinutes(int duration) {
            return duration * 60;
        }

        public int toSeconds(int duration) {
            return duration * 60 * 60;
        }

        public int toTicks(int duration) {
            return duration * 60 * 60 * 20;
        }
    },

    MINUTE {
        public int toHours(int duration) {
            return duration / 60;
        }

        public int toMinutes(int duration) {
            return duration;
        }

        public int toSeconds(int duration) {
            return duration * 60;
        }

        public int toTicks(int duration) {
            return duration * 60 * 20;
        }
    },

    SECOND {
        public int toHours(int duration) {
            return duration / 60 / 60;
        }

        public int toMinutes(int duration) {
            return duration / 60;
        }

        public int toSeconds(int duration) {
            return duration;
        }

        public int toTicks(int duration) {
            return duration * 20;
        }
    },

    TICK {
        public int toHours(int duration) {
            return duration / 20 / 60 / 60;
        }

        public int toMinutes(int duration) {
            return duration / 20 / 60;
        }

        public int toSeconds(int duration) {
            return duration / 20;
        }

        public int toTicks(int duration) {
            return duration;
        }
    };

    public int toHours(int duration) {
        throw new AbstractMethodError();
    }

    public int toSeconds(int duration) {
        throw new AbstractMethodError();
    }

    public int toMinutes(int duration) {
        throw new AbstractMethodError();
    }

    public int toTicks(int duration) {
        throw new AbstractMethodError();
    }

}
