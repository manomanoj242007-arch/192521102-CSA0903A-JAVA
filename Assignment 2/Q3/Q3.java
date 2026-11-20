import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Student {

    private final String regNo;
    private final String name;

    public Student(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Student)) {
            return false;
        }

        Student student = (Student) object;

        return regNo.equals(student.regNo);
    }

    // hashCode() must use the same identity field as equals().
    @Override
    public int hashCode() {
        return Objects.hash(regNo);
    }

    @Override
    public String toString() {
        return regNo + " " + name;
    }
}

public class Q3 {

    public static void main(String[] args) {

        Set<Student> present = new HashSet<>();

        // First scan at Gate 1.
        present.add(new Student("192311001", "Arun"));

        // Second student scan.
        present.add(new Student("192311002", "Divya"));

        // Same student scanned again at Gate 3.
        present.add(new Student("192311001", "Arun"));

        System.out.println("Unique students: " + present.size());

        System.out.println("\nStudent details:");

        for (Student student : present) {
            System.out.println(student);
        }
    }
}