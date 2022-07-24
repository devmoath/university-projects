import java.sql.*;
import java.util.Scanner;

public class JdbcConnection {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        boolean ok;

        Scanner input = new Scanner(System.in);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:{username}@localhost:1521:xe");
            statement = connection.createStatement();

            System.out.print("Choose a table:\n1) RESEARCHER\n2) RESEARCHAWARD\nYour choice:)");

            int choose = input.nextInt();

            while (choose < 1 || choose > 2) {
                System.out.println("Wrong choose, try again.");
                System.out.print("Choose a table:\n1) RESEARCHER\n2) RESEARCHER WARD\nYour choice:)");
                choose = input.nextInt();
            }

            boolean repeat;

            switch (choose) {
                case 1:
                    ok = true;
                    while (ok) {
                        System.out.println("Table RESEARCHER");
                        System.out.println("1) Insert new record");
                        System.out.println("2) Delete a record");
                        System.out.println("3) Update a record");
                        System.out.println("4) Exit");
                        System.out.print("Choose an operation:)");

                        int operation = input.nextInt();

                        switch (operation) {
                            case 1:
                                repeat = true;

                                while (repeat) {
                                    repeat = false;
                                    System.out.println("RESEARCHER - INSERTION:");
                                    System.out.print("Input First-Name:)");
                                    String firstName = input.next();
                                    System.out.print("Input Middle-Name:)");
                                    String middleName = input.next();
                                    System.out.print("Input Family-Name:)");
                                    String familyName = input.next();
                                    System.out.print("Input Mobile:)");
                                    String mobile = input.next();
                                    System.out.print("Input Email:)");
                                    String email = input.next();
                                    String sqlInsert =
                                        "insert into RESEARCHER values ( '" +
                                        firstName +
                                        "' , '" +
                                        middleName +
                                        "' , '" +
                                        familyName +
                                        "' , " +
                                        mobile +
                                        " , '" +
                                        email +
                                        "' )";
                                    try {
                                        int countInserted = statement.executeUpdate(sqlInsert);
                                        System.out.println(countInserted + " records inserted.");
                                    } catch (SQLException e) {
                                        if (e.getMessage().contains("ORA-00001")) System.out.println(
                                            "There is an error : a primary key value couldn't be duplicated"
                                        ); else if (e.getMessage().contains("ORA-00984")) System.out.println(
                                            "There is an error : a primary key value couldn't be null"
                                        ); else if (e.getMessage().contains("ORA-01400")) System.out.println(
                                            "There is an error : a primary key value couldn't be null"
                                        ); else System.out.println(e.getMessage());
                                    }
                                    System.out.print("Insert a new record (Y/N)?:");
                                    String insert = input.next();
                                    if ((insert.contains("y") || insert.contains("Y")) && insert.length() <= 3) {
                                        repeat = true;
                                    }
                                }
                                break;
                            case 2:
                                repeat = true;
                                while (repeat) {
                                    repeat = false;
                                    System.out.println("RESEARCHER - DELETION:");
                                    System.out.print("Input First-Name:)");
                                    String firstName = input.next();
                                    System.out.print("Input Middle-Name:)");
                                    String middleName = input.next();
                                    System.out.print("Input Family-Name:)");
                                    String familyName = input.next();
                                    String sqlDelete =
                                        "delete from RESEARCHER where FIRST_NAME = '" +
                                        firstName +
                                        "' and MIDDLE_NAME = '" +
                                        middleName +
                                        "' and FAMILLY_NAME = '" +
                                        familyName +
                                        "'";
                                    try {
                                        int countDeleted = statement.executeUpdate(sqlDelete);
                                        System.out.println(countDeleted + " records deleted.");
                                    } catch (SQLException e) {
                                        if (e.getMessage().contains("ORA-02292")) System.out.println(
                                            "There is an error : you can't delete it because integrity constraint violated because child record found"
                                        ); else System.out.println(e.getMessage());
                                    }
                                    System.out.print("Delete another new record (Y/N)?:");
                                    String delete = input.next();
                                    if ((delete.contains("y") || delete.contains("Y")) && delete.length() <= 3) {
                                        repeat = true;
                                    }
                                }
                                break;
                            case 3:
                                repeat = true;
                                while (repeat) {
                                    repeat = false;
                                    System.out.println("RESEARCHER - UPDATE:");
                                    System.out.print("Input First-Name:)");
                                    String firstName = input.next();
                                    System.out.print("Input Middle-Name:)");
                                    String middleName = input.next();
                                    System.out.print("Input Family-Name:)");
                                    String familyName = input.next();
                                    System.out.print(
                                        "Input the attribute's number of the attribute to be updated(4-5):"
                                    );
                                    int number = input.nextInt();
                                    boolean enter = true;
                                    while (enter) {
                                        enter = false;
                                        switch (number) {
                                            case 4:
                                                System.out.print("Enter a new value for Mobile:)");
                                                String newMobile = input.next();
                                                String sqlUpdate =
                                                    "update RESEARCHER set MOBILE = '" +
                                                    newMobile +
                                                    "' where FIRST_NAME = '" +
                                                    firstName +
                                                    "' and MIDDLE_NAME = '" +
                                                    middleName +
                                                    "' and FAMILLY_NAME = '" +
                                                    familyName +
                                                    "'";
                                                try {
                                                    int countUpdated = statement.executeUpdate(sqlUpdate);
                                                    System.out.println(countUpdated + " records updated.");
                                                } catch (SQLException e) {
                                                    if (e.getMessage().contains("ORA-00920")) System.out.println(
                                                        "There is an error : this attribute couldn't be null"
                                                    ); else System.out.println(e.getMessage());
                                                }
                                                System.out.print("Update another new record (Y/N)?:");
                                                String update = input.next();
                                                if (
                                                    (update.contains("y") || update.contains("Y")) &&
                                                    update.length() <= 3
                                                ) {
                                                    repeat = true;
                                                }
                                                break;
                                            case 5:
                                                System.out.print("Enter a new value for Email:)");
                                                String newEmail = input.next();
                                                sqlUpdate =
                                                    "update RESEARCHER set EMAIL = '" +
                                                    newEmail +
                                                    "' where FIRST_NAME = '" +
                                                    firstName +
                                                    "' and MIDDLE_NAME = '" +
                                                    middleName +
                                                    "' and FAMILLY_NAME = '" +
                                                    familyName +
                                                    "'";
                                                try {
                                                    int countUpdated = statement.executeUpdate(sqlUpdate);
                                                    System.out.println(countUpdated + " records updated.");
                                                } catch (SQLException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                                System.out.print("Update another new record (Y/N)?:");
                                                update = input.next();
                                                if (
                                                    (update.contains("y") || update.contains("Y")) &&
                                                    update.length() <= 3
                                                ) {
                                                    repeat = true;
                                                }
                                                break;
                                            default:
                                                enter = true;
                                                System.out.println("There's no attribute for this number, try again.");
                                                System.out.print(
                                                    "Input the attribute's number of the attribute to be updated(4-5):"
                                                );
                                                number = input.nextInt();
                                                break;
                                        }
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("GoodBye!");
                                return;
                            default:
                                System.out.println("Wrong choose, try again.");
                                break;
                        }
                    }
                    break;
                case 2:
                    ok = true;
                    while (ok) {
                        System.out.println("Table RESEARCHAWARD");
                        System.out.println("1) Insert new record");
                        System.out.println("2) Delete a record");
                        System.out.println("3) Update a record");
                        System.out.println("4) Exit");
                        System.out.print("Choose an operation:)");
                        int operation = input.nextInt();
                        switch (operation) {
                            case 1:
                                repeat = true;
                                while (repeat) {
                                    repeat = false;
                                    System.out.println("RESEARCHAWARD - INSERTION:");
                                    System.out.print("Input First-Name:)");
                                    String firstName = input.next();
                                    System.out.print("Input Middle-Name:)");
                                    String middleName = input.next();
                                    System.out.print("Input Family-Name:)");
                                    String familyName = input.next();
                                    System.out.print("Input AWARD_DATE:)");
                                    String awardDate = input.next();
                                    System.out.print("Input AWARD_AMOUNT:)");
                                    int awardAmount = input.nextInt();
                                    System.out.print("Input AWARD_TITLE:)");
                                    String awardTitle = input.next();
                                    String sqlInsert =
                                        "insert into RESEARCHAWARD values ( '" +
                                        firstName +
                                        "' , '" +
                                        middleName +
                                        "' , '" +
                                        familyName +
                                        "' , '" +
                                        awardDate +
                                        "' , " +
                                        awardAmount +
                                        " , '" +
                                        awardTitle +
                                        "' )";
                                    try {
                                        int countInserted = statement.executeUpdate(sqlInsert);
                                        System.out.println(countInserted + " records inserted.");
                                    } catch (SQLException e) {
                                        if (e.getMessage().contains("ORA-00001")) System.out.println(
                                            "There is an error : a primary key value couldn't be duplicated"
                                        ); else if (e.getMessage().contains("ORA-00984")) System.out.println(
                                            "There is an error : a primary key value couldn't be null"
                                        ); else if (e.getMessage().contains("ORA-01400")) System.out.println(
                                            "There is an error : a primary key value couldn't be null"
                                        ); else if (e.getMessage().contains("ORA-01843")) System.out.println(
                                            "There is an error : not a vaild month use name of the month instead of numbers."
                                        ); else if (e.getMessage().contains("ORA-02291")) System.out.println(
                                            "There is an error : integrity constraint violated because parent key not found."
                                        ); else System.out.println(e.getMessage());
                                    }
                                    System.out.print("Insert a new record (Y/N)?:");
                                    String insert = input.next();
                                    if ((insert.contains("y") || insert.contains("Y")) && insert.length() <= 3) {
                                        repeat = true;
                                    }
                                }
                                break;
                            case 2:
                                repeat = true;
                                while (repeat) {
                                    repeat = false;
                                    System.out.println("RESEARCHAWARD - DELETION:");
                                    System.out.print("Input First-Name:)");
                                    String firstName = input.next();
                                    System.out.print("Input Middle-Name:)");
                                    String middleName = input.next();
                                    System.out.print("Input Family-Name:)");
                                    String familyName = input.next();
                                    System.out.print("Input AWARD_DATE:)");
                                    String awardDate = input.next();
                                    String sqlDelete =
                                        "delete from RESEARCHAWARD where FIRST_NAME = '" +
                                        firstName +
                                        "' and MIDDLE_NAME = '" +
                                        middleName +
                                        "' and FAMILLY_NAME = '" +
                                        familyName +
                                        "' and AWARD_DATE = '" +
                                        awardDate +
                                        "'";
                                    try {
                                        int countDeleted = statement.executeUpdate(sqlDelete);
                                        System.out.println(countDeleted + " records deleted.");
                                    } catch (SQLException e) {
                                        if (e.getMessage().contains("ORA-01858")) System.out.println(
                                            "AWARD_DATE attribute can't be a number."
                                        ); else System.out.println(e.getMessage());
                                    }
                                    System.out.print("Delete another new record (Y/N)?:");
                                    String delete = input.next();
                                    if ((delete.contains("y") || delete.contains("Y")) && delete.length() <= 3) {
                                        repeat = true;
                                    }
                                }
                                break;
                            case 3:
                                repeat = true;
                                while (repeat) {
                                    repeat = false;
                                    System.out.println("RESEARCHAWARD - UPDATE:");
                                    System.out.print("Input First-Name:)");
                                    String firstName = input.next();
                                    System.out.print("Input Middle-Name:)");
                                    String middleName = input.next();
                                    System.out.print("Input Family-Name:)");
                                    String familyName = input.next();
                                    System.out.println("Input Award-Date:)");
                                    String awardDate = input.next();
                                    System.out.print(
                                        "Input the attribute's number of the attribute to be updated(5-6):"
                                    );
                                    int number = input.nextInt();
                                    boolean enter = true;
                                    while (enter) {
                                        enter = false;
                                        switch (number) {
                                            case 5:
                                                System.out.println("Enter a new value for Award-Amount:)");
                                                String newAwardAmount = input.next();
                                                String sqlUpdate =
                                                    "update RESEARCHER set MOBILE = " +
                                                    newAwardAmount +
                                                    " where '" +
                                                    firstName +
                                                    "' and '" +
                                                    middleName +
                                                    "' and '" +
                                                    familyName +
                                                    "' and '" +
                                                    awardDate +
                                                    "'";
                                                try {
                                                    int countUpdated = statement.executeUpdate(sqlUpdate);
                                                    System.out.println(countUpdated + " records updated.");
                                                } catch (SQLException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                                System.out.print("Update another new record (Y/N)?:");
                                                String update = input.next();
                                                if (
                                                    (update.contains("y") || update.contains("Y")) &&
                                                    update.length() <= 3
                                                ) {
                                                    repeat = true;
                                                }
                                                break;
                                            case 6:
                                                System.out.println("Enter a new value for � Award-Title:)");
                                                String newAwardTitle = input.next();
                                                sqlUpdate =
                                                    "update RESEARCHER set MOBILE = " +
                                                    newAwardTitle +
                                                    " where '" +
                                                    firstName +
                                                    "' and '" +
                                                    middleName +
                                                    "' and '" +
                                                    familyName +
                                                    "' and '" +
                                                    awardDate +
                                                    "'";
                                                try {
                                                    int countUpdated = statement.executeUpdate(sqlUpdate);
                                                    System.out.println(countUpdated + " records updated.");
                                                } catch (SQLException e) {
                                                    System.out.println(e.getMessage());
                                                }
                                                System.out.print("Update another new record (Y/N)?:");
                                                update = input.next();
                                                if (
                                                    (update.contains("y") || update.contains("Y")) &&
                                                    update.length() <= 3
                                                ) {
                                                    repeat = true;
                                                }
                                                break;
                                            default:
                                                enter = true;
                                                System.out.println("There's no attribute for this number, try again.");
                                                System.out.print(
                                                    "Input the attribute's number of the attribute to be updated(5-6):"
                                                );
                                                number = input.nextInt();
                                                break;
                                        }
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("GoodBye!");
                                return;
                            default:
                                System.out.println("Wrong choose, try again.");
                                break;
                        }
                        break;
                    }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
