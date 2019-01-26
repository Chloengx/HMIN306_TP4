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

Beaucoup plus complète pour ASTParser, on relève quelques métriques comme le nombre de classe, de méthodes, etc

## Graphe d'appels

Nous recommendons d'utiliser la version Spoon car celle d'AST pose problème pour les appels de méthode dans une même classe, nottament si l'appel n'utilise pas "this".



## Graphe de couplage



## Dendrogramme (Non fonctionnel)

Nous n'avons pas réussi cet exercice

Nous avons cependant commencé en prenant connaissance de comment afficher un dendrogramme sous Java.

# Librairie

+ ASTParser
+ Spoon
+ [mxGraph](https://github.com/jgraph/mxgraph)
