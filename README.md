# Client FTP
**Auteurs** : **Plancke** Aurélien & **Plé** Lucas M1 E-Services
aurelien.plancke.etu@univ-lille.fr
lucas.ple.etu@univ-lille.fr

Ce programme server ftp a été réalisé en Java dans le cadre des cours de Système Répartis en Master 1 E-Services.
Il a pour but de se comporter en tant que serveur FTP et de gérer les connexions clientes. Ce serveur les commandes de bases ainsi que les connexions concurencielles.

## Installation

### Prérequis pour installer le projet

- [Java 11](https://www.oracle.com/fr/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven](https://maven.apache.org/)

Voici les commandes élémentaires à lancer depuis la racine du projet afin de l'utiliser :

### Compiler le projet 
mvn test clean compile assembly:single


### Générer la Javadoc
mvn javadoc:javadoc


### Accéder à la javadoc 
firefox target/site/apidocs/index.html


## Fonctionnement de la CLI


### Exemple d'utilisation


## Fonctionnement du programme

## Architecture


## Commandes implémentées

| Commande FTP |                               Effet                                |                      Prends en paramètres                       | Code succes | Code erreur
|:------------:|:------------------------------------------------------------------:|:---------------------------------------------------------------:|:-----------:|:----------:|
|     USER     |                 Commence la procédure de connexion                 |                      Le nom d'utilisateur                       |      331    |     530    |
|     PASS     |                 Permet de vérifier le mot de passe                 |                Le mot de passe de l'utilisateur                 |      230    |     503    |
|     PASV     | Permet de demander une ip et un port pour une connexion de données |               /                                                 |      227    |     421    |
|     CDUP     | Remonte d'un répertoire par rapport à celui courant                |               /                                                 |      250    |     401    |
|     CWD      | Change le répertoire courant                                       |Le chemin vers le répertoire vers lequel se déplacer             |      250    |     401    |
|     DELE     | Supprime un fichier                                                |               Le chemin vers le fichier à supprimer             |      250    |     550    |
|     LIST     | List tout les fichiers du répertoire courant                       |               /                                                 |      226    |     550    |
|     MKD      | Créer un répertoire                                                |              Le chemin vers le répertoire à créér               |      257    |     550    |
|     PWD      | Affiche le repertoire courrant                                     |               /                                                 |      257    |     /      |
|     RETR     | Télécharge un fichier depuis le serveur                            |               Le chemin ou le nom du fichier à télécharger      |      226    |     421    |
|     RMD      | Supprime un répertoire                                             |               Le chemin vers le répertoire à supprimer          |      250    |     550    |
|     RNFR     | Renommes un fichier ou répertoire                                  |               Le nouveau nom                                    |      227    |     421    |
|     STOR     | Téléverse un fichier vers le serveur                               |               Le nom du fichier à téléverser                    |      226    |     421    |
|     TYPE     | Permet d'activer le mode binaire                                   |               /                                                 |      200    |     /      |

## Code samples

## Gestion des erreurs

## UML

![img.png](rsc/uml.png)

## Vidéo de fonctionnement

![Demo](rsc/demo.mp4)
