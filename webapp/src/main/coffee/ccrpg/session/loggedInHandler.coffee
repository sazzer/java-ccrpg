define(["crossroads", "hasher", "ccrpg/session/loggedInSignal"], (crossroads, hasher, LoggedInSignal) ->

  crossroads.addRoute '/logout', () ->
    LoggedInSignal.dispatch(false)
    hasher.replaceHash("")

  crossroads.addRoute '/login/external/{name}', (name) ->
    console.log("Logging in via: " + name)
    LoggedInSignal.dispatch(true)
    hasher.replaceHash("profile")
)