# DND_Semi-Autonomous_Vehicles
Models the semi-autonomous vehicles in warehouses of the DND scenario.

The warehouse having a grid mapped out on the floor with barcodes to be scanned by the vehicles to determine current position. The vehicles can be commanded to move to a certain position or to recharge. The vehicles on-board control systems continuously listen for commands and react to a given command. Commands encapsulate all of the required information to go about doing a task and also facilitates the recording of commands and the ability to reverse a command. The vehicle can move to a certain position in the warehouse and then return to it's previous position just by reversing the command. Some behaviours of vehicles are state dependant and some are not both of which can be encapsulated in a command object.

Console input usage: print|recharge|move x,y

![alt text](https://github.com/Sam-Hammersley/DND_Semi-Autonomous_Vehicles/blob/master/Untitled%20Diagram.png?raw=true)
