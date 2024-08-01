# GitHubRepoFinder API
The GitHubRepoFinder API allows users to retrieve repository information from GitHub, including the list of branches and the latest commit SHA for each branch.

## Before Starting
Before running the project, make sure you have installed on your system:
- Java 21 
- Maven 

## Setup
If you have completed the previous steps, you can start cloning the project to your computer:
1. Navigate to the main page of the repository
2. Click  <> Code button
3. Clone the project:

```sh
$ git clone https://github.com/anastasimars/githubrepofinder.git
```

5. Navigate to the directory where you’d like your cloned directory to appear. Use the cd command to change the directory:

```sh
$ cd '/c/Users/YOUR_USERNAME/My Project'
```

7. Build with Maven: 

```sh
mvn clean install
```
8. Run the application: 
```sh
mvn spring-boot:run
```

## Features
- Fetch repositories for a given GitHub username
- List branches for each repository, excluding forks
- Error handling for cases where the username is not found

## Usage
Once the application is running, you can interact with it using HTTP requests. The primary endpoint is:

GET /api/github/users/{username}/repos

Example usage:
To fetch repositories for a user named jakubnabrdalik, send a GET request to:

```sh
http://localhost:8080/api/github/users/jakubnabrdalik/repos
```
This will return a JSON response containing the repositories and their branches for the specified user.

# Error Handling
404 Not Found: If the username does not exist, the API returns a UsernameNotFoundException.
