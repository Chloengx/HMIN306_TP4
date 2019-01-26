# Auteurs
+ Anthony Chaillot
+ Louis Daviaud

# Installation

Sous-eclipse (ou autre), ajouter TOUS les fichiers jar (lib/) au build path

# Utilisation

## ASTParser

Executer hmin306.tp4.astparser.main.ASTParserMain.java

## Spoon

Executer hmin306.tp4.spoon.main.SpoonMain.java

## Fonctionnement

Pour ASTParser et Spoon, toutes les fonctionnalités suivantes ont étés developpées, l'utilisation est la même pour les 2 Mains.

Par défault, l'analyse porte sur le projet lui même, pour analyser un projet il suffit de mettre le chemin du code source (src/) dans les mains au lieu de "./src"

## Metriques

Beaucoup plus complète pour ASTParser, on relève quelques métriques :
+ Nombre de classes
+ Nombre de méthodes
+ ... (Voir ASTParserMain)

## Graphe d'appels

Nous recommendons d'utiliser la version Spoon car celle d'AST pose problème pour les appels de méthode dans une même classe, nottament si l'appel n'utilise pas "this".

Affichage l'ensemble des classes interne en projet (en bleu), pour chaque classe on vois la liste des méthodes de cette dernière.
Une flèche relie les méthode lorsqu'elle s'appellent entre elles. (Dans le sens de la flèche, methode1 -> méthodeB).

## Graphe de couplage

Affichage l'ensemble des classes interne en projet (en bleu), les classes sont reliés entre elles lorsque ces dernière intéragissent entre elles (via des appelent de méthode). Un trait relie les classes entre elles avec une valeur (x / y), x étant le nombre d'appels entre les deux classes (somme) et y nombre total d'appel de méthode. Dans notre exemple il y a des flèche mais c'est une limite de mxGraph, il ne faut pas en tenir compte.

## Dendrogramme (Non fonctionnel)

Nous n'avons pas réussi cet exercice

Nous avons cependant commencé en prenant connaissance de comment afficher un dendrogramme sous Java.

# Librairie

+ ASTParser
+ [Spoon](http://spoon.gforge.inria.fr)
+ [mxGraph](https://github.com/jgraph/mxgraph) : Pour l'affichage des graphes d'appel/de couplage
