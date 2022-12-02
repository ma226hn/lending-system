package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import model.domain.CategoryType.Category;



/**
 * Represents the item contract.
 */
public class Item {
  private int id;
  private Category category;
  private String name;
  private String description;
  private int dayOfCreation;
  private int cost;
  private Member owner;
  private ArrayList<Integer> reservedDays;
  private ArrayList<Contract> contractsList;

  /**
   *  Item constructor.
   *
   * @param name Item name.
   * @param description .
   * @param category .
   * @param cost .
   * @param owner .
   * @param dayOfCreation .
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "One shared scanner object.")
  public Item(String name, String description, Category category,
      int cost, Member owner, int dayOfCreation, int id) {
    this.category = category;
    this.name = name;
    this.description = description;
    this.dayOfCreation = dayOfCreation;
    this.cost = cost;
    this.owner = owner;
    this.contractsList =  new ArrayList<>();
    this.id = id;
    this.reservedDays = new ArrayList<>();
  }

  /**
   *  .
   *
   * @param name .
   * @param description .
   * @param cost .
   */
  public Item(String name, String description, Category category, int cost) {
    this.name = name;
    this.description = description;
    this.cost = cost;
    this.category = category;

  }
  
  /**
   * .
   *
   * @param name .
   * @param description .
   * @param category .
   * @param cost .
   * @param dayOfCreation .
   */
  public Item(String name, String description,
      Category category, int cost, int dayOfCreation) {
    this.category = category;
    this.name = name;
    this.description = description;
    this.dayOfCreation = dayOfCreation;
    this.cost = cost;
    this.id = 0;
  }


  /**
   * . 
   */
  public Item(Item item) {
    this.id = item.getId();
    this.category = item.getCategory();
    this.name = item.getName();
    this.description = item.getDescription();
    this.dayOfCreation = item.getDayOfCreation();
    this.cost = item.getCost();
    this.owner = item.getOwner();
    this.reservedDays = item.getReservedDays();
    this.contractsList = item.getContractsList();
  }

  public Category getCategory() {
    return this.category;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public int getDayOfCreation() {
    return this.dayOfCreation;
  }

  public int getCost() {
    return this.cost;
  }

  public int getId() {
    return this.id;
  }

  public Member getOwner() {
    Member returnedValue = new Member(owner);
    return returnedValue;
  }

  
  /**
   * Return wich days is the item is not available.
   */
  public ArrayList<Integer> getReservedDays() {
    ArrayList<Integer> copyOfReservedDays = new ArrayList<>();
    copyOfReservedDays.addAll(reservedDays);
    return  copyOfReservedDays;
  }

  /**
   * Returns the all the item contracts.
   */
  public ArrayList<Contract> getContractsList() {
    ArrayList<Contract> copy = new ArrayList<>();
    copy.addAll(this.contractsList);
    return copy;
  }
 
  /**
   * Changes the item info and updates it.
   *
   * @param name .
   * @param description .
   * @param cost .
   */
  protected void changeInformation(String name, String description, Category category,
      int cost) {
    this.name = name;
    this.description = description;
    this.cost = cost;
    this.category = category;
  }

  protected void addContract(Contract contract) {
    this.contractsList.add(contract);
  }

  /**
   * Add the new days in which the item is not available.
   *
   * @param startDay .
   * @param endDay .
   */
  protected void updateReservedDays(int startDay, int endDay) {
    for (int i = startDay; i <= endDay; i++) {
      this.reservedDays.add(i);
    }
  }

  /**
   *  Do pay the credits.
   */
  public void begainPayment(int day) {
    for (Contract c : this.contractsList) {
      if ((c.getStartDay() == day) && (!c.getBorrower().getId().equals(c.getOwner().getId()))) {
        c.pay();
      }
    }

  }

  /**
   *  Returns if the item is available .
   */
  protected boolean isAvailable(int startDay, int endDay) {
 
    for (int i = startDay; i <= endDay; i++) {
      int index1 = this.getReservedDays().indexOf(i);
     
      if (index1 != -1) { 
        return false;
      }
    }
    return true;
  }  
}


  
  

