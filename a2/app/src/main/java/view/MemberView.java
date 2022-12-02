package view;

import java.util.ArrayList;
import model.domain.Member;

/**
 * represents Member view class.
 */
public class MemberView extends view.MainView {

  /**
     * Represents the member menu actions.
     */
  public static enum MemberEvent {
      AddMember,
      DeleteMember,
      EditMember,
      SimpleListMembers,
      VerboseListMember,
      Back,
      SelectMember
  }

  /**
   * Represents the member menu actions.
   */
  public MemberEvent memberActionView() {
          
    System.out.println("*******************************************");
    System.out.println("****        Member Action           *******");

    System.out.println("*******************************************");
    System.out.println("-Print (add) to add Member ");
    System.out.println("-Print (list ) to list all members");
    System.out.println("-Print (verbose ) to get verbose list for all members");
    System.out.println("-Print (edit ) to update member info");
    System.out.println("-Print (select ) to select one member");
    System.out.println("-Print (delete ) to delete member");
    System.out.println("-Print (back) to go back to main menu");

    System.out.println("___________________________");

    String choice = getStringInput(); 
    choice = choice.toLowerCase();
 
    if (choice.equals("add")) {
      return MemberEvent.AddMember;
         
    } else if (choice.equals("list")) {
      return MemberEvent.SimpleListMembers;

    } else if (choice.equals("verbose")) {
      return MemberEvent.VerboseListMember;
       
    } else if (choice.equals("select")) {
      return MemberEvent.SelectMember;

    } else if (choice.equals("edit")) {
      return MemberEvent.EditMember;
        
    } else if (choice.equals("delete"))  {
      return MemberEvent.DeleteMember;

    } else if (choice.equals("back"))  {
      return MemberEvent.Back;
         
    } else {
      System.out.println("....... Wrong choice try again ");
      return   this.memberActionView();
    }
  }

  /**
   * Gets member object information to create or to edit a member information.
   *
   * @return .
   */
  public model.domain.Member insertMemberIfo() {
    String firstName = "";
    System.out.println(" Enter the first name:  ");
    firstName = getStringInput();
    String lastName = "";
    System.out.println(" Enter the last name:  ");
    lastName = getStringInput();
    String email = "";
    System.out.println("Enter the email:  ");
    email = getStringInput();
    String phoneNumber = "";
    System.out.println("Enter the phone number:  ");
    phoneNumber = getStringInput();
    model.domain.Member newMemberInfo = new model.domain.Member(
        firstName, lastName, email, phoneNumber);

    return newMemberInfo;

  }


  /**
   * Prints message if the member is added or not.
   *
   * @param done .
   */
  public void showMessageMemberIsAddedOrNot(boolean done) {
    System.out.println("   ");
    if (done) {
      System.out.println(" Member is added successfully .");
    } else {
      System.out.println(" The email or the phone number is already used.... .");
    }
    System.out.println("    ");

  }

 

  /**
   * .
   *
   * @param m .
   */
  public void showMessageMemberIsModified(Member m, boolean done) {
    System.out.println("    ");
    if (done) {
      System.out.println(" The member is modified     ");
      System.out.println("Name: " + m.getFirstName() + " " + m.getLastName() + "\n"
           + "Email: " + m.getEmail() + "\n" + " Phone number: " + m.getPhoneNumber());
    } else {
      System.out.println(" The email or the phone number is already used.");

    }
    System.out.println("    ");
    
  }

  /**
   * .
   *
   * @param memberList .
   */
  public void showAllMembers(ArrayList<Member> memberList) {
    System.out.println("____________________________    ");
    System.out.println("_________All Members_________________    ");
    System.out.println("______________________________    ");

    for (Member m : memberList) {
      showMemberInfo(m);
      System.out.println("****************************" + '\n');


    }
    System.out.println("    ");
  }

  /**
   *.
   *
   * @param specificMember .
   */
  public void showSelectedMemberInfo(model.domain.Member specificMember) {
    System.out.println("  ");
    System.out.println("________________________________________________");
    System.out.println("________________________________________________");
    showMemberInfo(specificMember);
    System.out.println("********  Items *******");
    for (int i = 0; i < specificMember.getItems().size(); i++) {
      System.out.println(
          "Id:" + specificMember.getItems().get(i).getId() + '\n'
          + "  Name:" + specificMember.getItems().get(i).getName() + '\n'
          + "  Category :" + specificMember.getItems().get(i).getCategory() + '\n'
          + "  Description:" + specificMember.getItems().get(i).getDescription() + '\n'
          + "  cost:" + specificMember.getItems().get(i).getCost() +   '\n'
          + "  Day of creation:" + specificMember.getItems().get(i).getDayOfCreation() + '\n'
         
          + "********************************" + '\n');
    }
  }

  /**
   *  .
   */
  public void showMemberInfo(Member m) {
    System.out.println(
        "\n"
        + "___________________________________\n" + "\n"
         + " Name:" + m.getFirstName() + "  " + m.getLastName() + "\n"
         + " Credits: " + m.getCredits() + "\n"
         + " Id: " + m.getId() + "\n"
         + " Phone number: " + m.getPhoneNumber() + "\n"
         + " Email: " + m.getEmail() + "\n"
         + " creation day: " + m.getMemberCreationDay() + "\n"
         + " number of all owned items: " + m.getItems().size() + "\n"
         + "\n"
         + "**********************************");
  }  
}