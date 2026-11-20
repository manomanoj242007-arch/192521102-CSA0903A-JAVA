# Task A3 – Discover the Data-Access Design Pattern (5 marks)

## A3.1 Common pattern
Repeated steps: obtain connection, prepare SQL, bind parameters, execute, iterate ResultSet, map rows to objects, handle SQL exceptions, close ResultSet/statement/connection. Differences: entity type, table/column names, key type, SQL and mapping logic.

## A3.2 Generic DAO
```java
public interface Dao<T, K> {
    void insert(T entity) throws SQLException;
    Optional<T> findById(K id) throws SQLException;
    List<T> findAll() throws SQLException;
    boolean update(T entity) throws SQLException;
    boolean delete(K id) throws SQLException;
}
```
StudentDao implements Dao<Student, Integer>; it owns student SQL and maps rows. A service calls StudentDao rather than embedding SQL in Swing/console classes.

## A3.3 Object-table mapping
Student(id, registerNo, name, email) maps to student(id PRIMARY KEY, register_no UNIQUE, name, email). ResultSet columns map to constructor/setter fields.

## Alternative
Embedding SQL in GUI/service classes duplicates SQL in multiple places. A column rename then requires edits at each query/mapping location; DAO localizes changes. Both designs have broadly the same database-side O(1) indexed lookup or O(n) scan and similar row memory. DAO adds a small abstraction/dispatch overhead but reduces maintenance cost.
