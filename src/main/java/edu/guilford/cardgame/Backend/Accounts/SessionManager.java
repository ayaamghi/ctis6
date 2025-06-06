package edu.guilford.cardgame.Backend.Accounts;

public class SessionManager {



    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void clearSession() {
        currentUser = null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static String getCurrentUserName() {
        return currentUser != null ? currentUser.getName() : null;
    }


}
