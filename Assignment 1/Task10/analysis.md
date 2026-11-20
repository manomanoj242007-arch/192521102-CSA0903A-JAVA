# Task10 – Analysis

Suggested packages: parking.model, parking.service and parking.app. Keep fields private; expose validated public methods; use protected for subclass extension and package-private for internal helpers. Integer demonstrates wrapper/autoboxing; avoid Integer == Integer because it compares object identity; use equals(). Public fields bypass validation. Central validation is O(1) per update and avoids repeating checks in callers.
