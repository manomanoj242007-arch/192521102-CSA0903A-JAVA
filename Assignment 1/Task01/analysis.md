# Task01 – Analysis

Problem analysis: Vehicle stores identity and category; ParkingSlot prevents double occupancy; ParkingFloor owns slots; ParkingFacility allocates/releases; ParkingTicket records entry; ParkingPass validates eligibility; FeeCalculator applies rates. Rules: unique active registration, unique ticket IDs, valid release only, monthly student/staff passes park free. Parallel arrays require O(n) search and synchronized updates; object fields remain together. Both require O(n) storage.
