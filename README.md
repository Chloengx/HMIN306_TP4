# Auteurs
+ Anthony Chaillot
+ Louis Daviaud

# Installation

Sous-eclipse (autres), ajouter TOUS les fichiers jar au build path

# Utilisation

## ASTParser

Executer hmin306.tp4.astparser.main.ASTParserMain.java

## Spoon

Executer hmin306.tp4.spoon.main.SpoonMain.java

## Fonctionnement

Pour ASTParser et Spoon, toutes les fonctionnalités suivantes ont étés developpées, l'utilisation est la même pour les 2 Mains.

Notre projet utlise des chaine de caractère pour fonctionner, par défault on utilise le projet lui même, nous analysons donc le projet d'analyse.

## Metriques

Beaucoup plus complète pour ASTParser

## Graphe d'appels

Nous recommendons d'utiliser la version Spoon car celle d'AST pose problème pour les appels de méthode dans une même classe, nottament si l'appel n'utilise pas "this".

## Graphe de couplage



## Dendrogramme (Non fonctionnel)

Nous n'avons pas réussi cet exercice

Nous avons cependant commencé en prenant connaissance de comment afficher un dendrogramme sous Java.
