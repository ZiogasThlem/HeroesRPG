Heroes RPG

This is first assignment of BackEnd section of Noroff FullStack course

This is a Maven Application to highlight Object Oriented Programming and Design with Java.
This project doesn't have any functionality of it's one, rather it runs in test
mode with locally created objects. Project consists of the source code and tests. 

More specifically:  

1) A Heroes package containing:  

		a)	An abstract Hero class with a single constructor with a name field, and all methods
		and fields required for the subclasses to function properly. The user can create the hero of preference by only typing a name while creating a class.  

		b)	A Hero Attributes class where Strength, Dexterity and Intelligence are initiated modified and displayed per user request. 

		c)	The following Hero Subclasses: Mage, Warrior, Rogue and Ranger. Each of these classes has its own attributes, Armor and Weapon type and of 			course, attributes. 

2) An Items package containing:

		a)	An abstract Item class with constructors for its subclasses (Armor and Weapon).

		b)	Two Subclasses: Weapon and Armor.

		c)	Enumerators for Armor Type, Weapon Type and Slot of each Item created.

3) A package containing custom exceptions:

		a)	An InvalidArmorException, which is throw when a Hero tries to equip and an Armor Item of different Armor Type or higher level or higher level than the 		Hero’s.

		b)	An invalidWeaponException, which is throw when a Hero tries to equip and a Weapon Item of different Weapon Type or higher level than the 		Hero’s.

4)	A test package where all methods are tested on every subclass






		



