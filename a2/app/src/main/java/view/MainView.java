package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.domain.Contract;

/**
 * Represents.
 */
public class MainView {
    
  public  Scanner input = new Scanner(System.in, "UTF8");

  
  /**
     * Represents the main menu actions.
     */
  public static enum SystemEvent  {
        MemberAction,
        ItemAction,
        AdvancedDay,
        Quit
  }
   
  
 
  
  /**
     * Represents the member menu actions.
     */
  public SystemEvent show() {
       
    System.out.println("    ");  
    System.out.println("********* The Stuff Lending System***************");
    System.out.println("Print (M) for  Member Actions");
    System.out.println("Print (I) for  Item Actions");
    System.out.println("Print (D) to  increase  the advanced day");
    System.out.println("Print (Q) to Quit");
    System.out.println("___________________________");

    char choice =  getStringInput().charAt(0);
    
    if (Character.toLowerCase(choice) == 'q') {
      return SystemEvent.Quit;
           
    } else if (Character.toLowerCase(choice) == 'm')  {
      return SystemEvent.MemberAction;
           
    } else if (Character.toLowerCase(choice) == 'i') {
      return SystemEvent. ItemAction;
         
    }   else if (Character.toLowerCase(choice) == 'd') {
      return SystemEvent.AdvancedDay;
         
    } 
    System.out.println("....... Wrong choice try again ");
    return   this.show();
           
        
  }

 

  /**
  *  .
  */
  public String getStringInput() {
    String inputString;
    inputString = this.input.next();
    return  inputString;
    
  }

  /**
   * Gets integer input.
   */
  public int getIntInput() {
    String str = this.input.next();
    int intNumber = 0;
    boolean tryAgin = true;
    while (tryAgin) {
      try {
        intNumber = Integer.parseInt(str);
        tryAgin = false;
      } catch (Exception e) {
        System.out.println(" invalid value enter number value .");
        str = this.input.next();
        tryAgin = true;
      }
    }
    return intNumber;
  }

  /**
   * .
   *
   * @return .
   */
  public  String insertEmail() {
    
    System.out.println("   ");

    System.out.println("Enter the email of the member ");
    
    String email = getStringInput();
    return email;
  }

  /**
   * Gets the email form the user.
   *
   * @return the email.
   */
  public  String insertBorrowerEmail() {
    
    System.out.println("   ");

    System.out.println("Enter the email of the member who wants to borrow ");
    
    String email = getStringInput();
    return email;
  }

  /**
   * .
   */
  public void memberNotFound() {
    System.out.println((""));
    System.out.println("OBS :");
    System.out.println("This email does not exist!!");
  }

  
 
  /**
   * .
   */
  public void wrongInsert() {
    System.err.println("");
    System.out.println(("!!!   OBS:  wrong choice try again "));
    System.out.println("  ");
  }

  /**
   * Done.
   */
  public void doneMessage() {
    System.out.println("   ********************          ");
    System.out.println("    The Process Is Done     ");
    System.out.println("   ********************          ");
  }

  /**
   * Returns item details for a specific details.
   */
  public void showItems(ArrayList<model.domain.Item> items) {
    System.out.println(" Items  :");
    for (int i = 0; i < items.size(); i++) {
      System.out.println(
          "*********************************" + '\n'
          + "Id:" + items.get(i).getId() + '\n'
          + "  Name:" + items.get(i).getName() + '\n'
          + "  Category :" + items.get(i).getCategory() + '\n'
          + "  Description:" + items.get(i).getDescription() + '\n'
          + "  cost:" + items.get(i).getCost() +   '\n'
          + "  Day of creation:" + items.get(i).getDayOfCreation() + '\n'
          + '\n'
          + "*************" + '\n'
          + " contracts  :" + '\n'
          + "**************");
      for (Contract con : items.get(i).getContractsList()) {
        System.out.println(
            " The borrower: " + con.getBorrower().getFirstName() 
            + "  " + con.getBorrower().getLastName() + '\n'
             + " Contract period is from day: " + con.getStartDay() 
             + ", to day: " + con.getEndDay() + '\n'
             + "\n"
             + "************************************" + '\n');
      }    
    }
  }

  public void increaseAdvancedDay(int day) {
    System.out.println("  ");
    System.out.println(" the current day is " + day);
  }
  
 
}