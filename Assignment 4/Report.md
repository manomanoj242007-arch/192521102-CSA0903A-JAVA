# Assignment 4 Report – CO4

The full formatted PDF report is provided separately in this conversation. This Markdown file records the evidence and implementation status in the repository.

## Task folders and evidence
- Task01: FeeWindow.java, FeeService.java, output_screenshot.svg
- Task02: AppointmentDesk.java, output_screenshot.svg
- Task03: LibrarySearch.java, output_screenshot.svg
- Task04: DrawPad.java, output_screenshot.svg
- Task05: Four converter implementations, output_screenshot.svg
- Task06: EventRegistrationApp.java, schema.sql, output_screenshot.svg

## Important execution requirements
- GUI programs require a desktop graphical environment.
- Task01 requires a JDBC Connection supplied by a database setup before FeeWindow can be launched.
- Task06 requires MySQL Server and MySQL Connector/J. Update the sample password in RegistrationService before running.
- Task06's current source demonstrates a background insert and paged list query; the full assignment brief also requests event filtering and CSV export, which must be added and tested before final submission.
- The SVG output evidence follows the dark terminal screenshot style used in Assignment 2. These are illustrative test/evidence panels, not captured screenshots from a live GUI session. Capture actual GUI screenshots when executing on your desktop.

## Compile
From a task folder:
```bash
javac *.java
```
Run the class containing `main`. Task01 and Task06 require their database configuration as described above.
