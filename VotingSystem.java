import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private boolean hasVoted;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.hasVoted = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
}

public class VotingSystem {
    private Map<String, User> users;
    private Map<String, Integer> candidates;

    public VotingSystem() {
        users = new HashMap<>();
        candidates = new HashMap<>();
        // Initialize candidates
        candidates.put("1. BJP", 0);
        candidates.put("2. Indian National Congress", 0);
        candidates.put("3. Aam Aadmi Party (AAP)", 0);
    }

    public void registerUser(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password));
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Username already exists.");
        }
    }

    public User loginUser(String username, String password) {
        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            System.out.println("Login successful.");
            return users.get(username);
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }

    public void displayCandidates() {
        System.out.println("Available Candidates:");
        for (String candidate : candidates.keySet()) {
            System.out.println(candidate);
        }
    }

    public void vote(User user, int candidateNumber) {
        String candidateKey = null;
        for (String key : candidates.keySet()) {
            if (key.startsWith(Integer.toString(candidateNumber))) {
                candidateKey = key;
                break;
            }
        }
        if (user.hasVoted()) {
            System.out.println("You have already voted.");
        } else if (candidateKey != null) {
            candidates.put(candidateKey, candidates.get(candidateKey) + 1);
            user.setHasVoted(true);
            System.out.println("Vote cast successfully.");
        } else {
            System.out.println("Invalid candidate number.");
        }
    }

    public void displayResults() {
        System.out.println("Voting Results:");
        for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }
    }

    public static void main(String[] args) {
        VotingSystem system = new VotingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Vote");
            System.out.println("4. Display Results");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    system.registerUser(username, password);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    system.loginUser(username, password);
                    break;
                case 3:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();
                    User user = system.loginUser(username, password);
                    if (user != null) {
                        system.displayCandidates();
                        System.out.print("Enter candidate number to vote for: ");
                        int candidateNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        system.vote(user, candidateNumber);
                    }
                    break;
                case 4:
                    system.displayResults();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
