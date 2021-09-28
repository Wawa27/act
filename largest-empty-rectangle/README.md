# Largest empty rectangle

Q1. Un rectangle de surface maximale respectant les contraintes a nécessairement deux sommets de la forme
(xi, 0) (xj, 0) car la base du rectangle est sur l'axe des x (y = 0).

La surface du rectangle maximale respectant les contraintes et dont deux sommets sont (xi, 0) (xj, 0)
est représenté par : (xj - xi) * (yj - yi)

En me basant sur les réponses précedentes, j'ai concu un algorithme simple qui parcours chaque point, et tente de créer
un rectangle avec tout les points suivants, en vérifiant qu'il n'y a pas de point gênant entre ces deux points.

L'éxécution de l'algorithme (5 points) conçu avec les points de l'example se termine au bout d'environ 0.5 milliseconds.

Temps par opération = 0.5ms / 5^3 = 0,004ms
Temps estimée de l'éxécution de l'algorithme avec n = 100000 -> 0,004 * 100000^3 = 126 ans

On peut donc supposer que l'éxécution de l'algorithme avec 100000^3 pourrait prendre 126 ans à s'éxécuter.