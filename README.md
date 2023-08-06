# Geos Vanilla QoL Mod

## What is this?

This is a mod Designed with Servers in mind. It changes some basic Vanilla features (in a Configurable way) to make the game just a little bit more enjoyable.

## What can it do?

Currently, the following Features have been Implemented:
* [Void Traveling](#void-travel)
* [Chest Graves](#chest-graves)
* [Looting on Bows](#looting-on-bows)
* [Constant Anvil Cost (Calculated by Enchantment Rarity)](#constant-anvil-cost)
* [Non-Italic Rename Items](#non-italic-item-names)
* [Status and Reload Command](#status-and-reload-command)


## Feature Demonstrations

### Void Travel
Fallen into the Void? Don't worry! Every Time you take damage, you have a 50% chance of teleporting into the Overworld, at 16\*X, 600Y, 16\*Z.

Afraid that you'll lose your Stuff by Falling into the Void? Then this next Feature is gonna save your day.

---

### Chest Graves
When you die, you drop your Stuff. Everybody knows that, right?
Well, now when you die, you turn into chests.
This also takes height limits into mind. If you die under Y -64, Your chests will spawn at Y -64. If you (somehow) die over Y 255, your chests will spawn at Y 255.

---

### Looting on Bows
Tired of holding your Power Bow in your offhand and your Looting sword in your main hand to abuse that age old bug [MC-3304](https://bugs.mojang.com/browse/MC-3304)?
Well, now you can put looting on your Bow by Just Using an Anvil.

---

### Constant Anvil Cost
Ever heard of [Repair Cost](https://minecraft.fandom.com/wiki/Anvil_mechanics#Anvil_uses)? It's the thing every Minecraft Player hates, because it increases the Level Cost every time you use the Anvil.
Well, no longer. RepairCost will now be always be 1, which results in consistency.
Also: Enchantment Apply costs have been changed to not consider the Level of Enchantment you're applying, resulting in cheaper Anvil Costs than usual.

---

### Non-Italic Item Names
If you've ever renamed an Item, you've probably noticed that the name became *italic*. Well, if you have this Feature on, the Name will just be White.

---

### Status and Reload Command
The Mod comes with a Command. ``/gvqol <status/reload>``<br>
The "Status" Argument shows which Features of the Mod are Enabled and Disabled.<br>
The "Reload" Argument is for Operators and is used to reload the Config file.

---

## Configuration
You will find the Config File in ``config/gvqol.json``.

Here's the default Configuration File, Just in case you mess something up.
```json
{
  "voidTravel": true,
  "chestGraves": true,
  "bowLooting": true,
  "anvilCost": true,
  "anvilRename": true
}
```
## Contribution
Feel free to contribute in any way! Graphics, Texts, Code, etc.
There's just one Rule: Do not modify Registries. If you do, it's no longer a Serverside mod.

## License
This Project is licensed under the [MIT License](https://en.wikipedia.org/wiki/MIT_License).
