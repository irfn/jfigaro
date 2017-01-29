# JFigaro [![Build Status](https://travis-ci.org/bitfurry/jfigaro.svg?branch=master)](https://travis-ci.org/bitfurry/jfigaro)

JFigaro is java based Application configuration library inspired by ruby gem [Figaro](https://github.com/laserlemon/figaro)

Like Figaro, JFigaro is inspired by the [Twelve-Factor App](http://12factor.net) methodology, which states:

> The twelve-factor app stores config in environment variables (often shortened to env vars or env). Env vars are easy to change between deploys without changing any code; unlike config files, there is little chance of them being checked into the code repo accidentally; and unlike custom config files, or other config mechanisms such as Java System Properties, they are a language- and OS-agnostic standard.

This is straightforward in production environments but local development environments are often shared between multiple applications, requiring multiple configurations.

#Whats Wrong with using java properties for configuration?

- Java properties is an ancient api and has not improved or changed in decades
- Properties need to be included into the artifact(although this can be manipulated via changing classpaths and permissions which is messy). 
- The more scalable way to have configuration is via environment variables. (see 12factor)
- Proliferation of properties files to support testing etc.

#Whats different?

JFigaro parses a YAML file in your application for setting environment in dev and test modes. In other modes Environment is used via `System.getenv`

- Single application.yml (typically not checked into code, application.yml.example could be checked in instead) for defining development, test environments
- Any configuration may be overridden via environment (even in development/test). Environment defined configuration is always preferred.

# Yet another way

<img src="http://imgs.xkcd.com/comics/standards.png">


# Roadmap

- | Feature | done|
- | MVP | yes|
- | Logging | no |
# App Environment
`APP_ENVIRONMENT` is the environment variable which jFigaro looks for. 

```
set APP_ENVIRONMENT = production
```

development is assumed to be the default.


### Example
```
ApplicationConfiguration configuration = Figaro.configure()
configuration.getValue("SOME_VALUE")
``` 
