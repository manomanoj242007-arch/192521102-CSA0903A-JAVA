# Task A1 – Design a File Format (7 marks)

## A1.1 Record layouts
**Delimited text (CSV-like):** pass_id,reg_no,name,route_no,boarding_stop,validity_date,amount_paid. UTF-8, one record per line. Quote fields containing comma, quote or newline; double embedded quotes. A robust parser must understand quoted fields; never split blindly on commas for production data.

Example:
`P1001,192311001,"Arun, K",12,"Central Stop",2027-03-31,4500.00`

**Binary DataOutputStream layout:** write fields in a documented order: writeUTF(passId), writeUTF(regNo), writeUTF(name), writeInt(routeNo), writeUTF(boardingStop), writeUTF(validityDate), writeDouble(amountPaid). UTF strings are length-prefixed; int is 4 bytes; double is 8 bytes. DataOutputStream is big-endian. Version the file header (magic + version) before records.

## A1.2 Pseudocode – SIMATS standard
```
ALGORITHM ADD_PASS(passFile, pass)
BEGIN
  OPEN BufferedWriter in APPEND mode
  VALIDATE required fields and numeric values
  ENCODE each field using quoted-delimited format
  WRITE encoded record and newline
  FLUSH and CLOSE writer
END

ALGORITHM FIND_PASS(passFile, wantedID)
BEGIN
  IF file does not exist THEN RETURN NOT_FOUND
  OPEN BufferedReader
  WHILE line can be read
    PARSE line using quote-aware parser
    IF malformed THEN record error and CONTINUE
    IF field pass_id equals wantedID THEN RETURN record
  END WHILE
  CLOSE reader
  RETURN NOT_FOUND
END

ALGORITHM LIST_ROUTE(passFile, wantedRoute)
BEGIN
  OPEN BufferedReader
  WHILE line can be read
    PARSE and validate record
    IF valid and route_no equals wantedRoute THEN DISPLAY record
    ELSE IF malformed THEN log line number and continue
  END WHILE
  CLOSE reader
END
```
Use BufferedReader/BufferedWriter over FileReader/FileWriter (or explicit UTF-8 Files.newBufferedReader/newBufferedWriter) to reduce small read/write calls. FileInputStream/FileOutputStream are appropriate for raw bytes and binary DataOutputStream formats, not convenient for text parsing.

## A1.3 Damaged data
Validate field count, date, route integer and amount decimal. Quarantine malformed lines with line number and reason; continue reading valid records. Missing file should be reported distinctly and created only for add. Write to a temporary file, flush/close, then atomically rename where supported; include checksum/version or journal for stronger recovery. A partial final line is detected by missing delimiter/field count and quarantined.

## Alternative: RandomAccessFile
With fixed-size records and an index mapping pass ID to record number, seek to i × recordSize and read one record in O(1). Without an index, searching IDs still requires O(n) scan. Fixed-size fields waste padding when names/stops are short; choose maximum lengths carefully. Sequential delimited file scan is O(n) time and O(1) extra memory.
