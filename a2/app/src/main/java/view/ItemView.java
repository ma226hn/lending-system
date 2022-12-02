package view;

import model.domain.CategoryType.Category;
import model.domain.Item;



/**
 * Represent Item class.
 */
public class ItemView extends view.MainView {

  

  /**
     * Represents the item menu actions.
     */
  public static enum ItemEvent {
        AddItem,
        listItems,
        editItem,
        Contract,
        DeleteItem,
        Back
     
  }

 

  /**
     * Represents the member menu actions.
     */

  public ItemEvent itemActionView() {
            
    System.out.println("*******************************************");
    System.out.println("****        Item Action             *******");

    System.out.println("*******************************************");

    System.out.println("-Print (add) for  add Item ");
    System.out.println("-Print (list ) for  list all items");
    System.out.println("-Print (edit ) for  changeItem info");
    System.out.println("-Print (contract ) for  write contract");
    System.out.println("-Print (delete ) for  deleteItem");

    System.out.println("-Print (back) to go back to the main menu");

    System.out.println("___________________________");

    String choice =  getStringInput(); 
    choice = choice.toLowerCase();
   
    if (choice.equals("add")) {
      return ItemEvent.AddItem;
           
    } else if (choice.equals("list")) {
      return ItemEvent.listItems;
              
    } else if (choice.equals("edit")) {
      return ItemEvent.editItem;
         
    } else if (choice.equals("contract")) {
      return ItemEvent.Contract;

    } else if (choice.equals("delete"))  {
      return ItemEvent.DeleteItem;
            
    } else if (choice.equals("back"))  {
      return ItemEvent.Back;
           
    }
    System.out.println("....... Wrong choice try again ");
    return   this.itemActionView();
           
        
  }
 
  
  public void wrongInsert() {
  }

 
  /**
   * Gets item info to create an item.
   *
   * @param day .
   * @return .
   */
  public Item insertNewItemInfo(int day) {
    String name = "";
    System.out.println(" Enter the name of item:  ");
    name = getStringInput();
    String description = "";
    System.out.println(" Enter the description of item:  ");
    description = getStringInput();

   
    
    int cost = 0;
    System.out.println(" enter the cost of item:  ");
    cost = getIntInput();
    model.domain.Item newItemInfo = new model.domain.Item(name, description,
        getCategoryChoice(), cost, day);

    return newItemInfo;
  }

  /**
   * Gets item info to edit an item..
   */
  public Item insertToEditItemInfo() {
    String name = "";
    System.out.println(" Enter the name of item:  ");
    name = getStringInput();

    String description = "";
    System.out.println(" Enter the new description of item:  ");
    description = getStringInput();

    
    int cost = 0;
    System.out.println(" Enter the cost of item:  ");
    cost = getIntInput();

    model.domain.Item newItemInfo = new model.domain.Item(name, description,
         getCategoryChoice(), cost);

    return newItemInfo;
  }




  /**
   * Choose item.
   */
  public int chooseItem() {  
    System.out.println("   ");
    System.out.println(" Enter the ID of the item ");
    int itemIndex = getIntInput();
    System.out.println("   ");
    

    return itemIndex;
  }

  /**
 * Not found message.
 */
  public void itemNotFound() {  
    System.out.println("   ");
    System.out.println(" the item not Found !!!!!");
    System.out.println("   ");

  }

  /**
   * Gets category of the item.
   *
   * @return .
   */
  private Category getCategoryChoice() {
    
    System.out.println("    "); 
    String choice = ""; 
   
    System.out.println("Insert type of the item");
    System.out.println(
        "Choose one of the types: Tool, Vehicle, Game,  Toy,  Sport ,Other "
    );

    choice = getStringInput().toLowerCase();
    if (choice.equals("tool")) {
      return Category.tool;
    } else if (choice.equals("vehicle")) {
      return Category.vehicle;
    } else if (choice.equals("game")) {
      return  Category.game;
    } else if (choice.equals("toy")) {
      return Category.toy;
    } else if (choice.equals("sport")) {
      return  Category.sport;
    } else if (choice.equals("other")) {
      return Category.other;
        
    } else {

      System.out.println(" invalid value try again !!!!");
      return getCategoryChoice();
    }

    
  }

  /**
   * Gets the contract first day.
   */
  public int insertStartDay(int day) {
    System.out.println("  ");
    System.out.println(" insert start day ");
    int startDay =  getIntInput();
    if (startDay < day) {
      System.out.println(" The start day should be after the current day try again :");
      startDay = insertStartDay(day);

    }
    return startDay;

  }

  /**
   * Gets the contract end day.
   *
   * @param startDay .
   * @return .
   */
  public int insertEndDay(int startDay) {
    System.out.println("  ");
    System.out.println(" insert end day ");
    int endDay =  getIntInput();
    if (startDay > endDay) {
      System.out.println(" The end day should be after the start day try again :");
      endDay = insertEndDay(startDay);

    }
    return endDay;
  }

  /**
   *  Prints when the contract is not created.
   */
  public void unsuccessfulMessage() {
    System.out.println("");
    System.out.println("..........Something went wrong !!!");
    System.out.println("The item is already reserved or credits are not enough");
  }
}
