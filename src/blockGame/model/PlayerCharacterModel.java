// Rene

package blockGame.model;

import java.util.ArrayList;
import java.util.List;


public class PlayerCharacterModel {

// GRUNDLEGENDE FELDER
private String name;
private int level             = 1;
private int experience        = 0;
/*private int experienceToNext  = GameConstants.DEFAULT_XP_TO_NEXT_LEVEL; Müsste noch angelegt werden*/

//ÜBERLEBENSATTRIBUTE
/*private int health            = GameConstants.DEFAULT_MAX_HEALTH;  Müsste noch angelegt werden*/
/*private int maxHealth         = GameConstants.DEFAULT_MAX_HEALTH;  Müsste noch angelegt werden*/
/*private int stamina           = GameConstants.DEFAULT_MAX_STAMINA; Müsste noch angelegt werden*/
/*private int maxStamina        = GameConstants.DEFAULT_MAX_STAMINA; Müsste noch angelegt werden*/
private int hunger            = 0;   // 0 = satt, 100 = verhungert
private int thirst            = 0;   // 0 = kein Durst, 100 = verdurstet
private int radiation         = 0;   // 0 = sauber, 100 = tödlich
private int infection         = 0;   // zukünftige Nutzung
private int mentalHealth      = 100; // 0 = zusammengebrochen

// ATTRIBUTE
private int strength   = 5;
private int agility    = 5;
private int intelligence = 5;
private int endurance  = 5;
private int perception = 5;
private int luck       = 5;

// BERECHNETE WERTE
private int attackPower    = 0;
private int defense        = 0;
private int maxCarryWeight = 0;
private int carryWeight    = 0;

// POSITION
private int x = 0, y = 0;
private int movementSpeed = 1;
private String currentArea = "Safe Zone";

// INVENTAR & AUSRÜSTUNG
/* private final PlayerInventory inventory = new PlayerInventory(20);  Müsste noch angelegt werden*/
/* private Weapon equippedWeapon   = null;							   Müsste noch angelegt werden*/
/* private Armor  equippedArmor    = null;							   Müsste noch angelegt werden*/

// FERTIGKEITEN
private int scavengingSkill = 1;
private int craftingSkill   = 1;
private int combatSkill     = 1;
private int stealthSkill    = 1;
private int medicalSkill    = 1;

// ZUSTAND
private boolean isAlive      = true;
private boolean isSick       = false;
private boolean isRadioactive = false;
private final List<String> statusEffects = new ArrayList<>();


// GETTER
public String getName() { return name; }
public int getLevel()   { return level; }
public int getExperience() { return experience; }
//public int getHealth()  { return health; }
//public int getStamina() { return stamina; }
public int getHunger()  { return hunger; }
public int getThirst()  { return thirst; }
public int getRadiation() { return radiation; }
public int getAttackPower() { return attackPower; }
public int getDefense() { return defense; }
//public PlayerInventory getInventory() { return inventory; }
}

	

	

	
