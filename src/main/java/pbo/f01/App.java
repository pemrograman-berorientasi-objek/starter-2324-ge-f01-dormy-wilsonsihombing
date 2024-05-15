package pbo.f01;

import java.util.*;

import javax.persistence.*;
import pbo.f01.model.*;

public class App {

  public static void main(String[] args) {
    DriveApp.Initialize();
    DriveApp.clearTableStudent();
    DriveApp.clearTableDorm();

    String str;

    Scanner scanner = new Scanner(System.in);

    while (true) {
      str = scanner.nextLine();

      if (str.equals("---")) {
        break;
      } else {
        String split[] = str.split("#");

        switch (split[0]) {
            case "student-add" :
              DriveApp.addStudent(split[1], split[2], split[3], split[4]);
              break;

            case "dorm-add" :
              DriveApp.addDorm(split[1], Integer.parseInt(split[2]), split[3]);
              break;  

            case "assign":
              DriveApp.assignStudent(split[1], split[2]);
              break;

            case "display-all" :
              DriveApp.displayDorm();
              break;

          default:
            break;
        }
      }

    }
    scanner.close();

  }
}
