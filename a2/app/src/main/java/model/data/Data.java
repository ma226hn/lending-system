package model.data;


import model.domain.CategoryType.Category;
import model.domain.Item;
import model.domain.LendingSystem;
import model.domain.Member;


/**
 * Class  for persistence .
 */
public class Data {
  /**
   *  Hard coded "loading" of some members with items.
   *
   * @param lendBorrowSys .
   */
  public void load(LendingSystem lendBorrowSys) {
    Member memberOne = new Member("Shirin", "Meirkhan", "sh@sh", "0725594352", 0, 300);
    lendBorrowSys.addNewMember(memberOne);


    Member memberTwo = new Member("Mnar", "Ali",
        "ma@ma", "0725594350", 0, 100);
    lendBorrowSys.addNewMember(memberTwo);
    
    Member memberThree = new Member("Leo", "Sawah",
        "l@l", "07254350", 0, 100);
    lendBorrowSys.addNewMember(memberThree);



    Item itemOne = new Item("Item one", "Old Nike",
        Category.sport, 50, lendBorrowSys.getAdvancedDay());
    lendBorrowSys.addItemToMember(0, itemOne);
    
    Item itemTwo = new Item("Item two", "Old toshiba",
        Category.tool, 10, lendBorrowSys.getAdvancedDay());
    lendBorrowSys.addItemToMember(0, itemTwo);

    lendBorrowSys.createNewContract(2, 0, 1, 5, 7);
    
  }
}
