<br/>

<p align="center">
  <a href="https://github.com/loryblu/loryblu-android">
    <img src="https://github-production-user-asset-6210df.s3.amazonaws.com/69876102/274274731-31580d3d-27d6-476b-b3c7-93cb61e4e3cc.png" alt="Logo" width="421" height="298">
  </a>



  <h3 align="center">Loryblu Android</h3>

  <p align="center">
    Loryblu is an app designed to help parents and children diagnosed with ASD, especially those looking for or waiting for multidisciplinary treatment, with a playful and interactive environment to contribute in a light, inclusive, and functional way through activities, cognitive stimulation games, and music.
  </p>

<br/>
<br/>

## Table Of Contents

* [About the Project](#about-the-project)
* [Built With](#built-with)
* [License](#license)
* [Authors](#authors)

## About The Project

![app_screen](https://github.com/loryblu/loryblu-android/assets/69876102/09b08090-ba70-4fee-940c-4562c50064ac)

The app has several Stories to help parents with organization, and games for children.
- We use login feature so user can use the app on multiple devices and don't lose their data.
- Logbook: It aims to help parents organize the child's daily tasks.
  -  LoryEstudioso: allows the responsible person to save tasks related to the child's studies.
  -  LoryRotina: allows the responsible person to organize the child's tasks, selecting the day of the week, the time of day, and the recurrence.
- Other stories are still under development. 

Modules Responsibilities

* **app**: Contains the application-level classes and scaffolding that tie together the rest of the codebase.

* **data**: Abstraction for accessing data sources, remote or local
  * ***auth***: Implementation for auth endpoints. Using Ktor for REST API

- **core**: Resources that is useful for all modules
  - ***network***:  Implementation of Ktor and models that API can return.

  - ***ui***: Ui resources thatares used in multiple modules, as strings, drawables and some components.

  - ***util***: Extensions functions and some type validators that are used in multiple modules

- **feature**: Contains all screens and screen's logic.
- **buildSrc**: Configurations that is used in multiple modules



## Built With

- Android Native with Kotlin
- Compose for all screens
- Koin for Dependency Injection
- Ktor for consume API
- Jetpack Navigation
- Multi modules
- Clean architecture



## License

Distributed under the MIT License. See [LICENSE](https://github.com/loryblu/loryblu-android/blob/development/LICENSE) for more information.



## Authors

* **André Moraes Filho** - *Android Developer* - [André Moraes Filho](https://github.com/softdevandre)
* **Jean Patrick Hartmann** - *Android Developer* - [Jean Patrick Hartmann](https://github.com/hartmannjean)
* **Ruliam Santos Oliveira** - *Android Developer* - [Ruliam Santos Oliveira](https://github.com/OdisBy)
