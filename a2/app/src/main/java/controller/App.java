package controller;

import model.domain.LendingSystem;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    model.domain.LendingSystem lendBorrowSys = new LendingSystem();
    view.MainView menu = new view.MainView();
    boolean run = true;
    while (run) {  
      view.MainView.SystemEvent  choice =  menu.show();
      controller.MemberController memberControlPanel = new controller.MemberController();
      controller.ItemController itemControlPanel = new controller.ItemController();

      switch (choice) {
        case ItemAction:
          itemControlPanel.itemMenu(lendBorrowSys);
          break;
        case MemberAction:
          memberControlPanel.memberMenu(lendBorrowSys);
          break;
        case AdvancedDay: {
          lendBorrowSys.increaseDay();
          menu.increaseAdvancedDay(lendBorrowSys.getAdvancedDay());
          break;
        }
        case Quit:
          run = false;
          break;
        default:
          run = false;
          break;
      }
    }
  }  
}
