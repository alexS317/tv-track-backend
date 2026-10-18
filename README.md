<a id="readme-top"></a>
<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->

<!-- PROJECT LOGO -->
<br />
<div align="center">
<h1 align="center">TV Track (Backend)</h1>

  <p align="center">
    Application to keep track of your favourite TV shows.
    <br />
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

I started this project because I wanted to learn Java/Spring, and wanted to build a project that is not too overwhelming, but still covers a lot of the most common concepts.

In this backend application, users can create an account and save the TV shows they're currently watching in a personal list. They can also track different properties, such as when they started or finished the show, their personal rating or the episode they're currently at.

For the actual data, the project makes use of the [TVmaze API](https://www.tvmaze.com/api). The main purpose of this custom backend is to map the TVmaze id of a show to a specific user account and save that reference in a database, alongside with the aforementioned custom properties.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

- [![Spring][Spring.io]][Spring-url]
- [![PostgreSQL][Postgresql.org]][Postgresql-url]
- [![Postman][Postman.com]][Postman-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->

## Getting Started

Here you find the instructions on how to set up the project locally.

### Prerequisites

Make sure you have the following installed:

- [JDK 25](https://www.oracle.com/java/technologies/downloads/#java25)
- [Docker](https://docs.docker.com/get-started/get-docker/)
- [Maven](https://maven.apache.org/download.cgi) (Optional, you can also use the included Maven wrapper)

### Installation

1. Clone the repo (or download the project as a zip file)
   ```sh
   git clone https://github.com/alexS317/tv-track-backend.git
   cd tv-track-backend
   ```
2. Copy the .env.example file, rename it to .env and generate a secret key to use for the JWT authentication (Node.js is used in this specific command, but there are other options)
   ```sh
   node -e "console.log(require('crypto').randomBytes(32).toString('base64'))"
   ```
3. Start the development server, make sure the docker engine is running in the background beforehand
   ```sh
   # Maven installed separately
   mvn spring-boot:run

   # Using Maven wrapper in Git Bash console
   ./mvnw spring-boot:run
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->

## Usage

The project is a pure backend REST API, the frontend will be built in a separate project. When the development server is running, you can view the API documentation under: http://localhost:8080/swagger-ui/index.html (you may have to add /v3/api-docs in the explore input field if it isn't there by default)

You can use a tool like Postman for testing. In the _readme-resources folder you find a JSON file that contains the configuration for the Postman collection, which you can import into your account. You'll have to set up the variables in the collection accordingly.

To authenticate your user, you copy the token value (which you'll receive in the response body after registering or logging in) into the auth_token field in the Variables section of the Postman collection. In this way, you won't have to manually add the authorization header on every request.

As an authenticated user, you are able to perform different actions:
- View, edit or delete your own user account
- View a list of all your saved shows, add new ones, as well as view/update/delete individual shows
- Test the TVmaze API with the tvmaze_id property of your saved shows

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ROADMAP -->

## Roadmap

Basic features:
- [x] Add user registration and authentication with JWT
- [x] View/create/edit/delete shows

Optional features and improvements:
- [ ] Improve authentication security (e.g. email verification when signing up)
- [ ] Add more user profile customisation features (profile picture etc.)
- [ ] Implement features for interactions between users (e.g. see what shows your friends are watching, send them suggestions)
- [ ] Add more features for organizing shows (e.g. custom lists)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->

## Acknowledgments

- Based on [Best README Template](https://github.com/othneildrew/Best-README-Template)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->


<!-- Shields.io badges. You can a comprehensive list with many more badges at: https://github.com/inttter/md-badges -->

[Spring.io]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/
[Postgresql.org]: https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white
[Postgresql-url]: https://www.postgresql.org/
[Postman.com]: https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white
[Postman-url]: https://www.postman.com/