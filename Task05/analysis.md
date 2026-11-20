# Task05 – Analysis

The ticket sequence is static because it belongs to the class. A synchronized static method protects increments. Occupancy is instance data because each facility has its own count. An instance ticket counter could issue duplicate 1001 at two gates. One global counter uses O(1) space; per-facility counters use O(k) for k facilities. A ticket becomes garbage-collection eligible when no reachable reference remains.
