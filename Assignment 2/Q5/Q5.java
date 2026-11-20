import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Team {

    final String name;
    final int points;
    final int goalDiff;

    Team(String name, int points, int goalDiff) {
        this.name = name;
        this.points = points;
        this.goalDiff = goalDiff;
    }

    @Override
    public String toString() {
        return name + "(" + points + "," + goalDiff + ")";
    }
}

class Leaderboard<T> {

    private final List<T> items = new ArrayList<>();
    private final Comparator<? super T> order;

    Leaderboard(Comparator<? super T> order) {
        this.order = order;
    }

    public void add(T item) {
        items.add(item);
    }

    public void addAll(List<? extends T> newItems) {
        items.addAll(newItems);
    }

    public List<T> ranking() {

        // Make a copy so the original insertion order is not changed.
        List<T> sortedItems = new ArrayList<>(items);

        // Sort the copied list using the supplied comparator.
        sortedItems.sort(order);

        return sortedItems;
    }
}

public class Q5 {

    public static void main(String[] args) {

        // Higher points first, then higher goal difference,
        // then team name in alphabetical order.
        Comparator<Team> byRank =
            Comparator.comparingInt((Team team) -> team.points)
                .reversed()
                .thenComparing(
                    Comparator.comparingInt(
                        (Team team) -> team.goalDiff
                    ).reversed()
                )
                .thenComparing(
                    team -> team.name,
                    String.CASE_INSENSITIVE_ORDER
                );

        Leaderboard<Team> board = new Leaderboard<>(byRank);

        board.add(new Team("Panthers", 9, 4));
        board.add(new Team("Eagles", 9, 6));
        board.add(new Team("Cobras", 6, 1));
        board.add(new Team("Bisons", 9, 4));

        System.out.println("Team Leaderboard:");
        System.out.println(board.ranking());

        // Demonstrate another tie at points and goal difference.
        List<Team> tieTeams = new ArrayList<>();

        tieTeams.add(new Team("Beta", 10, 2));
        tieTeams.add(new Team("Alpha", 10, 2));

        Leaderboard<Team> tieBoard = new Leaderboard<>(byRank);
        tieBoard.addAll(tieTeams);

        System.out.println("\nAlphabetical tie-break:");
        System.out.println(tieBoard.ranking());
    }
}