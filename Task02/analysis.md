# Task02 – Analysis

Use int for indexes/duration, String for registration, LocalDateTime for timestamps and BigDecimal for money. A jagged String[][] supports different slot counts. For 125 minutes, (minutes+59)/60 gives 3 hours; integer division 125/60 incorrectly gives 2. Casting after division cannot restore precision. A flat array formula assumes equal floor sizes; jagged arrays and ArrayList<String[]> support uneven floors. Indexed access is O(1); storage O(total slots).
