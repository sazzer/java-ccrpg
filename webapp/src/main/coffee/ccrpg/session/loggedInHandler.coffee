define(["crossroads", "ccrpg/session/loggedInSignal"], (crossroads, LoggedInSignal) ->

  crossroads.addRoute '/logout', () ->
    LoggedInSignal.dispatch(false)
  crossroads.addRoute '/login/external/{name}', (name) ->
    console.log("Logging in via: " + name)
    LoggedInSignal.dispatch(true)
)