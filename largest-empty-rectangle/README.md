# Largest empty rectangle

Pour concevoir les algorithmes, j'ai utiliser le design pattern Strategy, qui m'as permis d'implémenter et
d'éxpérimenter des algorithme afin de trouver la meilleur.

J'ai créer des datasets avec les points, la taille du plan, et la réponse à trouver afin de valider les algorithmes
conçus.

## Q1

Un rectangle de surface maximale respectant les contraintes a nécessairement deux sommets de la forme
(xi, 0) (xj, 0) car la base du rectangle est sur l'axe des x (y = 0).

La surface du rectangle maximale respectant les contraintes et dont deux sommets sont (xi, 0) (xj, 0)
est représenté par : (xj - xi) * (yj - yi)

En me basant sur les réponses précedentes, j'ai concu un algorithme simple qui parcours chaque point, et tente de créer
un rectangle avec tout les points suivants, en vérifiant qu'il n'y a pas de point gênant entre ces deux points.

L'éxécution de l'algorithme (5 points) conçu avec les points de l'example se termine au bout d'environ 0.5 milliseconds.

Temps par opération = 0.5ms / 5^3 = 0,004ms Temps estimée de l'éxécution de l'algorithme avec n = 100000 -> 0,004 *
100000^3 = 126 ans

On peut donc supposer que l'éxécution de l'algorithme avec 100000^3 pourrait prendre 126 ans à s'éxécuter.

# Q2

En poursuivant l'approche précéndente, j'ai pu concevoir un algorithme "diviser pour régner".

J'ai dabbord essayer de prendre le point au milieu afin de découper les plan en 2, mais je suis tombé face à un probleme
lorsque le plus grand rectangle se situé dans les deux plans résultant. C'est pourquoi j'ai dabbord parcouru les points
afin de trouver le point le plus petit pour decouper les plans depuis se point.

# Q4

Pour l'algorithme linéaire, j'ai essayer plusieurs algorithmes avant de trouver la meilleur selon moi.

J'ai dabbord essayer de parcours les points de haut en bas. Ensuite, j'ai essayer de placer les points un à un sur le
plan de gauche à droite et de calculer l'aire des rectangles qui était formé.

Et finalement, je pense avoir trouver le meilleur algorithme : il ne faut plus parcourir les points mais les cases de
gauche à droite (en x). Il faut récupérer la hauteur pour chaque x et lors d'un changement calculer le rectangle
résultant, cela est possible car on peut supposer comme l'indique l'éxércice, que les points sont limitée à 1 par
abscisse donnée.

# Q5

J'ai transformé l'agorithme "diviser pour régner" pour pouvoir utiliser toute les capacités du processeur avec la classe
Future de Java.

Pour les cas ou l'on a pas beaucoup le points, l'algorithme de base est plus rapide, mais sinon, l'algorithme parralèle
va jusqu'à 20x plus vite.