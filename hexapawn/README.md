## Q2:

    Si SuccesseurMin < 0 -> -1 * (MAX(successeursNegatif) - 1)
    Si SuccesseurMin > 0 -> -1 * (MAX(successeurs) + 1)

## Optimisations

Pour optimiser notre algorithme, on a faire recours à la mémoization, c'est à dire qu'on va stocker à chaque fois que
l'on trouve une valeur d'une configuration dans une map que l'on va utiliser pour les autres configurations

On stocke les configurations dans une Map pour chaque joueur, avec pour clé le board, et comme valeur le résultat de la
configuration

## Encore plus d'optimisations

Pour optimiser encore plus notre algorithme, lorsque l'on trouve une valeur à une configuration, on peut calculer celle
de l'adversaire pour la mémoriser dans le même temps. On calcule la valeur de l'adversaire en fonction du joueur
courant, par example, si c'est on trouve une configuration pour le joueur blanc, on peut inverser la valeur et lui
ajouter 1 pour obtenir celle du joueur noir.

Pour pousser encore plus l'optimization, on peut aussi inverse le board pour trouver de nouvelles valeur de
configuration.

En prenant par example 
