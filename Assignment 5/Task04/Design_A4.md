# Task A4 – What Should Be Serialized? (4 marks)

Mark password and live JDBC Connection transient. Passwords should not be persisted in plaintext; store a secure password hash in an appropriate credential store if required. A live Connection is a runtime resource and is not meaningfully serializable. Recreate connections after deserialization. The Hostel → Rooms → Residents reachable object graph is traversed; all non-transient reachable serializable objects are written, preserving shared references and cycles.

Declare a stable serialVersionUID. Adding phoneNumber while keeping the same UID allows compatible old streams to deserialize; the new field receives its default value (null for String) unless readObject/readResolve supplies a migration default. An incompatible class change or changed UID can cause InvalidClassException.

## Alternative: CSV/JSON
Text formats are human-readable and often larger due to field names/delimiters/escaping. Save/load n residents is O(n) time and O(n) memory if materialized; streaming can reduce extra memory. Adding a field can be handled with optional properties/defaults and explicit schema versioning. Java serialization is convenient for Java object graphs but is Java-specific and class-version sensitive.
