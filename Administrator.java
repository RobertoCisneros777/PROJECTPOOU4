package ProjectPOO;

import java.util.ArrayList;

    class Administrator extends User {
        ArrayList<Permissions> permissions;
        boolean isSuperAdmin;

        public boolean isIsSuperAdmin() {
            return isSuperAdmin;
        }

        public Administrator(Profile profile, String userName, String password, boolean isSuperAdmin, ArrayList<Permissions> permissions) {
            super(profile, userName, password);
            this.isSuperAdmin = isSuperAdmin;
            this.permissions = permissions;
        }
    }