package controller;

import java.util.ArrayList;
import model.domain.Item;
import model.domain.LendingSystem;

/**
 * Represents the item controller class.
 */

public class ItemController {


  view.ItemView itemView = new view.ItemView();

  /**
  * Main view.
  */
  public void itemMenu(LendingSystem lendBorrowSys) {

    boolean back = false;
    view.ItemView.ItemEvent option = itemView.itemActionView();
    switch (option) {
      case AddItem: {
        addItem(lendBorrowSys);
        break;
      }

      case listItems:
        listMemberItems(lendBorrowSys);
        break;
     
      case editItem: {
        editItem(lendBorrowSys);
        break;
      }

      case Contract:
        addContract(lendBorrowSys);
        break;

      case DeleteItem:
        deleteItem(lendBorrowSys);
        break;

      case Back:
        back = true;
        break;

      default:
        itemView.wrongInsert();
        break;
    }
    if (!back) {
      itemMenu(lendBorrowSys);
    }
  }

  private void addItem(model.domain.LendingSystem lendBorrowSys) {
    int memberIndex = this.getMemberIndex(lendBorrowSys);
    if (memberIndex == -1) {
      itemView.memberNotFound();
    } else {
      model.domain.Item item = itemView.insertNewItemInfo(lendBorrowSys.getAdvancedDay());
      lendBorrowSys.addItemToMember(memberIndex, item);
      itemView.doneMessage();
    }
  }

  private void listMemberItems(model.domain.LendingSystem lendBorrowSys) {
    int memberIndex = this.getMemberIndex(lendBorrowSys);
    if (memberIndex == -1) {
      itemView.memberNotFound();
    } else {
      model.domain.Member selectedMember = lendBorrowSys.getSpecificMember(memberIndex);
      itemView.showItems(selectedMember.getItems());
    }
  }

  private void editItem(LendingSystem lendBorrowSys) {
    int memberIndex = this.getMemberIndex(lendBorrowSys);

    if (memberIndex == -1) {
      itemView.memberNotFound();
    } else {
      model.domain.Member member = new model.domain.Member(
          lendBorrowSys.getSpecificMember(memberIndex));
      itemView.showItems(member.getItems());    
      int itemId = itemView.chooseItem();
      if (member.searchItem(itemId)) {
        model.domain.Item newItem = itemView.insertToEditItemInfo();
        lendBorrowSys.editMemberItem(memberIndex, itemId, newItem);
        itemView.doneMessage();
      } else {
        itemView.itemNotFound();
      }
    }
  }

  private void addContract(LendingSystem lendBorrowSys) {
    ArrayList<Item> items = new ArrayList<>(lendBorrowSys.getAllSystemItems()); 
    itemView.showItems(items);
    int startDay = 0;
    int endDay = 0; 
    int itemId;
    do { 
      itemId = itemView.chooseItem();
      
      startDay = itemView.insertStartDay(lendBorrowSys.getAdvancedDay());
    
      endDay = itemView.insertEndDay(startDay);
    } while (startDay > endDay);
    String borrowerEmail = itemView.insertBorrowerEmail();
    int borrowerIndex = lendBorrowSys.searchEmail(borrowerEmail);
    int[] ownerAndItemIndexes = this.getOwnerAndItemIndexes(lendBorrowSys, itemId);
    int ownerIndex = ownerAndItemIndexes[0];
    int itemIndex = ownerAndItemIndexes[1];
    if (borrowerIndex == -1) {
      itemView.memberNotFound();
    } else {
      if (lendBorrowSys.createNewContract(borrowerIndex, ownerIndex, itemIndex, startDay, endDay)) {
        itemView.doneMessage();
      } else {
        itemView.unsuccessfulMessage();
      }
    }
  }


  private void deleteItem(LendingSystem lendBorrowSys) {
   
    int memberIndex = this.getMemberIndex(lendBorrowSys);
    if (memberIndex == -1) {
      itemView.memberNotFound();
    } else {
      model.domain.Member member = new model.domain.Member(
          lendBorrowSys.getSpecificMember(memberIndex));
      itemView.showItems(member.getItems());    
      int itemId = itemView.chooseItem();
      if (member.searchItem(itemId)) {
        lendBorrowSys.deleteMemberItem(memberIndex, itemId);
        itemView.doneMessage();
      } else {
        itemView.itemNotFound();
      }
    }
  }

  /**
   * Returns the member index or -1 if not found.
   *
   * @param lendBorrowSys .
   * @return .
   */
  private int getMemberIndex(LendingSystem lendBorrowSys) {
    String memberEmail = itemView.insertEmail();
    int memberIndex = lendBorrowSys.searchEmail(memberEmail);
    return memberIndex;
  } 
  
  private int[] getOwnerAndItemIndexes(LendingSystem lendBorrowSys, int itemId) {
    int[] ownerAndItemIndex = {0, 0};
    for (int i = 0; i < lendBorrowSys.getMemberList().size(); i++) {
      for (int j = 0; j < lendBorrowSys.getMemberList().get(i).getItems().size(); j++) {
        if (lendBorrowSys.getMemberList().get(i).getItems().get(j).getId() == itemId) {
          ownerAndItemIndex[0] = i;
          ownerAndItemIndex[1] = j;
        }
      }
    }
    return ownerAndItemIndex;
  }
}
