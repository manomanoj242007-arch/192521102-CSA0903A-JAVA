# Task06 – Analysis

Vehicle is the superclass; Car and TwoWheeler are hierarchical subclasses; ElectricCar extends Car (multilevel). super() invokes parent construction first: Vehicle → Car → ElectricCar. UML: Vehicle <|-- Car <|-- ElectricCar; Vehicle <|-- TwoWheeler. Composition alternative: Vehicle has a VehicleType rate/policy object. Adding a category requires a new policy rather than subclass; fee calculation remains O(1), with one policy reference per vehicle.
