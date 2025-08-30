# Introduction

Security concept.

## Identity
In general an Identity is the collection of contextual attributes associated with human user (person), or entity as application, device, component, or machine. An Identity is usually managed by the Identity Provider.
Single entity (human user, or machine) can have more than one identity. Let's take you as a person. You can have Work Identity (work user account), Social Network Identity (faceboook user account), or any other contextual Identity. 

## Identity Provider
An Identity Provider stores Identities, identity related attributes and credentials. It provides functions as authentication (log in, sign in), user account registration (sign up), account management and more. 

## User Account
A human user Identity is associated with an User Account, so an Identity Provider can Authenticate a user. Modern Identity management solutions enable an User Account to be associated with more Identities via account linking and identity federation. 

## Authentication
The Authentication it is about to provide login credentials by an User which are associated with his/her account. From perspective of a system which protect a resource, it is important to know who is making the request to access the resource. The Authentication is the process of verifying an Identity of user, machine, or system. There are various credentials associates with account as password, token, certificate, or cookie which can be checked by the system responisble for Authentication. 

## Authorization
The Authorization is about to setup privileges to User account in order to grant access to protected Resources based on Access Policy. The Access Policy defines the conditions that must be satisfied to grant access to a Resource.

## Access Policy Enforcement
The Access Policy Enfrocement is about enforcing the  Access Policy to ensure any actions taken by the User are allowed by the privileges they have been granted. The Access Policy defines the conditions that must be satisfied to grant access.

Example of the Access Policy conditions:

* User must belong to given Group
* User must have given Role
* USer must access Resource from given Location 

Using Policies we implement strategies as RBAC (Role Based Access Control), ABAC (Attribute Based Access Control), or combinations of them.  

--------------------
## User
An individual identity which is defined in the identity storage. The storage which can be an RDBMS, flat file or LDAP server contains user attributes like username and password.

## Group
A set of users classified with a set of common characteristics which usually lead to a set of common permissions and access levels.

## Security Realm
The access channel for the application server to storage containing user’s authentication and grouping information

## Role
An application developer specifies which roles can access which set of the application functions. These roles are then mapped to users and groups using the vendor specific configuration files

## Principal
An identity with known credentials which can be authenticated using an authentication protocol.

## Credential
Contains or references information used to authenticate a principal.s Password is a simple credential used for authentication.