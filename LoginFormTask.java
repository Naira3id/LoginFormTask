import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class User {

    private String username;
    private String password;
    private String role;

    User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}

class WelcomePage extends Frame {

    WelcomePage(User user) {
        super("Welcome Page");
        setLayout(new GridBagLayout());

        Label welcomeLabel = new Label("Hello, " + user.getUsername() + " (" + user.getRole() + ")", Label.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        add(welcomeLabel);

        setSize(350, 200);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}

 public class LoginFormTask extends Frame {


     private List<User> userList = new ArrayList<>();
    public LoginFormTask() {
        super("Login");
        loadUsers();

        setLayout(new GridBagLayout());
        Panel content = new Panel(new BorderLayout(10, 15));

        Label title = new Label("Welcome back", Label.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        content.add(title, BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(3, 2, 8, 8));
        form.add(new Label("Username:", Label.RIGHT));
        TextField username = new TextField(18);
        form.add(username);

        form.add(new Label("Password:", Label.RIGHT));
        TextField password = new TextField(18);
        password.setEchoChar('*');
        form.add(password);

        form.add(new Label("Role:", Label.RIGHT));
        Choice role = new Choice();
        role.add("Student");
        role.add("TA");
        role.add("Professor");
        form.add(role);

        content.add(form, BorderLayout.CENTER);

        Panel buttons = new Panel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        Button cancel = new Button("Cancel");
        Button login = new Button("Login");
        buttons.add(cancel);
        buttons.add(login);
        content.add(buttons, BorderLayout.SOUTH);

        add(content);

        login.addActionListener(e -> {
            String inputUser = username.getText().trim();
            String inputPass = password.getText().trim();
            String selectedRole = role.getSelectedItem();

            User foundUser = findUser(inputUser);

            if (foundUser != null && foundUser.getPassword().equals(inputPass) && foundUser.getRole().equalsIgnoreCase(selectedRole)) {
                new WelcomePage(foundUser).setVisible(true);
                dispose();
            } else {

                title.setText("Invalid Login!");
                title.setForeground(Color.RED);
            }
        });

        cancel.addActionListener(e -> {
            dispose();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { dispose(); }
        });
        setSize(420, 340);
        setLocationRelativeTo(null);

    }

    private void loadUsers() {
        userList.add(new User("student1", "1234", "Student"));
        userList.add(new User("ta1", "1234", "TA"));
        userList.add(new User("prof1", "1234", "Professor"));
    }

    private User findUser(String name) {
        for (User u : userList) {
            if (u.getUsername().equals(name)) {
                return u;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        new LoginFormTask().setVisible(true);

    }
}
