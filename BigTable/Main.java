import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io. * ;
import java.nio. * ;
import java.util. * ;

public class Main {

  static int files,n,bestK,index,newS;
  static int[] listlongeur_voitures;
  static ArrayList < ArrayList < Integer >> length;
  public static int[] currX,bestX;
  public static boolean[][] isVisited;



public static void main(String[] args) {
    initialize();

    for (int cases = 0; cases < files; cases++) {
      index = cases;
      n = length.get(cases).size();
      bestK = -1;
      currX = new int[n + 1];
      bestX = new int[n + 1];
      for (int i = 0; i < bestX.length; i++) {
        bestX[i] = -1;
        currX[i] = -1;
      }
      isVisited = new boolean[n + 1][listlongeur_voitures[cases] + 1];
      for (int i = 0; i <= n; i++) {

        for (int j = 0; j < listlongeur_voitures[cases] + 1; j++) {
          isVisited[i][j] = false;
        }
      }

      BacktrackSlove(0, listlongeur_voitures[cases]);
      int car = 0;

      for (int i = 0; i < bestX.length; i++) {
        if (bestX[i] == 0 || bestX[i] == 1) car++;
      }
      System.out.println(car);

      for (int i = 0; i < bestX.length; i++) {
        if (bestX[i] == 0) System.out.println("port");
        else if (bestX[i] == 1) System.out.println("starboard");
      }

      if (cases < files - 1) System.out.println("");

    }

  }

  public static void initialize() {

    bestK = -1;

    Scanner myscanner = new Scanner(System. in );
    files = Integer.parseInt(myscanner.nextLine());
    length = new ArrayList < ArrayList < Integer >> (files);
    listlongeur_voitures = new int[files];

    for (int i = 0; i < files; i++) {
      myscanner.nextLine();
      listlongeur_voitures[i] = Integer.parseInt(myscanner.nextLine()) * 100;
      int temp = Integer.parseInt(myscanner.nextLine());
      length.add(new ArrayList < Integer > ());

      while (temp != 0) {
        length.get(i).add(temp);
        temp = Integer.parseInt(myscanner.nextLine());

      }
    }

    myscanner.close();

  }

  

  public static void BacktrackSlove(int currK, int currS) {
    int longeur_voitures = 0;
    for (int i = 0; i < currK; i++) {
      longeur_voitures = longeur_voitures + length.get(index).get(i);

    }

    if (currK > bestK) {
      bestK = currK;
      for (int i = 0; i < currX.length; i++) {
        bestX[i] = currX[i];

      }
    }

    if (currK < n) {
      if ((currS - length.get(index).get(currK)) >= 0 && ((isVisited[currK + 1][currS - length.get(index).get(currK)])) == false) {
        currX[currK] = 1;
        newS = currS - length.get(index).get(currK);
        isVisited[currK + 1][newS] = true;
        BacktrackSlove(currK + 1, newS);
        isVisited[currK + 1][newS] = true;

      }
      int espace_libre = ((2 * listlongeur_voitures[index]) - currS - longeur_voitures) - length.get(index).get(currK);
      if ( espace_libre >= 0 && isVisited[currK + 1][currS] == false) {
        currX[currK] = 0;
        BacktrackSlove(currK + 1, currS);
        isVisited[currK + 1][currS] = true;
      }

    }

  }

}