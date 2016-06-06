# JFigaro

JFigaro is java based Application configuration library inspired by ruby gem [Figaro](https://github.com/laserlemon/figaro)

Like Figaro, JFigaro is inspired by the [Twelve-Factor App](http://12factor.net) methodology, which states:

> The twelve-factor app stores config in environment variables (often shortened to env vars or env). Env vars are easy to change between deploys without changing any code; unlike config files, there is little chance of them being checked into the code repo accidentally; and unlike custom config files, or other config mechanisms such as Java System Properties, they are a language- and OS-agnostic standard.

This is straightforward in production environments but local development environments are often shared between multiple applications, requiring multiple configurations.

JFigaro parses a YAML file in your application for setting environment in dev and test modes. In other modes Environment is used via `System.getenv`


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