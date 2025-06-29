# Spring Security OAuth2 Resource Server with Keycloak and Logging

Ce projet est une démonstration d'un **Resource Server** sécurisé avec **Spring Security OAuth2**, utilisant **Keycloak** comme serveur d'autorisation, avec une **journalisation complète** des accès et événements dans des fichiers `.log`.

---

## 🛠️ Technologies utilisées

- Java 17+
- Spring Boot 3.5.3
- Spring Security (OAuth2 Resource Server)
- Keycloak 26
- SLF4J / Logback
- Maven

---

## 🔐 Fonctionnalités principales

- Authentification JWT avec Keycloak (OAuth2 Resource Server)
- Protection des endpoints REST
- Journalisation automatique des requêtes HTTP
- Sauvegarde des logs dans des fichiers `.log`
- Configuration flexible via `application.properties`

---

## 🚀 Lancer le projet localement

### 1. Cloner le dépôt

```bash
git clone https://github.com/pegueo2000/spring-oauth2-keycloak-logging.git
cd spring-oauth2-keycloak-logging
