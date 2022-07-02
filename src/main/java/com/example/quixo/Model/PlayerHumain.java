package com.example.quixo.Model;


public class PlayerHumain implements Player {


    public void deplacement(Position A_source, Position B_Destination, int player, int [][] game) {

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





}
