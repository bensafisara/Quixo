package com.example.quixo;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TEST {
  /*  public static final Random RANDOM = new Random();
    public static final Random RANDOM1 = new Random();
    public static void main(String[] args) {
        AI_Player PlayerIaInstance = new AI_Player();
        Plateau p = new Plateau();
        PlayerHumain playerHumainInstance = new PlayerHumain();

        Scanner scn = new Scanner(System.in);

        System.out.println("turn human tap 1 (O) / turn IA  2 (X) : ");
        boolean sara = true;
         int choice = 2;
         if(choice==2){
                     ArrayList<Position> myArraySource = new ArrayList<Position>();
                    myArraySource = p.CasesJouablesSource(choice);
                    for (int i=0 ; i< myArraySource.size();i++){
                        System.out.println(myArraySource.get(i));

                    }
                     int randomIndex = RANDOM.nextInt(myArraySource.size());
                     System.out.println(randomIndex);

                     ArrayList<Position> myArrayDest = new ArrayList<Position>();
                     myArrayDest = p.CasesJouablesDestination(5,myArraySource.get(randomIndex));
                     for (int i=0 ; i< myArrayDest.size();i++){
                         System.out.println("affichage"+myArrayDest.get(i));
                     }
                     int randomIndex1 = RANDOM1.nextInt(myArrayDest.size());
             playerHumainInstance.deplacement(myArraySource.get(randomIndex),myArrayDest.get(randomIndex1),choice,p.game);
                     myArrayDest.clear();
                     myArraySource.clear();
                     p.printBoard();
                     choice =1;}


         while(sara) {//game is not over
             boolean continuer = true;

             do {

                 if (continuer == false) {
                     System.out.println("votre tour est fini");
                 }

                 System.out.println("votre tour ");
                 ArrayList<Position> myArraySource1 = new ArrayList<Position>();
                 myArraySource1 = p.CasesJouablesSource(1);
                 for (int i = 0; i < myArraySource1.size(); i++) {
                     System.out.println("vous pouvez choisir de déplacer cette case " + myArraySource1.get(i));
                 }
                 System.out.println("entrer la cas source souhaité   X Y ");
                 Position choixHumainSource = new Position(scn.nextInt(), scn.nextInt());
                 ArrayList<Position> myArrayDest1 = new ArrayList<Position>();
                 myArrayDest1 = p.CasesJouablesDestination(5, choixHumainSource);
                 for (int i = 0; i < myArrayDest1.size(); i++) {
                     System.out.println("vous pouvez choisir coome destination cette case" + myArrayDest1.get(i));
                 }
                 Position choixHumainDest = new Position(scn.nextInt(), scn.nextInt());
                 playerHumainInstance.deplacement(choixHumainSource, choixHumainDest, 1, p.game);
                 continuer = false;//user can't move
                 // Vider le ArrayList
                 myArrayDest1.clear();
                 myArraySource1.clear();
                 for (int i = 0; i < 5; i++) {
                     for (int j = 0; j < 5; j++) {

                         System.out.println(p.game[i][j] + "");
                     }
                     System.out.println();
                 }
                 System.out.println();

                 choice = choice ==1 ? 2 : 1;

             } while (continuer == false);

             if (p.isSolved() != 0) {
                 break;
             }
             System.out.println("turn IA 2 ");
             choice = choice ==1 ? 2 : 1;

             Position[] MeilleurDeplacementAI = PlayerIaInstance.jouerIA(choice);
             PlayerIaInstance.deplacement(MeilleurDeplacementAI[0], MeilleurDeplacementAI[1], choice, p.game);

             for (int i = 0; i < 5; i++) {
                 for (int j = 0; j < 5; j++) {

                     System.out.println(p.game[i][j] + "");
                 }
                 System.out.println();
             }
             System.out.println();
         }


            if(p.isSolved()==10){
                System.out.println("human losed!");
            }else {
                if(p.isSolved()==-10){
                    System.out.println("human won!");}}



         }
*/

}






