


package com.example.quixo.Model;


import java.util.ArrayList;

public class PlayerAI {

    Integer playerTurn = 2 ;
    public PlayerAI(Integer playerTurn) {
        this.playerTurn = playerTurn;
    }


    /**
     * 1* Générer tous les mouvements possibles et leurs déplacement dans un premier temps
     * 2* pour chaque déplacement (nouveau board) rappeler l'algorithme minimax encore une fois
     * 3* mettre cette valeur dans un variable de retour
     * 4* la récursivité permet de générer un nombre d'appels en profondeur et d'avoir un score finale pour ce déplacement
     * */
    //Cette fonction retourne quel est le meilleur mouvement à effectuer
    public ArrayList<Position> GetMinMaxMove(int[][] game){
        // Récupérer les cases jouables pour le player (IA)
        Plateau plateau = new Plateau();
        int[][] gameTemp = cloneGame(game);
        ArrayList<Position> meilleurChoix = new ArrayList<>(2);
        int meilleurScore = Integer.MIN_VALUE;
        //Je clone mon tableau
        // créer une copie de tableau (java utilise les références dans les tableaux, arraylist ...
        plateau.setGame(cloneGame(game));
        ArrayList<Position> movables = plateau.CasesJouablesSource(playerTurn);
        for (Integer i = 0; i < movables.size(); i++) {
            // réinitialiser le plateau pour chaque case jouable et éviter les erreurs
            plateau.setGame(cloneGame(game));
            ArrayList<Position> destinations = plateau.CasesJouablesDestination(5,movables.get(i));
            for (int j = 0; j < destinations.size(); j++ ) {
                deplacement(movables.get(i), destinations.get(j), this.playerTurn, plateau.getGame());
                // Appeler le minimax algo pour récupérer la meilleure valeur si on va faire ce déplacement.
                int scoreMinimax = minimaxAlgo(cloneGame(plateau.getGame()),2,this.playerTurn );

                // cette condition sert à soustraire deux de score finale si la case sélectionner contient déja une case de joueur.
                // nous avons proposé qu'un joueur a plus de chance de gagner s'il va insérer une nouvelle case sur le plateau.
                //if (game[movables.get(i).getX()][movables.get(i).getY()]==2) scoreMinimax-=2;
                if (scoreMinimax>meilleurScore || (i==0&&j==0)){
                    meilleurScore = scoreMinimax;
                    meilleurChoix.add(0, movables.get(i));
                    meilleurChoix.add(1 ,destinations.get(j));
                    meilleurChoix.subList(2,meilleurChoix.size()).clear();
                }
            }
        }
        return meilleurChoix;
    }




    // génère tous les premiers cases mouvables et la destination de chaque mouvement, puis appelle cette fonction.
    // compare le résultat de chaque coup avec le meilleur score et choisit le meilleur à la fin.
    public int minimaxAlgo(int[][] game, int depth,int joueurEnCours){
        Plateau plateau = new Plateau();
        // créer une copie de tableau (java utilise les références dans les tableaux, arraylist ...
        int[][] gameTemp = cloneGame(game);
        // créer un nouveau plateau avec le board passé en paramètres.
        plateau.setGame(cloneGame(game));
        // Calculer combien de movables est possible
        ArrayList<Position> movables = plateau.CasesJouablesSource(joueurEnCours);
        //je calcule le score pour ce tableau initiale
        int myScore = GetPlateauScore(plateau.getGame(), joueurEnCours);
        // si y a pas de mouvement ou la profondeur fixé est atteinte alors retourner le score
        // movables.size()==0 forcément un cas de jeu gagnant (donc score = longeur de plateau)
        //le cas triviale pour sortir de récursivité
        if (depth < 1 || myScore == plateau.getGame().length ) {
            return myScore;
        }
        // sinon continuer à calculer le score en appelant minimaxAlgo encore une fois
        int scoreFinale = 0;
        for (Integer i = 0; i < movables.size(); i++) {
            plateau.setGame(cloneGame(game));
            ArrayList<Position> destinations = plateau.CasesJouablesDestination(5,movables.get(i));
            // aprés chaque destination déplacer et avoir le nouveau dashboard
            // calculer le score
            for (int j = 0; j < destinations.size(); j++ ){
                // déplacer
                // chaque déplacement doit se faire sur un nouveau board afin d'éviter le probleme de référence dans les tableau java.
                if (joueurEnCours==2) {
                    // Mettre la valeur à min car notre but c'est de prendre le score maximum qui sera supérieur
                    scoreFinale = Integer.MIN_VALUE;
                    deplacement(movables.get(i), destinations.get(j), 2, cloneGame(plateau.getGame()));
                    scoreFinale = Integer.max(scoreFinale, minimaxAlgo(cloneGame(plateau.getGame()), depth - 1, monAdversaire(joueurEnCours)));
                } else{
                    // Mettre la valeur à max
                    scoreFinale = Integer.MAX_VALUE;
                    deplacement(movables.get(i), destinations.get(j), 1, plateau.getGame());
                    scoreFinale = Integer.min(scoreFinale, minimaxAlgo(plateau.getGame(), depth - 1, monAdversaire(joueurEnCours)));
                }
            }
        }
        return scoreFinale;

    }




    //**************************************************************************//

    public int GetPlateauScore(int[][] Board,int player)  {
        // calculer les scores (lignes, diagonal, colonnes)
        int diagScore = getDiagScore(Board, player);
        int diagScoreOppose = getInvDiagScore(Board, player);
        int ligneScore = getMaxLigneScore(Board, player);
        int colsScore = getMaxColonnesScore(Board, player);

        // ArrayList<Integer> scores = new ArrayList<Integer>();
        //List<Integer> bufferList = Arrays.asList(diagScore, diagScoreOppose, ligneScore, colsScore);
        int max = Integer.max(diagScore,diagScoreOppose);
        max = Integer.max(max,ligneScore);
        max = Integer.max(max,colsScore);
        // scores.addAll(bufferList);
        //System.out.println("max score plateau : " + getMaxInArray(scores));
        // return getMaxInArray(scores);
        return max;
    }


    public int getLigneScore(ArrayList<Integer> ligne, int player )  {
        int score = Integer.MIN_VALUE;
        for (int i = 0; i < ligne.size(); i++) {
            if (ligne.get(i) == player) {
                if (score==Integer.MIN_VALUE) score=1;
                else score++;
            }
        }
        return score;
    }

    public int getMaxLigneScore(int[][] Board, int player)  {
        int size = Board.length;

        ArrayList<Integer> scores = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> ligne = new ArrayList<Integer>();

            //
            for(int j=0; j< Board[i].length; j++){
                ligne.add(Board[i][j]);

            }
            System.out.println("Calcule score ligne: "+ ligne.toString());
            scores.add( getLigneScore(ligne, player));
        }
        //System.out.println("max score lignes : " + getMaxInArray(scores));
        return getMaxInArray(scores);
    }

    public int getMaxColonnesScore(int[][] Board, int player)  {
        int size = Board.length;
        ArrayList<Integer> scores = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> colonne = new ArrayList<Integer>();
            //
            for(int j=0; j< Board.length; j++){
                colonne.add(Board[j][i]);
            }
            scores.add( getLigneScore(colonne, player));
        }

        return getMaxInArray(scores);
    }

    public Integer monAdversaire(Integer playerTurn){
        if (playerTurn==1)
            return 2;
        else
            return 1;
    }
    public int getDiagScore(int[][] Board, int player)  {
        ArrayList<Integer> diagonal = new ArrayList<>() ;
        int size = Board.length;
        for (int i = 0; i < size; i++ ){
            diagonal.add(Board[i][i]);
        }
        return getLigneScore(diagonal, player);
    }

    public int getInvDiagScore(int[][] Board, int player) {
        ArrayList<Integer> diagonalInverse = new ArrayList<>() ;
        int size = Board.length;
        for (int i = 0; i < size; i++ ){
            diagonalInverse.add(Board[i][size-1-i]);
        }
        return getLigneScore(diagonalInverse, player);
    }

    public int getMaxInArray(ArrayList<Integer> scoreArray)  {
        int max = 0;
        for(int i = 0; i < scoreArray.size(); i++) {
            if (scoreArray.get(i) > max) {
                max = scoreArray.get(i);
            }
        }
        return max;
    }



    public void deplacement(Position A_source, Position B_Destination, int player,int [][] game) {

        int XSource = A_source.getX(), YSource = A_source.getY();
        int XDest = B_Destination.getX(), YDest = B_Destination.getY();
        /* si il sont sur la même colonne,  la chose qu'il faut modifier c'est la ligne
         *  mais il faut aussi décaler toutes les autres pièces selon 2 cas  */
        if (YSource == YDest) {
            // si la destination a un id plus grand
            //on décale vers La droite (incrémente)
            if (XSource < XDest) {
                for (int i = XSource; i < 4; i++) {

                    game[i][YSource] = game[i + 1][YSource];

                }
            }
            // si la destination a un id plus petit
            //on décale vers La gauche(décrémente)
            if (XSource > XDest) {
                for (int i = XSource; i > 0; i--) {
                    game[i][YSource] = game[i - 1][YSource];

                }
            }

        }
        /* si il sont sur la même ligne  la chose qu'il faut modifier c'est la colonne
         *  mais il faut aussi décaler toutes les autres pièces selon 2 cas  */
        if (XSource == XDest) {
            // si la destination a un id plus grand
            //on décale vers La droite (incrémente)
            if (YSource < YDest) {
                for (int i = YSource; i < 4; i++) {

                    game[XSource][i]= game[XSource][i + 1];

                }
            }
            // si la destination a un id plus petit
            //on décale vers La gauche(décrémente)
            if (YSource > YDest) {
                for (int i = YSource; i > 0; i--) {
                    game[XSource][i]= game[XSource][i - 1];

                }
            }
        }


        game[XDest][YDest] = player;

    }


    public void printGame(int[][] game){
        for (int i =0;i<5;i++){
            for(int j =0;j<5;j++){
                String val="?";
                if(game[i][j]==1){
                    val="O";
                }else if(game[i][j]==2){
                    val="X";
                }
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }

    public int [][] cloneGame(int[][] game){
        if(game == null) return null;

        int[][] result = new int[game.length][];
        for(int i=0; i< game.length; i++){
            result[i] = game[i].clone();
        }
        return result;
    }
}







