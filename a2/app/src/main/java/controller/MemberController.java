package controller;

import model.domain.Member;

/**
 * Represents the member controller class.
 */

public class MemberController {
 
  view.MemberView memberView = new view.MemberView();
  
  /**
   * Represents the member controller class.
   */
  public void memberMenu(model.domain.LendingSystem lendBorrowSys) {
    boolean back = false;
    view.MemberView.MemberEvent option = memberView.memberActionView();
    switch (option) {
      case AddMember: {
        addMember(lendBorrowSys);
        break;
      }
      case DeleteMember:
        deleteMember(lendBorrowSys);
        break;
      case SimpleListMembers:
        memberView.showAllMembers(lendBorrowSys.getMemberList());
        break;
      case EditMember: {
        editMemberInfo(lendBorrowSys);
        break;
      }
      case VerboseListMember:
        verboseList(lendBorrowSys);
        break;
      case SelectMember:
        selectMember(lendBorrowSys);
        break;
      case Back:
        back = true;
        break;
      default:
        memberView.wrongInsert();
        break;
    }
    if (!back) {
      memberMenu(lendBorrowSys);
    }
  }


  /**
   * Dele member.
   */
  private void addMember(model.domain.LendingSystem lendBorrowSys) {
    boolean done = false;
    model.domain.Member m; 
    do {
      m = memberView.insertMemberIfo();
      done = lendBorrowSys.addNewMember(m);
      memberView.showMessageMemberIsAddedOrNot(done);
    } while (!done);
  }

  private void deleteMember(model.domain.LendingSystem lendBorrowSys) {
    int index = this.getMemberIndex(lendBorrowSys);
    if (index == -1) {
      memberView.memberNotFound();
    } else {
      lendBorrowSys.deleteMember(index);
      memberView.doneMessage();
    }
  }

  /**
   * View list of the members.
   */
  private void editMemberInfo(model.domain.LendingSystem lendBorrowSys) {
    int memberIndex = this.getMemberIndex(lendBorrowSys);
    if (memberIndex== -1) {
      memberView.memberNotFound();
    } else {
      model.domain.Member m = memberView.insertMemberIfo();
      Boolean done = lendBorrowSys.changeMemberInfo(m, memberIndex); 
     
      memberView.showMessageMemberIsModified(m, done);
    }
  }

  

  /**
   * View verbose list of member.
   */
  private void verboseList(model.domain.LendingSystem lendBorrowSys) {
    for (Member m : lendBorrowSys.getMemberList()) {
      memberView.showMemberInfo(m);
      memberView.showItems(m.getItems());   
    }
  }


  /**
   * Select one member.
   */

  private void selectMember(model.domain.LendingSystem lendBorrowSys) {
    
    int memberIndex = this.getMemberIndex(lendBorrowSys);
    if (memberIndex == -1) {
      memberView.memberNotFound();
    } else {
      memberView.showSelectedMemberInfo(lendBorrowSys.getSpecificMember(memberIndex));
    }
  }

  /**
   * Returns member index or -1 if not found.
   *
   * @param lendBorrowSys .
   * @return .
   */
  private int getMemberIndex(model.domain.LendingSystem lendBorrowSys) {
    String email = memberView.insertEmail();
    int memberIndex = lendBorrowSys.searchEmail(email);
    return memberIndex;
  } 
}
