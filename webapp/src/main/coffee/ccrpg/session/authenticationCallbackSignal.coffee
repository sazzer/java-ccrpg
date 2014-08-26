define(["signals", "ccrpg/session/loggedInSignal"], (signals, LoggedInSignal) ->
  callbackSignal = new signals.Signal()

  window.authenticationCallback = (data) -> callbackSignal.dispatch(data)

  return callbackSignal
)