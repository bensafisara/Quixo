package com.example.quixo.Controller;
import com.example.quixo.Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Controller  {
    //Déclaration
    /****************************************/
    @FXML
    private Button bt00;
    @FXML
    private Button bt01;
    @FXML
    private Button bt02;
    @FXML
    private Button bt03;
    @FXML
    private Button bt04;
    @FXML
    private Button bt10;
    @FXML
    private Button bt11;
    @FXML
    private Button bt12;
    @FXML
    private Button bt13;
    @FXML
    private Button bt14;
    @FXML
    private Button bt20;
    @FXML
    private Button bt21;
    @FXML
    private Button bt22;
    @FXML
    private Button bt23;
    @FXML
    private Button bt24;
    @FXML
    private Button bt30;
    @FXML
    private Button bt31;
    @FXML
    private Button bt32;
    @FXML
    private Button bt33;
    @FXML
    private Button bt34;
    @FXML
    private Button bt40;
    @FXML
    private Button bt41;
    @FXML
    private Button bt42;
    @FXML
    private Button bt43;
    @FXML
    private Button bt44;
    @FXML
    private GridPane GD;
    @FXML
    private Label idCurrentPlayer;
    @FXML
    private Label idAfficherWinner;
    @FXML
    private Label WinnerLabel;

    /*****************************************/
    Player humainPlayr = new PlayerHumain();
    public Plateau p = new Plateau();
    public static int playerTurn = 1;
    public boolean optionSelectionner=true;//first click
    //les positions des boutons que le user peut selectionner
    public ArrayList<Position> myArrayDest1 = new ArrayList<Position>();
    //les positions des boutons que le user peut selectionner
    public ArrayList<Position> myArraySource1= p.CasesJouablesSource(playerTurn); // initilize the playable cases for the first player
    //les position des pieces jouées
    public Position selectionneDestination;
    public Position selectionneSource;
    ArrayList<Button> allButtons;
    ArrayList<Button> lateralButtons;
    /*
    private Controller()
    {}
     */

   /************************************************************/

    @FXML
    void RestartGame(ActionEvent event) {
        //Restart
        allButtons = new ArrayList<>(Arrays.asList(bt44,bt43,bt42,bt41,bt40,bt34,bt33,bt32,bt31,bt30,bt24,bt23,bt22,bt21,bt20,bt10,bt11,bt12,bt13,bt14,bt04,bt03,bt02,bt01,bt00));
        lateralButtons = new ArrayList<>(Arrays.asList(bt44,bt43,bt42,bt41,bt40,bt34,bt30,bt24,bt20,bt10,bt14,bt04,bt03,bt02,bt01,bt00));
        p = new Plateau();
                /*.game =
                new int[][]{
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                };*/
        allButtons.forEach(button ->{
           button.setText("");
        });
        playerTurn = 1;
        lateralButtons.forEach(button ->{ if(button.isDisabled()==true){
            button.setDisable(false);} });
        idAfficherWinner.setText("");
        idCurrentPlayer.setText("Tour Humain");
        optionSelectionner= true;
        myArrayDest1 = new ArrayList<Position>();
        myArraySource1= p.CasesJouablesSource(playerTurn);
        UpdateAffichage();
    }


    //Cette Fonction met à jour les cases et changements dans le Board
    public void UpdateAffichage(){
        if (p.game[0][0]== 2){bt00.setText("X");}else if (p.game[0][0]== 1){bt00.setText("O"); } else {bt00.setText(""); }
        if (p.game[0][1]== 2){bt10.setText("X");}else if (p.game[0][1]== 1) {bt10.setText("O");} else {bt10.setText("");}
        if (p.game[0][2]== 2){bt20.setText("X");}else if (p.game[0][2]== 1) bt20.setText("O"); else {bt20.setText("");}
        if (p.game[0][3]== 2){bt30.setText("X");}else if (p.game[0][3]== 1) bt30.setText("O"); else {bt30.setText("");}
        if (p.game[0][4]== 2){bt40.setText("X");}else if (p.game[0][4]== 1) bt40.setText("O"); else {bt40.setText("");}
        if (p.game[1][0]== 2){bt01.setText("X");}else if (p.game[1][0]== 1) bt01.setText("O");else {bt01.setText("");}
        if (p.game[1][1]== 2){bt11.setText("X");}else if (p.game[1][1]== 1) bt11.setText("O");else {bt11.setText("");}
        if (p.game[1][2]== 2){bt21.setText("X");}else if (p.game[1][2]== 1) bt21.setText("O");else {bt21.setText("");}
        if (p.game[1][3]== 2){bt31.setText("X");}else if (p.game[1][3]== 1) bt31.setText("O");else {bt31.setText("");}
        if (p.game[1][4]== 2){bt41.setText("X");}else if (p.game[1][4]== 1) bt41.setText("O");else {bt41.setText("");}
        if (p.game[2][0]== 2){bt02.setText("X");}else if (p.game[2][0]== 1) bt02.setText("O");else {bt02.setText("");}
        if (p.game[2][1]== 2){bt12.setText("X");}else if (p.game[2][1]== 1) bt12.setText("O"); else {bt12.setText("");}
        if (p.game[2][2]== 2){bt22.setText("X");}else if (p.game[2][2]== 1) bt22.setText("O");else {bt22.setText("");}
        if (p.game[2][3]== 2){bt32.setText("X");}else if (p.game[2][3]== 1) bt32.setText("O");else {bt32.setText("");}
        if (p.game[2][4]== 2){bt42.setText("X");}else if (p.game[2][4]== 1) bt42.setText("O");else {bt42.setText("");}
        if (p.game[3][0]== 2){bt03.setText("X");}else if (p.game[3][0]== 1) bt03.setText("O");else {bt03.setText("");}
        if (p.game[3][1]== 2){bt13.setText("X");}else if (p.game[3][1]== 1) bt13.setText("O"); else {bt13.setText("");}
        if (p.game[3][2]== 2){bt23.setText("X");}else if (p.game[3][2]== 1) bt23.setText("O");else {bt23.setText("");}
        if (p.game[3][3]== 2){bt33.setText("X");}else if (p.game[3][3]== 1) bt33.setText("O");else {bt33.setText("");}
        if (p.game[3][4]== 2){bt43.setText("X");}else if (p.game[3][4]== 1) bt43.setText("O");else {bt43.setText("");}
        if (p.game[4][0]== 2){bt04.setText("X");}else if (p.game[4][0]== 1) bt04.setText("O");else {bt04.setText("");}
        if (p.game[4][1]== 2){bt14.setText("X");}else if (p.game[4][1]== 1) bt14.setText("O");else {bt14.setText("");}
        if (p.game[4][2]== 2){bt24.setText("X");}else if (p.game[4][2]== 1) bt24.setText("O");else {bt24.setText("");}
        if (p.game[4][3]== 2){bt34.setText("X");}else if (p.game[4][3]== 1) bt34.setText("O");else {bt34.setText("");}
        if (p.game[4][4]== 2){bt44.setText("X");}else if (p.game[4][4]== 1) bt44.setText("O");else {bt44.setText("");}
    }


    //Cette fonction selectionner  représente le premier click du player
    public void selectionner( Integer rowIndex, Integer colIndex){
    //les bouton que le user peut selectionner
    selectionneSource = new Position(colIndex, rowIndex);

    if (myArraySource1.stream().filter((position) -> position.x==selectionneSource.x && position.y== selectionneSource.y).anyMatch(position -> true) ){
        //l'état de tableau (buttons) dépend de cette liste myArrayDist (aprés avoir selectionner une case)
        myArrayDest1 = p.CasesJouablesDestination(5,selectionneSource);
        //je désactive touts les boutons
        disableAllNode();
        //j'active les boutons qui peuvent être joués
        EnableButtons(myArrayDest1);
        //je vide la list myArraySource
        myArraySource1.clear();
        //la variable bolean optionSelectionner pour déterminer le type du coup que je vais jouer
        optionSelectionner=false;
        //mettre à jour les changement effectuer
        UpdateAffichage();
        p.printBoard();
        }
    }


    //Cette fonction deplacer correspond au deuxième click du player
    public void deplacer(Integer rowIndex, Integer colIndex){
        //La positions vers les quel le player peut dépacer son coup
        selectionneDestination = new Position(colIndex, rowIndex);

        if (myArrayDest1.stream().filter((position) -> position.x==selectionneDestination.x && position.y== selectionneDestination.y).anyMatch(position -> true)){
            //faire le déplacement
            humainPlayr.deplacement(selectionneSource,selectionneDestination,playerTurn,p.game);
            UpdateAffichage();
            if(p.isSolved()==-10){
                idAfficherWinner.setText("Humain a gagner ! Félicitation ");

                System.out.println("Humain a gagner ! Félicitation");

                //desable All
                disableAllNode();
            }else if(p.isSolved()==10){
                idAfficherWinner.setText("IA a gagner !  ");
                System.out.println("IA a Gagner !");
                disableAllNode();
            }else{

                playerTurn = playerTurn==1 ? 2 : 1;
                myArrayDest1.clear();
                myArraySource1 = p.CasesJouablesSource(playerTurn);
                disableAllNode();
                EnableButtons(myArraySource1);
                // les cases désactivé maintenant dépende de cette liste
                optionSelectionner = true;
                UpdateAffichage();
                p.printBoard();
            }
        }
    }


    private static Controller cntrl = new Controller();

    public static Controller getInstance()
    {   return cntrl;
    }


    //Cette Fonction correspond au click que fait l'utilisateur dans le board
    @FXML
    void jouer(ActionEvent e) throws InterruptedException {

        idCurrentPlayer.setText("Tour Humain ");
        Node source = (Node) e.getSource();
        Integer rowIndex = GD.getRowIndex(source)==null? 0: GD.getRowIndex(source);
        Integer colIndex = GD.getColumnIndex(source)==null?0: GD.getColumnIndex(source);
        System.out.println(rowIndex+""+colIndex);
        // Si c'est le premier clique alors selectionner une case sinon déplacer et passer le turn aux deuxième player
        if(playerTurn==1) {
            idCurrentPlayer.setText("Tour Humain ");
            if (optionSelectionner) {

                //System.out.println(myArraySource1.size());
                selectionner(colIndex, rowIndex);

            } else {
                deplacer(colIndex, rowIndex);
                Thread.sleep(500);
            }

            UpdateAffichage();

        }

        // aprés avoir jouer l'humain (l'IA doit jouer aussi et pour cela il faut pas avoir de else (pour continuer directement l'execution)
        if(playerTurn==2){
            idCurrentPlayer.setText("Tour IA ");
            PlayerAI playerIa = new PlayerAI(2);
            int [][]newGame = p.getGame();
            Plateau plateauTemp = new Plateau(newGame.clone());

            System.out.println("starting min max algo");
            Thread.sleep(1000);
                ArrayList<Position> minimaxChoix = playerIa.GetMinMaxMove(plateauTemp.getGame());


            System.out.println("Ending min max algo");
            // appeler la fonction min max qui retourn quoi selection et ou déplacer

            if (optionSelectionner) {
                // selectionner selon le résultat de min max
                System.out.println("Selectionner minmax -- ("+minimaxChoix.get(0).y+","+minimaxChoix.get(0).x+")");
                selectionner(minimaxChoix.get(0).y, minimaxChoix.get(0).x);

                // déplacer selon minmax
                System.out.println("Deplacer minmax -- ("+minimaxChoix.get(1).x+","+ minimaxChoix.get(1).y+")");
                deplacer(minimaxChoix.get(1).y, minimaxChoix.get(1).x);
                UpdateAffichage();
                //playerTurn=1;
                idCurrentPlayer.setText("Tour Humain ");
            }
        }
    }


    //Cette fonction desactive tous les boutons
    public void disableAllNode () {
        ObservableList<Node> allButtons = GD.getChildren();

        for (Node node : allButtons) {
            node.setDisable(true);
        }
    }



    //Cette fonction active tous les boutons
    public void EnableButtons(ArrayList<Position> ButtonsPosition){

        ObservableList<Node> allButtons = GD.getChildren();

        for (Node node : allButtons) {
            for (Position po : ButtonsPosition) {
                Integer rowIndex = GD.getRowIndex(node)==null? 0: GD.getRowIndex(node);
                Integer colIndex = GD.getColumnIndex(node)==null?0: GD.getColumnIndex(node);
                if (rowIndex== po.getX() && colIndex== po.getY())
                    node.setDisable(false);
            }
        }
    }


    @FXML
    void initialize(){
        // tous les buttons sont désactivés dans un premier temps

        disableAllNode();
        // on active les buttons joueable pour le premier joueur
        EnableButtons(myArraySource1);
        System.out.println("****************************");

    }


/*
*
private Controller()
    {}

    private static Controller cntrl = new Controller();

    public static Controller getInstance()
    {   return cntrl;
    }
*
*
* */






}
