package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User rsl = null;
        for (User user:users) {
            if (login.equals(user.getUsername())) {
                rsl = user;
            }
        }
        if (rsl == null) {
            throw new UserNotFoundException("user not found");
        }
        return rsl;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (!user.isValid() || user.getUsername().length() < 3) {
            throw new UserInvalidException("user invalid");
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            User[] users = {
                    new User("Petr Arsentev", true)
            };
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException ei) {
            System.out.println(ei.getMessage());
        } catch (UserNotFoundException en) {
            System.out.println(en.getMessage());
        }
    }
}
