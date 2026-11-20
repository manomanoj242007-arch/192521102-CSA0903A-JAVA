# Assignment 4 – Event-Driven GUI Design (CO4)

**Student:** Umasankar  
**Register number:** 192521102  
**Course:** Programming in Java (CSA09)  
**Java version:** Java 17+

## Contents
- Task01: GUI design review and repaired fee-payment separation (FeeWindow / FeeService).
- Task02: Hospital outpatient appointment desk with department-driven doctor list.
- Task03: Campus library search screen using nested Swing layout regions.
- Task04: Repaired AWT drawing pad; points persist across repaint.
- Task05: Four event-handler styles and completed lambda converter.
- Task06: Layered Swing/JDBC event registration application and MySQL schema.

## Compile and run
Open a terminal in the relevant task folder and compile:
```bash
javac *.java
```
Run the class containing main, for example:
```bash
java DrawPad
java AppointmentDesk
java LibrarySearch
java ConverterLambda
java EventRegistrationApp
```

Task01 requires a JDBC Connection supplied by a database setup class before it can be launched. Task06 requires MySQL Connector/J on the classpath, a MySQL server, and the schema in Task06/schema.sql. Replace the sample database password in RegistrationService before running.

## Notes
The Java GUI programs require a graphical desktop. The Task06 database integration cannot run until the local MySQL database and Connector/J driver are configured. The assignment PDF asks for design analysis, pseudocode, event tables, diagrams, alternative-concept complexity comparisons, test screenshots and a report; those written evidence items are provided separately in the report.
