# Task09 – Analysis

Vehicle is abstract because vehicles share registration state but specialize fee behaviour. Chargeable is an interface because charging is optional; a class can implement multiple interfaces. Default and static interface methods are demonstrated. If Vehicle became an interface it could not provide constructor-initialized instance state. Making Chargeable abstract prevents a class extending both due to single class inheritance. Dispatch is effectively O(1).
