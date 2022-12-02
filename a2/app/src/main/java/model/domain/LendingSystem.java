package model.domain;

import java.util.ArrayList;
import java.util.Random;
import model.data.Data;

/**
 * Responsible for the program time concept
 * and to do the .
 */
public class LendingSystem {
  int advancedDay = 0;
  private ArrayList<Member> membersList = new ArrayList<>();
  private static final Random RANDOM = new Random();
  int itemsId = 0;

  /**
   *  Constructor.
   */
  public LendingSystem() {
    this.membersList = new ArrayList<>();
    this.advancedDay = 0;
    Data data = new Data();
    data.load(this);
  }

  
  public int getAdvancedDay() {
    return this.advancedDay;
  }
  
  /**
   *  Advance the current day.
   */
  public void increaseDay() {
    this.advancedDay += 1;
    for (Member member : this.membersList) {
      member.checkContracts(this.advancedDay);
    }
  }

  /**
   * Returns all the members.
   */
  public ArrayList<Member> getMemberList() {
    ArrayList<Member> copy = new ArrayList<>();
    copy.addAll(this.membersList);
    return copy;
  }
 

  /**
   * Add new member to the system.
   */
  public boolean addNewMember(Member newMember) {
    int emailIndex = this.searchEmail(newMember.getEmail());

    int phoneIndex = this.searchPhoneNumber(newMember.getPhoneNumber());

    if (this.phoneAndEmailIsUniq(phoneIndex, emailIndex)) {
      Member registeredMember = new Member(newMember.getFirstName(), 
           newMember.getLastName(), newMember.getEmail(), newMember.getPhoneNumber(),
            this.getAdvancedDay(), this.generateId(), newMember.getCredits());

      membersList.add(registeredMember);

      return true;
    }
    return false;

  }

  /**
   * Edit a member.
   */
  public boolean changeMemberInfo(Member member, int memberIndex) {
    int emailIndex = this.searchEmail((member.getEmail()));

    int phoneIndex = this.searchPhoneNumber(member.getPhoneNumber());
    
    if (this.phoneAndEmailIsUniq(phoneIndex, emailIndex)) {
      membersList.get(memberIndex).modifyMemberInformation(member);
      return true;
    }
    return false;
  }

  /**
   * Find the matching memberEmail in the list.
   *
   * @param emailInput .
   * 
   * @return returns the index of the matching memberEmail or -1 if not found.
   */
  public int searchEmail(String emailInput) {
    Member[] memberArray = this.membersList.toArray(new Member[membersList.size()]);
    int i;
    for (i = 0; i < memberArray.length; i++) {
      if (memberArray[i].getEmail().equals(emailInput)) {
        return (i);
      }
    }
    return -1;
  }

  /**
   * returns if the phone number is in the system or not.
   *
   * @param phoneNumberInput .
   * @return returns the index of the matching phoneNumber or -1 if not found.
   */
  public int searchPhoneNumber(String phoneNumberInput) {
    Member[] memberArray = this.membersList.toArray(new Member[membersList.size()]);
    int i;
    for (i = 0; i < memberArray.length; i++) {
      if (memberArray[i].getPhoneNumber().equals(phoneNumberInput)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Checks that no other members have the same email or phone number.
   *
   * @param phoneIndex .
   * @param emailIndex .
   * @return
   */
  private boolean phoneAndEmailIsUniq(int phoneIndex, int emailIndex) {
    if ((emailIndex == -1) && (phoneIndex == -1)) {
      return true;
    }
    return false;
  }

  
  /**
   * Set member id.
   */
  private String generateId() {
    String generatedString = "";
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 6;
    boolean isNotUsed = true;
    do {
      generatedString = RANDOM.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
      isNotUsed = this.isUniq(generatedString);
    } while (!(isNotUsed));
    return generatedString;
  }

  /**
   * Check the id.
   * true when is not used
   */
  private boolean isUniq(String str) {
    Member[] memberArray = this.membersList.toArray(new Member[this.membersList.size()]);
    for (int i = 0; i < memberArray.length; i++) {
      if (memberArray[i].getId().equals(str)) {
        return false;
      }
    }
    return true;
  }


  public Member getSpecificMember(int memberIndex) {
    return this.membersList.get(memberIndex);
  }

  public void deleteMember(int index) {
    this.membersList.remove(this.membersList.get(index));
  }

  /**
   *  add new item to the member.
   */
  public void addItemToMember(int index, Item item) {
    this.membersList.get(index).addNewItem(item, this.itemsId);
    this.itemsId++;
  }

  public void editMemberItem(int memberIndex, int itemId, Item item) {
    this.membersList.get(memberIndex).changeSpecificItemInfo(itemId, item);
  }

  public void deleteMemberItem(int memberIndex, int itemId) {
    this.membersList.get(memberIndex).deleteSpecificItem(itemId);
  }
   
  /**
   *  Returns all the item.
   */
  public ArrayList<Item> getAllSystemItems() {
    ArrayList<Item> allItems = new ArrayList<>();
    for (Member m : this.membersList) {
      allItems.addAll(m.getItems());
    }
    return allItems;
  }

  /**
   * Create new contract after checking the item is available and check if the owner borrows
   *  or its another member and checks if member has the credits necessary
   *  and the create the contract .
   */
  public boolean createNewContract(int borrowerIndex, int ownerIndex, int itemIndex,
      int startDay, int endDay) {

    Item itemToBorrow = membersList.get(ownerIndex).getItems().get(itemIndex);

    int totalCost = this.calculateTotalCost(startDay, endDay, itemToBorrow.getCost());
  
    // if the borrower has mor credits than the total cost
    // and the item is available CREATE THE CONTRACT.
    if ((membersList.get(borrowerIndex).getCredits() > totalCost)
        && (itemToBorrow.isAvailable(startDay, endDay))) {

      Contract contract = new Contract(itemToBorrow, startDay, endDay,
          membersList.get(ownerIndex), membersList.get(borrowerIndex));

      itemToBorrow.addContract(contract);

      itemToBorrow.updateReservedDays(startDay, endDay); // make the item not available 

      return true;
    }

    return false;
  }

  private int calculateTotalCost(int startDay, int endDay, int cost) {
    int fee = 0;
    for (int i = startDay; i <= endDay; i++) {
      fee = fee + cost;
    }
    return fee;
  } 
  
}



 
 

