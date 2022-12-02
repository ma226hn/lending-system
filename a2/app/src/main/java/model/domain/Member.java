package model.domain;

import java.util.ArrayList;

/**
 * Member class.
 */
public class Member {
  private String firstName;
  private String lastName;
  private String email; // uniq
  private String phoneNumber; // uniq
  private int memberCreationDay;
  private int credits;
  private String id; // uniq (6 alpha)
  private ArrayList<Item> items; // array list of item.

  /**
   * Constructor.
   */
  public Member(String firstName, String lastName, String email,
      String phoneNumber, int memberCreationDay,
      String id, int credits) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.id = id;
    this.memberCreationDay = memberCreationDay;
    this.credits = credits;
    this.items = new ArrayList<>();

  }

  /**
   * Constructor.
   */
  public Member(Member m) {
    this.firstName = m.getFirstName();
    this.lastName = m.getLastName();
    this.email = m.getEmail();
    this.phoneNumber = m.getPhoneNumber();
    this.memberCreationDay = m.getMemberCreationDay();
    this.credits = m.getCredits();
    this.id = m.getId();
    this.items = m.getItems();
  }

  /**
   * Constructor.
   */
  public Member(String firstName, String lastName, String email, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;

  }

  /**
   * .
   *
   * @param firstName .
   * @param lastName .
   * @param email .
   * @param phoneNumber .
   * @param memberCreationDay .
   * @param credits .
   */
  public Member(String firstName, String lastName, String email, String phoneNumber,
       int memberCreationDay, int credits) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.memberCreationDay = memberCreationDay;
    this.credits = credits;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public int getMemberCreationDay() {
    return this.memberCreationDay;
  }

  public int getCredits() {
    return this.credits;
  }

  public String getId() {
    return this.id;
  }

  /**
   *  Returns all items.
   */
  public ArrayList<Item> getItems() {
    ArrayList<Item> copyItems = new ArrayList<>();
    copyItems.addAll(this.items);
    return copyItems;
  }

  /**
   * Add new item.
   *
   * @param item .
   * @return .
   */
  protected Item addNewItem(Item item, int id) {



  
    this.items
        .add(new Item(item.getName(), item.getDescription(),
          item.getCategory(), item.getCost(), this, item.getDayOfCreation(),
          id));
    this.credits += 100;
    return items.get(items.size() - 1);
  }

  /**
   * Get the name of all items .
   */
  public ArrayList<String> getItemsName() {
    int i = 0;
    ArrayList<String> itemsName = new ArrayList<>();
    for (i = 0; i < this.items.size(); i++) {
      itemsName.add(this.items.get(i).getName());
    }
    return itemsName;
  }

  /**
   * Delete an item.
   *
   * @param id .
   */
  protected void deleteSpecificItem(int id) {
    for (Item itemForDelete : this.items) {
      if (itemForDelete.getId() == id) {
        this.items.remove(itemForDelete);
        break;
      }
    }
  }

  /**
   * Updates the item info.
   *
   * @param index .
   * @param item .
   */
  protected void changeSpecificItemInfo(int index, Item item) {
    for (Item itemForUpdate : this.items) {
      if (itemForUpdate.getId() == index) {
        itemForUpdate.changeInformation(item.getName(),
             item.getDescription(), item.getCategory(), item.getCost());
        break;
      }
    }
  }


  /**
   * Provides functionality to change a member's information.
   */
  protected void modifyMemberInformation(Member member) {
    this.firstName = member.firstName;
    this.lastName = member.lastName;
    this.email = member.email;
    this.phoneNumber = member.phoneNumber;
  }

  /**
   * Search item. 
   */
  public boolean searchItem(int itemId) {
    Item [] itemArray = this.items.toArray(new Item [items.size()]);
    int i;
    for (i = 0; i < itemArray.length; i++) {
      if (itemArray[i].getId() == itemId) {
        return true;
      }
    }
    return false;
  }

  protected void updateCreditsValue(int cost) {
    this.credits = this.credits + cost;
  }

  /**
   *  .
   */
  public void checkContracts(int day) {
    for (Item item : this.items) {
      item.begainPayment(day);

    }
  }

}