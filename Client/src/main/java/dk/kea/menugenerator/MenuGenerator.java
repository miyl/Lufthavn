// Info:
// userLevel is used to make parts of the menu (the last parts) accessible to only certain users. Multiple user levels are supported, but those entries are always at the end.

package dk.kea.menugenerator;

import static dk.kea.statics.StaticLib.*;

public class MenuGenerator {

  MenuPoint[] mps;

  private boolean running;
  private String menuName;
  private int length;

  public MenuGenerator(String menuName, MenuPoint... mps) {
    this.menuName = menuName;
    this.mps = mps;
    this.length = mps.length;

    running = true;
  }

  public void run() {
    run(length);
  }

  public void run(int userLevel) {
    assert userLevel > 0 && userLevel <= length;
    while (running) {
      // System.out.println(this);
      System.out.println(print(userLevel));
      int choiceNum = getNumericalInputRangeLoop(0, userLevel);
      choice(choiceNum);

      if (running)
        pressEnterToContinue();
      clearScreen();
    }
  }

  // Changed from toString as I needed the userLevel argument
  public String print(int userLevel) {
    int i = 1;
    var str = "";
    for (MenuPoint mp : mps) {
      if (i > userLevel)
        break;
      str += i + ": " + mp.name + "\n";
      i++;
    }

    return "\n" + menuName + "\n" + "--------------\n" + str + "--------------\n" + "0. Exit \n" + "\n"
        + "Make your choice by typing one of the numbers above, followed by Enter. ";
  }

  private void choice(int choiceNum) {
    if (choiceNum > 0) {
        mps[choiceNum - 1].run();
    }
    else System.exit(0);
  }
}
