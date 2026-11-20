# Task08 – Analysis

Vehicle[] stores subclass objects through upcasting. Declared type is Vehicle; actual object selects ratePerHour() by dynamic dispatch. Overloads calculateFee(int) and calculateFee(LocalTime,LocalTime) demonstrate compile-time polymorphism. The last true occurs because equality compares registration number only; this is correct only if registration number is the complete identity across vehicle categories.
