# Task A5 – Design for Correctness and Safety (4 marks)

## A5.1 Transaction pseudocode
```
ALGORITHM TRANSFER(fromWallet, toWallet, amount)
BEGIN
  GET JDBC connection
  SET autoCommit = false
  TRY
    UPDATE wallet SET balance = balance - amount
      WHERE wallet_id = fromWallet AND balance >= amount
    IF affected rows != 1 THEN RAISE insufficient funds
    UPDATE wallet SET balance = balance + amount
      WHERE wallet_id = toWallet
    IF affected rows != 1 THEN RAISE destination error
    COMMIT
  CATCH error
    ROLLBACK
    REPORT failure
  FINALLY
    RESTORE autoCommit and CLOSE resources
END
```
Validate amount > 0 and use appropriate isolation/locking. Both updates commit or neither does.

## A5.2 SQL injection
Concatenating input into SQL allows quote characters and SQL syntax to change the WHERE expression. An attacker can craft an ID/password input that makes the predicate true. Use:
```java
String sql = "SELECT 1 FROM users WHERE id = ? AND pwd_hash = ?";
try (PreparedStatement ps = con.prepareStatement(sql)) {
    ps.setString(1, id);
    ps.setString(2, passwordHash);
    try (ResultSet rs = ps.executeQuery()) {
        return rs.next();
    }
}
```
Parameters are treated as data, not executable SQL. Store salted password hashes and use a password-hashing algorithm; do not store raw passwords.

## Alternative: auto-commit
Two independent auto-commit updates can leave money deducted but not credited if failure occurs between them. A transaction adds commit/rollback coordination and logging overhead, usually a small cost relative to network/database latency, but protects the invariant that total wallet funds remain constant. Separate updates are unsafe for this requirement.
