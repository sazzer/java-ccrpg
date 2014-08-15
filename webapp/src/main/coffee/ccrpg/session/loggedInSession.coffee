define(["ccrpg/session/loggedInSignal"], (LoggedInSignal) ->
  LoggedInSignal.add (state) ->
    console.log("Logged In state changed to: " + state)
)