package model.domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represent Contract class.
 */
public class Contract {
  private Item item;
  private int startDay;
  private int endDay;
  private Member owner;
  private Member borrower;


  /**
   * Contract constructor.
   *
   * @param startDay .
   * @param endDay .
   * @param item .
   * @param owner .
   * @param borrower .
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "One shared scanner object.")
  public Contract(Item item, int startDay, int endDay,  Member owner, Member borrower) {
    this.item = item;
    this.startDay = startDay;
    this.endDay = endDay;
    this.owner = owner;
    this.borrower = borrower;
  }



  public int getStartDay() {
    return this.startDay;
  }

  public int getEndDay() {
    return this.endDay;
  }
  
  


  public Item getItem() {
    Item copyItem = new Item(this.item);
    return copyItem;
  }

  public Member getOwner() {
    Member copyMember = new Member(this.owner);
    return copyMember;
  }

  public Member getBorrower() {
    Member copyBorrower = new Member(this.borrower);
    return copyBorrower;
  }


  /**
   *  Add credits to the owner and subtracts credits from the borrower
   * As much as the value of the item of the contract.
   */
  public void pay() {
    int totalCost = this.item.getCost() * (this.endDay - this.startDay + 1);
    this.owner.updateCreditsValue(totalCost);
    this.borrower.updateCreditsValue(-totalCost);
  }

}
