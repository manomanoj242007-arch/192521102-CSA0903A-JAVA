# Assignment 5 – CO5: Data Persistence Engineering
**Student:** Umasankar | **Register number:** 192521102 | **Course:** Programming in Java (CSA09)
**Java:** Java 17+ | **Database:** MySQL 8+ with MySQL Connector/J

## Tasks
- Task01 / A1 – Bus-pass file format, operations, damaged-data handling.
- Task02 / A2 – Buffered I/O and complexity analysis.
- Task03 / A3 – Generic DAO data-access pattern and object/table mapping.
- Task04 / A4 – Serialization, transient fields and versioning.
- Task05 / A5 – JDBC transactions and SQL injection prevention.
- Task06 / B1 – Complete PassStore and buffered line-count experiments.
- Task07 / B2 – Adversarial login and damaged-file tests.
- Task08 / B3 – Statement, PreparedStatement and JDBC batch comparison.
- Task09 / B4 – Batch migration, DatabaseMetaData and production decision.

## Compile and run
For file-only programs:
```bash
cd "Assignment 5/Task06"
javac *.java
java PassStore
java BufferedLineCounter
```
For JDBC tasks, run the SQL script in Task08/schema.sql or Task09/schema.sql, add MySQL Connector/J to the classpath, and update DB_URL, DB_USER and DB_PASSWORD in the Java source. Windows classpath example:
```bash
javac -cp ".;mysql-connector-j-8.4.0.jar" *.java
java -cp ".;mysql-connector-j-8.4.0.jar" PassLoaderBenchmark
```
Linux/macOS use colon instead of semicolon.

## Results
B1 demonstrates add/find/list, skips malformed records, and compares buffered with unbuffered line counting. B2 demonstrates why parameterized SQL rejects injected credentials. B3 provides three insert strategies and transaction rollback. B4 migrates validated CSV rows in batches and reports database metadata.

**Evidence note:** Output images in each task folder are terminal-style illustrative test transcripts, not photographs of an actual local execution. Replace/add genuine screenshots and measured timing values after running on your own computer and database. No timing numbers are fabricated as measured results.
