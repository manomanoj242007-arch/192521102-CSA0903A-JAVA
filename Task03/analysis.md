# Task03 – Analysis

Allocation pseudocode: validate registration; scan floors and slots; occupy the first empty slot and return coordinates; return null if full. Release: validate floor/slot; return false for invalid or empty positions; otherwise clear and return true. Fee: hours=(minutes+59)/60; fee=hours*rate; valid monthly pass makes fee zero. Allocation/release worst case O(F*S), storage O(F*S). A free-slot stack can make both O(1) with O(F*S) extra storage.
